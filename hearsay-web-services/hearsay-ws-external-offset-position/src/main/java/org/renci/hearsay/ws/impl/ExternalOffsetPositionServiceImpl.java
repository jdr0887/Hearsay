package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.ExternalOffsetPositionDAO;
import org.renci.hearsay.dao.model.ExternalOffsetPosition;
import org.renci.hearsay.ws.ExternalOffsetPositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExternalOffsetPositionServiceImpl implements ExternalOffsetPositionService {

    private static final Logger logger = LoggerFactory.getLogger(ExternalOffsetPositionServiceImpl.class);

    private ExternalOffsetPositionDAO externalOffsetPositionDAO;

    public ExternalOffsetPositionServiceImpl() {
        super();
    }

    @Override
    public ExternalOffsetPosition findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        ExternalOffsetPosition ret = null;
        try {
            ret = externalOffsetPositionDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ExternalOffsetPositionDAO getExternalOffsetPositionDAO() {
        return externalOffsetPositionDAO;
    }

    public void setExternalOffsetPositionDAO(ExternalOffsetPositionDAO externalOffsetPositionDAO) {
        this.externalOffsetPositionDAO = externalOffsetPositionDAO;
    }

}
