package org.renci.hearsay.dao.jpa;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.Range;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Alignment;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.Identifier;
import org.renci.hearsay.dao.model.Location;
import org.renci.hearsay.dao.model.ReferenceSequence;
import org.renci.hearsay.dao.model.Region;
import org.renci.hearsay.dao.model.RegionType;
import org.renci.hearsay.dao.model.StrandType;

public class AlignmentTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    @BeforeClass
    public static void setup() {
        emf = Persistence.createEntityManagerFactory("test-hearsay", null);
        em = emf.createEntityManager();
    }

    @AfterClass
    public static void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    public void nullRegions() {

        ReferenceSequenceDAOImpl referenceSequenceDAO = new ReferenceSequenceDAOImpl();
        referenceSequenceDAO.setEntityManager(em);

        AlignmentDAOImpl alignmentDAO = new AlignmentDAOImpl();
        alignmentDAO.setEntityManager(em);

        List<Long> referenceSequenceIdList = Arrays.asList(93854L);

        try {
            for (Long referenceSequenceId : referenceSequenceIdList) {

                ReferenceSequence referenceSequence = referenceSequenceDAO.findById(referenceSequenceId);

                List<Alignment> alignmentList = alignmentDAO.findByReferenceSequenceId(referenceSequence.getId());

                if (CollectionUtils.isNotEmpty(alignmentList)) {

                    for (Alignment alignment : alignmentList) {
                        List<Region> regionList = alignment.getRegions();
                        if (CollectionUtils.isEmpty(regionList)) {
                            for (Region region : regionList) {
                                assertTrue(region.getRegionLocation() == null);
                            }
                        }

                    }
                }
            }
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAll() {

        AlignmentDAOImpl alignmentDAO = new AlignmentDAOImpl();
        alignmentDAO.setEntityManager(em);

        try {
            List<Alignment> alignmentList = alignmentDAO.findAll();
            assertTrue(alignmentList.size() > 0);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void findByReferenceSequenceId() {

        ReferenceSequenceDAOImpl referenceSequenceDAO = new ReferenceSequenceDAOImpl();
        referenceSequenceDAO.setEntityManager(em);

        AlignmentDAOImpl alignmentDAO = new AlignmentDAOImpl();
        alignmentDAO.setEntityManager(em);

        try {
            List<ReferenceSequence> foundReferenceSequences = referenceSequenceDAO
                    .findByIdentifierSystemAndValue("www.ncbi.nlm.nih.gov/nuccore", "NM_001014794.2");
            assertTrue(CollectionUtils.isNotEmpty(foundReferenceSequences));
            List<Alignment> alignmentList = alignmentDAO.findByReferenceSequenceId(foundReferenceSequences.get(0).getId());
            assertTrue(CollectionUtils.isNotEmpty(alignmentList));
            List<Region> regions = alignmentList.get(0).getRegions();
            assertTrue(CollectionUtils.isNotEmpty(regions));
            regions.sort((a, b) -> {
                return a.getRegionLocation().getStart().compareTo(b.getRegionLocation().getStart());
            });
            regions.forEach(a -> System.out.println(a.getRegionLocation().toString()));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void findNonContigousRegions() {

        GeneDAOImpl geneDAO = new GeneDAOImpl();
        geneDAO.setEntityManager(em);

        ReferenceSequenceDAOImpl referenceSequenceDAO = new ReferenceSequenceDAOImpl();
        referenceSequenceDAO.setEntityManager(em);

        AlignmentDAOImpl alignmentDAO = new AlignmentDAOImpl();
        alignmentDAO.setEntityManager(em);

        try {
            List<Gene> geneList = geneDAO.findAll();
            if (CollectionUtils.isNotEmpty(geneList)) {
                for (Gene gene : geneList) {
                    List<ReferenceSequence> referenceSequenceList = referenceSequenceDAO.findByGeneId(gene.getId());
                    if (CollectionUtils.isNotEmpty(referenceSequenceList)) {
                        for (ReferenceSequence referenceSequence : referenceSequenceList) {

                            List<Alignment> alignmentList = alignmentDAO.findByReferenceSequenceId(referenceSequence.getId());

                            if (CollectionUtils.isNotEmpty(alignmentList)) {

                                a: for (Alignment alignment : alignmentList) {
                                    List<Region> regionList = alignment.getRegions();
                                    if (CollectionUtils.isEmpty(regionList)) {

                                        Region previousRegion = null;

                                        Region lastRegion = regionList.get(regionList.size() - 1);

                                        for (Region currentRegion : regionList) {

                                            if (currentRegion.equals(lastRegion)) {
                                                break;
                                            }

                                            if (previousRegion == null) {
                                                previousRegion = currentRegion;
                                                continue;
                                            }
                                            Location previousRegionLocation = previousRegion.getRegionLocation();

                                            if (previousRegionLocation == null) {
                                                continue;
                                            }

                                            Location currentRegionLocation = currentRegion.getRegionLocation();

                                            if (currentRegionLocation == null) {
                                                continue;
                                            }

                                            if (!previousRegionLocation.getStop().equals(currentRegionLocation.getStart() - 1)) {
                                                System.out.printf("%s: %s%n", referenceSequence.toString(), alignment.toString());
                                                continue a;
                                            }

                                        }

                                    }

                                }
                            }

                        }
                    }

                }
            }

        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fixRegionsWithNoGenomicLocation() {
        File alignmentsFile = new File("/tmp", "GCF_000001405.28_knownrefseq_alignments.gff3");
        LinkedList<String> alignmentsLines = new LinkedList<String>();
        try (FileInputStream fis = new FileInputStream(alignmentsFile);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                alignmentsLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ReferenceSequenceDAOImpl referenceSequenceDAO = new ReferenceSequenceDAOImpl();
        referenceSequenceDAO.setEntityManager(em);

        AlignmentDAOImpl alignmentDAO = new AlignmentDAOImpl();
        alignmentDAO.setEntityManager(em);

        GeneDAOImpl geneDAO = new GeneDAOImpl();
        geneDAO.setEntityManager(em);

        LocationDAOImpl locationDAO = new LocationDAOImpl();
        locationDAO.setEntityManager(em);

        RegionDAOImpl regionDAO = new RegionDAOImpl();
        regionDAO.setEntityManager(em);

        try {

            List<Long> referenceSequenceIdList = Arrays.asList(93854L, 94004L, 77351L, 81574L, 77866L, 70161L, 70163L, 82238L, 82243L,
                    70162L, 82240L, 82244L, 82242L, 82245L, 82239L, 82241L, 87211L, 77350L, 91687L, 91685L, 91686L, 91684L, 89010L, 64975L,
                    64974L, 85889L, 64971L, 64972L, 63901L, 88734L, 88736L, 61196L, 88735L, 75794L, 86093L, 86095L, 86094L, 86092L, 86091L,
                    93836L, 86097L, 65884L);

            for (Long referenceSequenceId : referenceSequenceIdList) {

                ReferenceSequence referenceSequence = referenceSequenceDAO.findById(referenceSequenceId);

                StrandType strandType = referenceSequence.getStrandType();

                String refSeqGenomicAccession = null;
                String refSeqVersionedAccession = null;
                if (CollectionUtils.isEmpty(referenceSequence.getIdentifiers())) {
                    System.out.println("refseq has no identifiers");
                    return;
                }
                for (Identifier identifier : referenceSequence.getIdentifiers()) {
                    if (identifier.getSystem().equals("www.ncbi.nlm.nih.gov/genome")) {
                        refSeqGenomicAccession = identifier.getValue();
                    }
                    if (identifier.getSystem().equals("www.ncbi.nlm.nih.gov/nuccore")) {
                        refSeqVersionedAccession = identifier.getValue();
                    }
                }

                List<Alignment> alignmentList = alignmentDAO.findByReferenceSequenceId(referenceSequence.getId());

                if (CollectionUtils.isNotEmpty(alignmentList)) {

                    for (Alignment alignment : alignmentList) {

                        Location proteinLocation = alignment.getProteinLocation();

                        List<Region> regionList = alignment.getRegions();

                        if (CollectionUtils.isNotEmpty(regionList)) {

                            for (Region region : regionList) {
                                Location transcriptLocation = region.getTranscriptLocation();
                                if (transcriptLocation == null) {
                                    continue;
                                }

                                Location regionLocation = region.getRegionLocation();

                                if (regionLocation != null) {
                                    continue;
                                }

                                for (String line : alignmentsLines) {

                                    if (line.startsWith("#")) {
                                        continue;
                                    }

                                    String[] columns = line.split("\t");
                                    String genomicAccession = columns[0];
                                    String genomicStart = columns[3];
                                    String genomicStop = columns[4];
                                    String strand = columns[6];
                                    String attributes = columns[8];

                                    if (!genomicAccession.substring(0, genomicAccession.indexOf("."))
                                            .equals(refSeqGenomicAccession.substring(0, refSeqGenomicAccession.indexOf(".")))) {
                                        continue;
                                    }

                                    if (attributes.contains(refSeqVersionedAccession)) {

                                        String[] attributeSplit = attributes.split(";");
                                        String[] targetSplit = attributeSplit[1].split(" ");
                                        Integer start = Integer.valueOf(targetSplit[1]);
                                        Integer stop = Integer.valueOf(targetSplit[2]);

                                        if (strandType.equals(StrandType.MINUS)) {
                                            System.out.printf("genomic: (%s, %s), transcript: (%s, %s)%n", start.toString(),
                                                    stop.toString(), transcriptLocation.getStop().toString(),
                                                    transcriptLocation.getStart().toString());
                                            if (transcriptLocation.getStart().equals(stop) && transcriptLocation.getStop().equals(start)) {
                                                Location genomicLocation = new Location(Integer.valueOf(genomicStart),
                                                        Integer.valueOf(genomicStop));
                                                em.getTransaction().begin();
                                                genomicLocation.setId(locationDAO.save(genomicLocation));
                                                em.getTransaction().commit();
                                                region.setRegionLocation(genomicLocation);
                                                em.getTransaction().begin();
                                                regionDAO.save(region);
                                                em.getTransaction().commit();
                                            }
                                        } else {
                                            System.out.printf("genomic: (%s, %s), transcript: (%s, %s)%n", start.toString(),
                                                    stop.toString(), transcriptLocation.getStart().toString(),
                                                    transcriptLocation.getStop().toString());
                                            if (transcriptLocation.getStart().equals(start) && transcriptLocation.getStop().equals(stop)) {
                                                Location genomicLocation = new Location(Integer.valueOf(genomicStart),
                                                        Integer.valueOf(genomicStop));
                                                genomicLocation.setId(locationDAO.save(genomicLocation));
                                                em.getTransaction().begin();
                                                genomicLocation.setId(locationDAO.save(genomicLocation));
                                                em.getTransaction().commit();
                                                region.setRegionLocation(genomicLocation);
                                                em.getTransaction().begin();
                                                regionDAO.save(region);
                                                em.getTransaction().commit();
                                            }
                                        }

                                    }

                                }

                            }
                        }
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReferenceSequenceRetrieval() {

        IdentifierDAOImpl identifierDAO = new IdentifierDAOImpl();
        identifierDAO.setEntityManager(em);

        ReferenceSequenceDAOImpl referenceSequenceDAO = new ReferenceSequenceDAOImpl();
        referenceSequenceDAO.setEntityManager(em);

        Map<ReferenceSequence, Integer> metrics = new HashMap<ReferenceSequence, Integer>();
        try {
            List<String> lines = FileUtils.readLines(new File("/tmp", "refseq-protein.txt"));

            for (String line : lines) {
                String[] split = line.split("\\|");
                String proteinId = split[0];
                String refSeqVersionedAccession = split[1];

                List<Identifier> identifierList = new ArrayList<Identifier>();

                // find by rna nucleotide accession
                List<Identifier> rnaNucleotideAccessionIdentifierList = identifierDAO
                        .findByExample(new Identifier("www.ncbi.nlm.nih.gov/nuccore", refSeqVersionedAccession));
                if (CollectionUtils.isNotEmpty(rnaNucleotideAccessionIdentifierList)) {
                    identifierList.add(rnaNucleotideAccessionIdentifierList.get(0));
                }

                // find by protein id
                List<Identifier> proteinAccessionIdentifierList = identifierDAO
                        .findByExample(new Identifier("www.ncbi.nlm.nih.gov/protein", proteinId));
                if (CollectionUtils.isNotEmpty(proteinAccessionIdentifierList)) {
                    identifierList.add(proteinAccessionIdentifierList.get(0));
                }

                if (identifierList.size() != 2) {
                    continue;
                }

                List<ReferenceSequence> potentialRefSeqs = referenceSequenceDAO.findByIdentifiers(identifierList);

                if (CollectionUtils.isEmpty(potentialRefSeqs)) {
                    System.out.println("Could not find ReferenceSequence");
                } else {
                    ReferenceSequence referenceSequence = potentialRefSeqs.get(0);
                    if (!metrics.containsKey(referenceSequence)) {
                        metrics.put(referenceSequence, 1);
                    } else {
                        Integer value = metrics.get(referenceSequence);
                        metrics.put(referenceSequence, value + 1);
                    }

                    System.out.printf("identifierList.size(): %s%n", identifierList.size());
                    System.out.printf("proteinId: %s%n", proteinId);
                    System.out.printf("refSeqVersionedAccession: %s%n", refSeqVersionedAccession);
                    System.out.printf("potentialRefSeqs.size(): %s%n", potentialRefSeqs.size());
                    System.out.println(referenceSequence.toString());
                }

            }

            for (ReferenceSequence referenceSequence : metrics.keySet()) {
                System.out.printf("%s:%d%n", referenceSequence, metrics.get(referenceSequence));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void createIntrons() {

        ReferenceSequenceDAOImpl referenceSequenceDAO = new ReferenceSequenceDAOImpl();
        referenceSequenceDAO.setEntityManager(em);

        AlignmentDAOImpl alignmentDAO = new AlignmentDAOImpl();
        alignmentDAO.setEntityManager(em);

        try {

            // minus strand example
            // ReferenceSequence referenceSequence = referenceSequenceDAO.findById(94696L);

            // minus strand example with entire protein in one exon range
            ReferenceSequence referenceSequence = referenceSequenceDAO.findById(80781L);

            // plus strand example
            // ReferenceSequence referenceSequence = referenceSequenceDAO.findById(75712L);

            // plus strand example with entire protein in one exon range
            // ReferenceSequence referenceSequence = referenceSequenceDAO.findById(87545L);

            StrandType strandType = referenceSequence.getStrandType();
            System.out.println(strandType.toString());

            for (Alignment alignment : referenceSequence.getAlignments()) {
                Location proteinLocation = alignment.getProteinLocation();
                System.out.printf("Protein: %s%n", proteinLocation.toString());

                List<Region> regionList = alignment.getRegions();
                List<Region> utrRegionList = new ArrayList<Region>();

                printRegions(regionList);

                if (strandType.equals(StrandType.MINUS)) {

                    for (Region region : regionList) {
                        Location transcriptLocation = region.getTranscriptLocation();
                        if (transcriptLocation == null) {
                            continue;
                        }
                        int transcriptStart = transcriptLocation.getStart();
                        int transcriptStop = transcriptLocation.getStop();
                        Range<Integer> transcriptRange = transcriptLocation.toRange();

                        Location regionLocation = region.getRegionLocation();
                        int regionStart = regionLocation.getStart();
                        int regionStop = regionLocation.getStop();

                        if (transcriptRange.contains(proteinLocation.getStart()) && transcriptRange.contains(proteinLocation.getStop())) {

                            transcriptLocation.setStop(proteinLocation.getStart() - 1);
                            regionLocation.setStop(regionStart + transcriptLocation.diff());

                            Region newRegion = createRegion(alignment, proteinLocation.getStart(), proteinLocation.getStop(),
                                    regionLocation.getStop() + 1,
                                    regionLocation.getStop() + 1 + (proteinLocation.getStop() - proteinLocation.getStart()));
                            utrRegionList.add(newRegion);

                            newRegion = createRegion(alignment, proteinLocation.getStop() + 1, transcriptStop,
                                    newRegion.getRegionLocation().getStop() + 1, regionStop);
                            utrRegionList.add(newRegion);

                        } else if (transcriptRange.contains(proteinLocation.getStart())) {

                            transcriptLocation.setStop(proteinLocation.getStart() - 1);
                            regionLocation.setStart(regionStop - transcriptLocation.diff());

                            Region newRegion = createRegion(alignment, proteinLocation.getStart(), transcriptStop,
                                    regionLocation.getStart() - 1 - (transcriptStop - proteinLocation.getStart()),
                                    regionLocation.getStart() - 1);
                            utrRegionList.add(newRegion);

                        } else if (transcriptRange.contains(proteinLocation.getStop())) {

                            transcriptLocation.setStart(proteinLocation.getStop() + 1);
                            regionLocation.setStop(regionStart + transcriptLocation.diff());

                            Region newRegion = createRegion(alignment, transcriptStart, proteinLocation.getStop(),
                                    regionLocation.getStop() + 1,
                                    regionLocation.getStop() + 1 + (proteinLocation.getStop() - transcriptStart));
                            utrRegionList.add(newRegion);

                        }

                    }

                } else {
                    for (Region region : regionList) {
                        Location transcriptLocation = region.getTranscriptLocation();
                        if (transcriptLocation == null) {
                            continue;
                        }
                        int transcriptStart = transcriptLocation.getStart();
                        int transcriptStop = transcriptLocation.getStop();
                        Range<Integer> transcriptRange = transcriptLocation.toRange();

                        Location regionLocation = region.getRegionLocation();
                        int regionStart = regionLocation.getStart();
                        int regionStop = regionLocation.getStop();

                        if (transcriptRange.contains(proteinLocation.getStart()) && transcriptRange.contains(proteinLocation.getStop())) {

                            transcriptLocation.setStop(proteinLocation.getStart() - 1);
                            regionLocation.setStop(regionStart + transcriptLocation.diff());

                            Region newRegion = createRegion(alignment, proteinLocation.getStart(), proteinLocation.getStop(),
                                    regionLocation.getStop() + 1,
                                    regionLocation.getStop() + 1 + (proteinLocation.getStop() - proteinLocation.getStart()));
                            utrRegionList.add(newRegion);

                            newRegion = createRegion(alignment, proteinLocation.getStop() + 1, transcriptStop,
                                    newRegion.getRegionLocation().getStop() + 1,
                                    newRegion.getRegionLocation().getStop() + 1 + (transcriptStop - proteinLocation.getStop() - 1));
                            utrRegionList.add(newRegion);

                        } else if (transcriptRange.contains(proteinLocation.getStart())) {

                            transcriptLocation.setStop(proteinLocation.getStart() - 1);
                            regionLocation.setStop(regionStart + transcriptLocation.diff());

                            Region newRegion = createRegion(alignment, proteinLocation.getStart(), transcriptStop,
                                    regionLocation.getStop() + 1,
                                    regionLocation.getStop() + 1 + (transcriptStop - proteinLocation.getStart()));
                            utrRegionList.add(newRegion);

                        } else if (transcriptRange.contains(proteinLocation.getStop())) {

                            transcriptLocation.setStart(proteinLocation.getStop() + 1);
                            regionLocation.setStart(regionStop - transcriptLocation.diff());

                            Region newRegion = createRegion(alignment, transcriptStart, proteinLocation.getStop(),
                                    regionLocation.getStart() - 1 - (proteinLocation.getStop() - transcriptStart),
                                    regionLocation.getStart() - 1);
                            utrRegionList.add(newRegion);

                        }

                    }
                }

                regionList.addAll(utrRegionList);

                printRegions(regionList);

                for (Region region : regionList) {

                    if (strandType.equals(StrandType.PLUS) && region.getTranscriptLocation().getStop() < proteinLocation.getStart()) {
                        region.setRegionType(RegionType.UTR5);
                    }

                    if (strandType.equals(StrandType.PLUS) && region.getTranscriptLocation().getStop() > proteinLocation.getStop()) {
                        region.setRegionType(RegionType.UTR3);
                    }

                    if (strandType.equals(StrandType.MINUS) && region.getTranscriptLocation().getStop() < proteinLocation.getStart()) {
                        region.setRegionType(RegionType.UTR5);
                    }

                    if (strandType.equals(StrandType.MINUS) && region.getTranscriptLocation().getStop() > proteinLocation.getStop()) {
                        region.setRegionType(RegionType.UTR3);
                    }

                }

                printRegions(regionList);

                List<Region> intronRegionList = new ArrayList<Region>();

                // adding intron regions

                Region lastRegion = regionList.get(regionList.size() - 1);
                Region previousRegion = null;
                for (Region currentRegion : regionList) {
                    if (previousRegion == null) {
                        previousRegion = currentRegion;
                        continue;
                    }

                    if (previousRegion.getRegionLocation().getStop().equals(currentRegion.getRegionLocation().getStart() - 1)) {
                        previousRegion = currentRegion;
                        continue;
                    }

                    if (currentRegion.equals(lastRegion)) {
                        break;
                    }

                    Region region = new Region(RegionType.INTRON);
                    region.setAlignment(alignment);
                    Location regionLocation = new Location(previousRegion.getRegionLocation().getStop() + 1,
                            currentRegion.getRegionLocation().getStart() - 1);
                    region.setRegionLocation(regionLocation);
                    intronRegionList.add(region);

                    previousRegion = currentRegion;
                }

                regionList.addAll(intronRegionList);

                printRegions(regionList);
            }

        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }

    }

    private Region createRegion(Alignment alignment, Integer transcriptStart, Integer transcriptStop, Integer genomicStart,
            Integer genomicStop) throws HearsayDAOException {
        Region newRegion = new Region(RegionType.EXON);
        newRegion.setAlignment(alignment);

        Location newTranscriptLocation = new Location(transcriptStart, transcriptStop);
        newRegion.setTranscriptLocation(newTranscriptLocation);

        Location newRegionLocation = new Location(genomicStart, genomicStop);
        newRegion.setRegionLocation(newRegionLocation);
        return newRegion;
    }

    private void printRegions(List<Region> regionList) {
        regionList.sort((a, b) -> {
            if (a != null && b != null) {
                return a.getRegionLocation().getStart().compareTo(b.getRegionLocation().getStart());
            }
            return 0;
        });
        System.out.println("-----------");
        for (Region region : regionList) {
            Location regionLocation = region.getRegionLocation();
            Location transcriptLocation = region.getTranscriptLocation();
            if (transcriptLocation == null) {
                System.out.printf("%s, %s%n", region.toString(), regionLocation.toString());
                continue;
            }
            System.out.printf("%s, %s, %s%n", region.toString(), regionLocation.toString(), transcriptLocation.toString());
        }
    }

}
