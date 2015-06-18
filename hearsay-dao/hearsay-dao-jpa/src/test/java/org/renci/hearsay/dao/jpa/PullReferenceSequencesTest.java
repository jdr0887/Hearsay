package org.renci.hearsay.dao.jpa;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.gbff.Filter;
import org.renci.gbff.GBFFManager;
import org.renci.gbff.filter.AndFilter;
import org.renci.gbff.filter.FeatureSourceOrganismNameFilter;
import org.renci.gbff.filter.FeatureTypeNameFilter;
import org.renci.gbff.filter.SequenceAccessionPrefixFilter;
import org.renci.gbff.filter.SourceOrganismNameFilter;
import org.renci.gbff.model.Feature;
import org.renci.gbff.model.Sequence;
import org.renci.gff3.GFF3Manager;
import org.renci.gff3.filters.AttributeValueFilter;
import org.renci.gff3.model.GFF3Record;
import org.renci.hearsay.dao.HearsayDAOBean;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.GenomeReference;
import org.renci.hearsay.dao.model.Identifier;
import org.renci.hearsay.dao.model.ReferenceSequence;
import org.renci.hearsay.dao.model.ReferenceSequenceChromosomeType;
import org.renci.hearsay.dao.model.ReferenceSequenceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PullReferenceSequencesTest extends AbstractPullTest {

    private final Logger logger = LoggerFactory.getLogger(PullReferenceSequencesTest.class);

    private static EntityManagerFactory emf;

    private static EntityManager em;

    private static final HearsayDAOBean hearsayDAOBean = new HearsayDAOBean();

    public PullReferenceSequencesTest() {
        super();
    }

    @BeforeClass
    public static void setup() {
        emf = Persistence.createEntityManagerFactory("test-hearsay", null);
        em = emf.createEntityManager();

        GeneDAOImpl geneDAO = new GeneDAOImpl();
        geneDAO.setEntityManager(em);
        hearsayDAOBean.setGeneDAO(geneDAO);

        GeneSymbolDAOImpl geneSymbolDAO = new GeneSymbolDAOImpl();
        geneSymbolDAO.setEntityManager(em);
        hearsayDAOBean.setGeneSymbolDAO(geneSymbolDAO);

        IdentifierDAOImpl identifierDAO = new IdentifierDAOImpl();
        identifierDAO.setEntityManager(em);
        hearsayDAOBean.setIdentifierDAO(identifierDAO);

        ReferenceSequenceDAOImpl referenceSequenceDAO = new ReferenceSequenceDAOImpl();
        referenceSequenceDAO.setEntityManager(em);
        hearsayDAOBean.setReferenceSequenceDAO(referenceSequenceDAO);

        GenomeReferenceDAOImpl genomeReferenceDAO = new GenomeReferenceDAOImpl();
        genomeReferenceDAO.setEntityManager(em);
        hearsayDAOBean.setGenomeReferenceDAO(genomeReferenceDAO);

    }

    @Test
    public void run() {
        logger.debug("ENTERING run()");

        String genomeReferenceVersion = null;

        File readmeCurrentReleaseFile = downloadReadmeCurrentRelease();
        List<String> currentReleaseLineList = new ArrayList<String>();
        try {
            currentReleaseLineList.addAll(FileUtils.readLines(readmeCurrentReleaseFile));
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        for (String line : currentReleaseLineList) {
            if (line.startsWith("ASSEMBLY NAME:")) {
                genomeReferenceVersion = line.substring(14, line.length()).trim();
                break;
            }
        }
        Set<String> refseqAccessionVersionSet = new HashSet<String>();
        File downloadChromosomeAccessionsFile = downloadChromosomeAccessions(genomeReferenceVersion);
        List<String> chromosomeAccessionList = new ArrayList<String>();
        try {
            chromosomeAccessionList.addAll(FileUtils.readLines(downloadChromosomeAccessionsFile));
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        for (String line : chromosomeAccessionList) {
            if (line.startsWith("#")) {
                continue;
            }
            try (Scanner scanner = new Scanner(line).useDelimiter("\t")) {
                scanner.next();
                String refseqAccessionVersion = scanner.next();
                refseqAccessionVersionSet.add(refseqAccessionVersion);
            }
        }

        File genomeReferenceMappingFile = downloadGenomeReference2ReferenceSequenceMapping();

        GFF3Manager gff3Mgr = GFF3Manager.getInstance(genomeReferenceMappingFile);

        File scaffoldNamesFile = downloadScaffoldNames();
        List<String> scaffoldNamesFileLines = new ArrayList<String>();
        try {
            scaffoldNamesFileLines.addAll(FileUtils.readLines(scaffoldNamesFile));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        List<String> accessionPrefixList = Arrays.asList(new String[] { "NM_", "NR_" });

        GBFFManager gbffMgr = GBFFManager.getInstance();

        List<Filter> filters = Arrays.asList(new Filter[] { new SequenceAccessionPrefixFilter(accessionPrefixList),
                new SourceOrganismNameFilter("Homo sapiens"), new FeatureSourceOrganismNameFilter("Homo sapiens"),
                new FeatureTypeNameFilter("CDS"), new FeatureTypeNameFilter("source") });

        List<File> vertebrateMammalianFileList = downloadVertebrateMammalianFiles();

        List<Sequence> sequenceList = new ArrayList<Sequence>();
        sequenceList.addAll(gbffMgr.deserialize(new AndFilter(filters),
                vertebrateMammalianFileList.toArray(new File[vertebrateMammalianFileList.size()])));
        // while (outputFileListIter.hasNext()) {
        // File f = outputFileListIter.next();
        // logger.info("adding sequences from {}", f.getName());
        // sequenceList.addAll(gbffMgr.deserialize(new AndFilter(filters), f));
        // // outputFileListIter.remove();
        // // f.delete();
        // }

        if (sequenceList != null && !sequenceList.isEmpty()) {
            logger.info("sequenceList.size(): {}", sequenceList.size());
            for (Sequence sequence : sequenceList) {

                String sequenceAccession = sequence.getAccession().contains(" ") ? sequence.getAccession().substring(0,
                        sequence.getAccession().indexOf(" ")) : sequence.getAccession();

                List<GFF3Record> gff3RecordList = gff3Mgr.deserialize(new AttributeValueFilter("Target",
                        sequenceAccession));

                try {
                    ReferenceSequence referenceSequence = new ReferenceSequence();

                    Identifier identifier = new Identifier("www.ncbi.nlm.nih.gov/nuccore", sequence.getAccession());
                    List<Identifier> possibleIdentifiers = hearsayDAOBean.getIdentifierDAO().findByExample(identifier);
                    if (possibleIdentifiers != null && !possibleIdentifiers.isEmpty()) {
                        identifier = possibleIdentifiers.get(0);
                    } else {
                        em.getTransaction().begin();
                        identifier.setId(hearsayDAOBean.getIdentifierDAO().save(identifier));
                        em.getTransaction().commit();
                    }
                    logger.info(identifier.toString());
                    referenceSequence.getIdentifiers().add(identifier);

                    for (Feature feature : sequence.getFeatures()) {

                        if (referenceSequence.getType() != null && referenceSequence.getChromosomeType() != null) {
                            break;
                        }

                        if ("source".equals(feature.getType()) && feature.getQualifiers().containsKey("organism")) {
                            ReferenceSequenceChromosomeType chromosomeType = null;
                            for (ReferenceSequenceChromosomeType rsct : ReferenceSequenceChromosomeType.values()) {
                                if (rsct.getValue().equals(feature.getQualifiers().getProperty("chromosome"))) {
                                    chromosomeType = rsct;
                                    break;
                                }
                            }
                            referenceSequence.setChromosomeType(chromosomeType);
                        }

                        if ("CDS".equals(feature.getType()) && referenceSequence.getType() == null) {
                            // we have a ReferenceSequence of a Transcript type
                            referenceSequence.setType(ReferenceSequenceType.TRANSCRIPT);
                            List<Gene> geneList = hearsayDAOBean.getGeneDAO().findBySymbol(
                                    feature.getQualifiers().getProperty("gene"));
                            if (geneList != null && !geneList.isEmpty()) {
                                referenceSequence.setGene(geneList.get(0));
                            }
                            String location = feature.getLocation();

                            referenceSequence
                                    .setCdsStart(Integer.valueOf(location.substring(0, location.indexOf(".."))));
                            referenceSequence.setCdsEnd(Integer.valueOf(location.substring(location.indexOf("..") + 2,
                                    location.length())));
                        }

                    }

                    em.getTransaction().begin();
                    referenceSequence.setId(hearsayDAOBean.getReferenceSequenceDAO().save(referenceSequence));
                    em.getTransaction().commit();

                    Set<String> refSeqAccessionSet = new HashSet<String>();
                    for (GFF3Record record : gff3RecordList) {
                        logger.info(record.toString());
                        String refSeqAccession = record.getSequenceId();
                        refSeqAccessionSet.add(refSeqAccession);
                    }

                    GenomeReference exampleGenomeReference = new GenomeReference();

                    // TODO figure out how to handle multiple refseqAccession mappings
                    String refSeqAccession = refSeqAccessionSet.iterator().next();

                    if (refSeqAccession.startsWith("NC_") && refseqAccessionVersionSet.contains(refSeqAccession)) {
                        exampleGenomeReference.setName(genomeReferenceVersion);
                    } else {

                        for (String line : scaffoldNamesFileLines) {
                            if (line.contains(refSeqAccession)) {
                                try (Scanner scanner = new Scanner(line).useDelimiter("\t")) {
                                    String genomeRefVersion = scanner.next();
                                    exampleGenomeReference.setName(genomeRefVersion);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }

                    }

                    List<GenomeReference> potentialGenomeReferences = hearsayDAOBean.getGenomeReferenceDAO()
                            .findByExample(exampleGenomeReference);

                    if (potentialGenomeReferences == null
                            || (potentialGenomeReferences != null && potentialGenomeReferences.isEmpty())) {
                        logger.warn("NO GenomeReference found - RefSeq: {}", refSeqAccession);
                    }

                    if (potentialGenomeReferences != null && !potentialGenomeReferences.isEmpty()) {
                        referenceSequence.setGenomeReference(potentialGenomeReferences.get(0));
                    }

                    if (referenceSequence.getGenomeReference() != null) {
                        logger.info(referenceSequence.getGenomeReference().toString());
                    } else {
                        logger.warn("GenomeReference is null");
                    }

                    em.getTransaction().begin();
                    hearsayDAOBean.getReferenceSequenceDAO().save(referenceSequence);
                    em.getTransaction().commit();

                    logger.info(referenceSequence.toString());
                } catch (NumberFormatException | HearsayDAOException e) {
                    e.printStackTrace();
                }

            }

        }

        logger.debug("FINISHED run()");
    }

    @Test
    public List<File> downloadVertebrateMammalianFiles() {
        List<File> ret = new ArrayList<File>();
        File tmpDir = new File("/tmp");
        for (File f : tmpDir.listFiles()) {
            if (f.getName().startsWith("vertebrate_mammalian")) {
                ret.add(f);
            }
        }
        return ret;
    }

    public File downloadGenomeReference2ReferenceSequenceMapping() {
        return new File("/tmp", "GCF_000001405.28_knownrefseq_alignments.gff3");
        // return NCBIFTPUtil.download("/refseq/H_sapiens/alignments", "GCF_000001405.28_knownrefseq_alignments.gff3");
    }

    public File downloadScaffoldNames() {
        return new File("/tmp", "scaffold_names");
        // return NCBIFTPUtil.download("/genomes/H_sapiens", "scaffold_names");
    }

    public File downloadReadmeCurrentRelease() {
        return new File("/tmp", "README_CURRENT_RELEASE");
        // return NCBIFTPUtil.download("/genomes/H_sapiens", "README_CURRENT_RELEASE");
    }

    public File downloadChromosomeAccessions(String genomeReferenceVersion) {
        return new File("/tmp", String.format("chr_accessions_%s", genomeReferenceVersion));
        // return NCBIFTPUtil.download("/genomes/H_sapiens/Assembled_chromosomes",
        // String.format("chr_accessions_%s", genomeReferenceVersion));
    }

    @AfterClass
    public static void tearDown() {
        em.close();
        emf.close();
    }

}
