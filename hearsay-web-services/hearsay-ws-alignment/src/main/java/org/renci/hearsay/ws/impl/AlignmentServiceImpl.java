package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.AlignmentDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Alignment;
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
        } catch (HearsayDAOException e) {
            e.printStackTrace();
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
