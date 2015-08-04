package org.renci.hearsay.dao.jpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.lang.math.IntRange;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Alignment;
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
    public void createIntrons() {

        ReferenceSequenceDAOImpl referenceSequenceDAO = new ReferenceSequenceDAOImpl();
        referenceSequenceDAO.setEntityManager(em);

        AlignmentDAOImpl alignmentDAO = new AlignmentDAOImpl();
        alignmentDAO.setEntityManager(em);

        try {

            // ReferenceSequence referenceSequence = referenceSequenceDAO.findById(55965L);
            // List<Alignment> alignmentList = alignmentDAO.findByReferenceSequenceId(55965L);

            ReferenceSequence referenceSequence = referenceSequenceDAO.findById(55830L);
            List<Alignment> alignmentList = alignmentDAO.findByReferenceSequenceId(55830L);

            StrandType strandType = referenceSequence.getStrandType();
            System.out.println(strandType.toString());

            for (Alignment alignment : alignmentList) {
                Location proteinLocation = alignment.getProteinLocation();
                System.out.printf("Protein: %s%n", proteinLocation.toString());

                List<Region> regionList = alignment.getRegions();
                List<Region> utrRegionList = new ArrayList<Region>();

                printRegions(regionList);

                for (Region region : regionList) {
                    Location transcriptLocation = region.getTranscriptLocation();
                    if (transcriptLocation == null) {
                        continue;
                    }

                    Location regionLocation = region.getRegionLocation();
                    int regionStart = regionLocation.getStart();
                    int regionStop = regionLocation.getStop();

                    IntRange transcriptRange = transcriptLocation.toRange();

                    int transcriptStart = transcriptLocation.getStart();
                    int transcriptStop = transcriptLocation.getStop();

                    if (strandType.equals(StrandType.MINUS)) {

                        if (transcriptRange.containsInteger(alignment.getProteinLocation().getStart())) {

                            transcriptLocation.setStart(alignment.getProteinLocation().getStart() - 1);

                            regionLocation.setStart(regionLocation.getStop() - transcriptLocation.diff());

                            Region newRegion = new Region(RegionType.EXON);
                            newRegion.setAlignment(alignment);

                            Location newTranscriptLocation = new Location(transcriptStart, alignment
                                    .getProteinLocation().getStart());
                            newRegion.setTranscriptLocation(newTranscriptLocation);

                            Location newRegionLocation = new Location(regionLocation.getStart() - 1
                                    - newTranscriptLocation.diff(), regionLocation.getStart() - 1);
                            newRegion.setRegionLocation(newRegionLocation);

                            utrRegionList.add(newRegion);
                        }

                        if (transcriptRange.containsInteger(alignment.getProteinLocation().getStop())) {

                            transcriptLocation.setStart(alignment.getProteinLocation().getStop());

                            regionLocation.setStart(regionLocation.getStop() - transcriptLocation.diff());

                            Region newRegion = new Region(RegionType.EXON);
                            newRegion.setAlignment(alignment);

                            Location newTranscriptLocation = new Location(transcriptStart, alignment
                                    .getProteinLocation().getStop() + 1);
                            newRegion.setTranscriptLocation(newTranscriptLocation);

                            // is this off by one?
                            Location newRegionLocation = new Location(regionLocation.getStart()
                                    - newTranscriptLocation.diff() - 1, regionLocation.getStart() - 1);
                            newRegion.setRegionLocation(newRegionLocation);

                            utrRegionList.add(newRegion);
                        }

                    }

                    if (strandType.equals(StrandType.PLUS)) {

                        if (transcriptRange.containsInteger(alignment.getProteinLocation().getStart())) {

                            transcriptLocation.setStop(alignment.getProteinLocation().getStop() - 1);

                            regionLocation.setStop(regionStart + transcriptLocation.diff());

                            Region newRegion = new Region(RegionType.EXON);
                            newRegion.setAlignment(alignment);

                            Location newTranscriptLocation = new Location(alignment.getProteinLocation().getStart(),
                                    transcriptStop);
                            newRegion.setTranscriptLocation(newTranscriptLocation);

                            Location newRegionLocation = new Location(regionLocation.getStop() + 1,
                                    regionLocation.getStop() + 1 + newTranscriptLocation.diff());
                            newRegion.setRegionLocation(newRegionLocation);

                            utrRegionList.add(newRegion);

                        }

                        if (transcriptRange.containsInteger(alignment.getProteinLocation().getStop())) {

                            transcriptLocation.setStop(alignment.getProteinLocation().getStop());

                            regionLocation.setStop(regionStart + transcriptLocation.diff());

                            Region newRegion = new Region(RegionType.UTR3);
                            newRegion.setAlignment(alignment);

                            Location newTranscriptLocation = new Location(alignment.getProteinLocation().getStop() + 1,
                                    transcriptStop);
                            newRegion.setTranscriptLocation(newTranscriptLocation);

                            Location newRegionLocation = new Location(regionStop - newTranscriptLocation.diff(),
                                    regionStop);
                            newRegion.setRegionLocation(newRegionLocation);

                            utrRegionList.add(newRegion);

                        }

                    }

                }

                regionList.addAll(utrRegionList);

                for (Region region : regionList) {

                    if (strandType.equals(StrandType.PLUS)
                            && region.getTranscriptLocation().getStop() < alignment.getProteinLocation().getStart()) {
                        region.setRegionType(RegionType.UTR5);
                    }

                    if (strandType.equals(StrandType.PLUS)
                            && region.getTranscriptLocation().getStop() > alignment.getProteinLocation().getStop()) {
                        region.setRegionType(RegionType.UTR3);
                    }

                    if (strandType.equals(StrandType.MINUS)
                            && region.getTranscriptLocation().getStop() < alignment.getProteinLocation().getStart()) {
                        region.setRegionType(RegionType.UTR5);
                    }

                    if (strandType.equals(StrandType.MINUS)
                            && region.getTranscriptLocation().getStop() > alignment.getProteinLocation().getStop()) {
                        region.setRegionType(RegionType.UTR3);
                    }

                }

                printRegions(regionList);

                List<Region> intronRegionList = new ArrayList<Region>();

                // adding intron regions
                Region previousRegion = null;
                for (Region currentRegion : regionList) {
                    if (previousRegion == null) {
                        previousRegion = currentRegion;
                        continue;
                    }

                    if (previousRegion.getRegionLocation().getStop()
                            .equals(currentRegion.getRegionLocation().getStart() - 1)) {
                        previousRegion = currentRegion;
                        continue;
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

    private void printRegions(List<Region> regionList) {
        Collections.sort(regionList, new Comparator<Region>() {
            @Override
            public int compare(Region o1, Region o2) {
                return Integer.compare(o1.getRegionLocation().getStart(), o2.getRegionLocation().getStart());
            }
        });
        System.out.println("-----------");
        for (Region region : regionList) {
            Location regionLocation = region.getRegionLocation();
            Location transcriptLocation = region.getTranscriptLocation();
            if (transcriptLocation == null) {
                System.out.printf("%s, %s%n", region.toString(), regionLocation.toString());
                continue;
            }
            System.out.printf("%s, %s, %s%n", region.toString(), regionLocation.toString(),
                    transcriptLocation.toString());
        }
    }

}
