package org.renci.hearsay.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.io.FileUtils;
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
import org.renci.hearsay.commands.util.NCBIFTPUtil;
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

public class PullReferenceSequencesRunnable implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(PullReferenceSequencesRunnable.class);

    private HearsayDAOBean hearsayDAOBean;

    public PullReferenceSequencesRunnable() {
        super();
    }

    @Override
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

        GBFFManager gbffMgr = GBFFManager.getInstance(1, true);

        List<Filter> filters = Arrays.asList(new Filter[] { new SequenceAccessionPrefixFilter(accessionPrefixList),
                new SourceOrganismNameFilter("Homo sapiens"), new FeatureSourceOrganismNameFilter("Homo sapiens"),
                new FeatureTypeNameFilter("CDS"), new FeatureTypeNameFilter("source") });

        List<File> vertebrateMammalianFileList = downloadVertebrateMammalianFiles();

        for (File f : vertebrateMammalianFileList) {

            List<Sequence> sequenceList = gbffMgr.deserialize(new AndFilter(filters), f);

            if (sequenceList != null && !sequenceList.isEmpty()) {

                logger.info("sequenceList.size(): {}", sequenceList.size());

                for (Sequence sequence : sequenceList) {

                    String sequenceAccession = sequence.getAccession().contains(" ") ? sequence.getAccession()
                            .substring(0, sequence.getAccession().indexOf(" ")) : sequence.getAccession();

                    List<GFF3Record> gff3RecordList = gff3Mgr.deserialize(new AttributeValueFilter("Target",
                            sequenceAccession));
                    logger.info("gff3RecordList.size(): {}", gff3RecordList.size());

                    ReferenceSequence referenceSequence = new ReferenceSequence();

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
                            try {
                                List<Gene> geneList = hearsayDAOBean.getGeneDAO().findBySymbol(
                                        feature.getQualifiers().getProperty("gene"));
                                if (geneList != null && !geneList.isEmpty()) {
                                    referenceSequence.setGene(geneList.get(0));
                                }
                            } catch (HearsayDAOException e) {
                                e.printStackTrace();
                            }
                            String location = feature.getLocation();

                            referenceSequence
                                    .setCdsStart(Integer.valueOf(location.substring(0, location.indexOf(".."))));
                            referenceSequence.setCdsEnd(Integer.valueOf(location.substring(location.indexOf("..") + 2,
                                    location.length())));
                        }

                    }

                    try {
                        referenceSequence.setId(hearsayDAOBean.getReferenceSequenceDAO().save(referenceSequence));
                    } catch (HearsayDAOException e1) {
                        e1.printStackTrace();
                    }
                    logger.info(referenceSequence.toString());

                    Identifier identifier = new Identifier("www.ncbi.nlm.nih.gov/nuccore", sequenceAccession);
                    try {
                        List<Identifier> possibleIdentifiers = hearsayDAOBean.getIdentifierDAO().findByExample(
                                identifier);
                        if (possibleIdentifiers != null && !possibleIdentifiers.isEmpty()) {
                            identifier = possibleIdentifiers.get(0);
                        } else {
                            identifier.setId(hearsayDAOBean.getIdentifierDAO().save(identifier));
                        }
                    } catch (HearsayDAOException e1) {
                        e1.printStackTrace();
                    }
                    logger.info(identifier.toString());
                    referenceSequence.getIdentifiers().add(identifier);

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

                    try {
                        List<GenomeReference> potentialGenomeReferences = hearsayDAOBean.getGenomeReferenceDAO()
                                .findByExample(exampleGenomeReference);

                        if (potentialGenomeReferences == null
                                || (potentialGenomeReferences != null && potentialGenomeReferences.isEmpty())) {
                            logger.warn("NO GenomeReference found - RefSeq: {}", refSeqAccession);
                        }

                        if (potentialGenomeReferences != null && !potentialGenomeReferences.isEmpty()) {
                            referenceSequence.setGenomeReference(potentialGenomeReferences.get(0));
                        }
                    } catch (HearsayDAOException e) {
                        e.printStackTrace();
                    }

                    if (referenceSequence.getGenomeReference() != null) {
                        logger.info(referenceSequence.getGenomeReference().toString());
                    } else {
                        logger.warn("GenomeReference is null");
                    }

                    try {
                        hearsayDAOBean.getReferenceSequenceDAO().save(referenceSequence);
                        logger.info(referenceSequence.toString());
                    } catch (HearsayDAOException e) {
                        e.printStackTrace();
                    }

                }

            }

        }

        logger.debug("FINISHED run()");
    }

    public List<File> downloadVertebrateMammalianFiles() {

        List<File> ret = new ArrayList<File>();

        File tmpDir = new File("/tmp");
        for (File f : tmpDir.listFiles()) {
            if (f.getName().startsWith("vertebrate_mammalian")) {
                ret.add(f);
            }
        }

        // FTPClient ftpClient = new FTPClient();
        // try {
        // ftpClient.connect("ftp.ncbi.nlm.nih.gov");
        //
        // ftpClient.login("anonymous", "anonymous");
        // ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        // ftpClient.enterLocalPassiveMode();
        //
        // int reply = ftpClient.getReplyCode();
        // if (!FTPReply.isPositiveCompletion(reply)) {
        // ftpClient.disconnect();
        // logger.error("FTP server refused connection.");
        // return null;
        // }
        //
        // List<FTPFile> ftpFileList = Arrays.asList(ftpClient.listFiles("/refseq/release/vertebrate_mammalian/",
        // new FTPFileFilter() {
        //
        // @Override
        // public boolean accept(FTPFile ftpFile) {
        // if (ftpFile != null && ftpFile.getName().endsWith("rna.gbff.gz")) {
        // return true;
        // }
        // return false;
        // }
        // }));
        //
        // for (FTPFile ftpFile : ftpFileList) {
        // File tmpFile = new File(System.getProperty("java.io.tmpdir", "/tmp"), ftpFile.getName());
        // try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(tmpFile))) {
        // ftpClient.retrieveFile(String.format("/refseq/release/vertebrate_mammalian/%s", ftpFile.getName()),
        // fos);
        // fos.flush();
        // }
        // ret.add(tmpFile);
        // }
        //
        // } catch (IOException e) {
        // e.printStackTrace();
        // } finally {
        // try {
        // if (ftpClient.isConnected()) {
        // ftpClient.disconnect();
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
        return ret;
    }

    public File downloadGenomeReference2ReferenceSequenceMapping() {
        return NCBIFTPUtil.download("/refseq/H_sapiens/alignments", "GCF_000001405.28_knownrefseq_alignments.gff3");
    }

    public File downloadScaffoldNames() {
        return NCBIFTPUtil.download("/genomes/H_sapiens", "scaffold_names");
    }

    public File downloadReadmeCurrentRelease() {
        return NCBIFTPUtil.download("/genomes/H_sapiens", "README_CURRENT_RELEASE");
    }

    public File downloadChromosomeAccessions(String genomeReferenceVersion) {
        return NCBIFTPUtil.download("/genomes/H_sapiens/Assembled_chromosomes",
                String.format("chr_accessions_%s", genomeReferenceVersion));
    }

    public HearsayDAOBean getHearsayDAOBean() {
        return hearsayDAOBean;
    }

    public void setHearsayDAOBean(HearsayDAOBean hearsayDAOBean) {
        this.hearsayDAOBean = hearsayDAOBean;
    }

}
