package org.renci.hearsay.dao.rs;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Region;
import org.renci.hearsay.dao.model.TranscriptAlignment;
import org.renci.hearsay.dao.model.TranscriptRefSeq;

public class TranscriptRefSeqTest {

    @Test
    public void testForContiguousRegions() {
        RESTDAOManager daoMgr = RESTDAOManager.getInstance("org/renci/hearsay/dao/rs/hearsay-dao-beans-test.xml");
        try {
            // List<TranscriptRefSeq> transcripts = daoMgr.getHearsayDAOBean().getTranscriptRefSeqDAO()
            // .findByGeneName("DRD3");
            // List<TranscriptRefSeq> transcripts = daoMgr.getHearsayDAOBean().getTranscriptRefSeqDAO()
            // .findByGeneName("BRCA1");
            // List<TranscriptRefSeq> transcripts = daoMgr.getHearsayDAOBean().getTranscriptRefSeqDAO()
            // .findByGeneName("PRKAG2");
            // List<TranscriptRefSeq> transcripts = daoMgr.getHearsayDAOBean().getTranscriptRefSeqDAO()
            // .findByGeneName("PKP2");
//            List<TranscriptRefSeq> transcripts = daoMgr.getHearsayDAOBean().getTranscriptRefSeqDAO()
//                    .findByGeneName("PRSS1");
            List<TranscriptRefSeq> transcripts = daoMgr.getHearsayDAOBean().getTranscriptRefSeqDAO()
                    .findByGeneName("ABCC9");

            if (transcripts != null && !transcripts.isEmpty()) {
                for (TranscriptRefSeq entity : transcripts) {
                    System.out.println(entity.toString());
                    Set<TranscriptAlignment> alignments = entity.getAlignments();
                    if (alignments != null && !alignments.isEmpty()) {
                        for (TranscriptAlignment alignment : alignments) {
                            System.out.println(alignment.toString());
                            Set<Region> regions = alignment.getRegions();
                            List<Region> sortedRegions = new ArrayList<Region>(regions);
                            Collections.sort(sortedRegions, new Comparator<Region>() {
                                @Override
                                public int compare(Region o1, Region o2) {
                                    return o1.getRegionStart().compareTo(o2.getRegionStart());
                                }
                            });

                            if (sortedRegions != null && !sortedRegions.isEmpty()) {
                                int previousRegionStop = 0;
                                for (Region region : sortedRegions) {
                                    System.out.println(region.toString());
                                    if (previousRegionStop != 0) {
                                        assertTrue(previousRegionStop + 1 == region.getRegionStart());
                                    }
                                    previousRegionStop = region.getRegionStop();
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
}
