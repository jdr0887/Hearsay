package org.renci.hearsay.ws.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.renci.hearsay.dao.AlignmentDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Alignment;
import org.renci.hearsay.dao.model.Region;
import org.renci.hearsay.ws.AlignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlignmentServiceImpl implements AlignmentService {

    private final Logger logger = LoggerFactory.getLogger(AlignmentServiceImpl.class);

    private AlignmentDAO alignmentDAO;

    public AlignmentServiceImpl() {
        super();
    }

    @Override
    public Alignment findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        Alignment ret = null;
        try {
            ret = alignmentDAO.findById(id);
            if (ret != null && CollectionUtils.isNotEmpty(ret.getRegions())) {
                Collections.sort(ret.getRegions(), new Comparator<Region>() {
                    @Override
                    public int compare(Region o1, Region o2) {
                        return o1.getRegionLocation().getStart().compareTo(o2.getRegionLocation().getStart());
                    }
                });
            }
        } catch (HearsayDAOException e) {
            logger.error("Error", e);
        }
        return ret;
    }

    @Override
    public List<Alignment> findByReferenceSequenceId(Long referenceSequenceId) throws HearsayDAOException {
        logger.debug("ENTERING findByReferenceSequenceId(Long)");
        List<Alignment> ret = new ArrayList<Alignment>();
        try {
            ret.addAll(alignmentDAO.findByReferenceSequenceId(referenceSequenceId));
            if (CollectionUtils.isNotEmpty(ret)) {
                for (Alignment alignment : ret) {
                    if (CollectionUtils.isNotEmpty(alignment.getRegions())) {
                        Collections.sort(alignment.getRegions(), new Comparator<Region>() {
                            @Override
                            public int compare(Region o1, Region o2) {
                                return o1.getRegionLocation().getStart().compareTo(o2.getRegionLocation().getStart());
                            }
                        });
                    }
                }
            }
        } catch (HearsayDAOException e) {
            logger.error("Error", e);
        }
        return ret;
    }

    public AlignmentDAO getAlignmentDAO() {
        return alignmentDAO;
    }

    public void setAlignmentDAO(AlignmentDAO alignmentDAO) {
        this.alignmentDAO = alignmentDAO;
    }

}
