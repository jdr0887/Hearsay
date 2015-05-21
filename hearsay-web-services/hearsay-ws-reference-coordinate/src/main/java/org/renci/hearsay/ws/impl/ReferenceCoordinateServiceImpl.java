package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.ReferenceCoordinateDAO;
import org.renci.hearsay.dao.model.ReferenceCoordinate;
import org.renci.hearsay.ws.ReferenceCoordinateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReferenceCoordinateServiceImpl implements ReferenceCoordinateService {

    private final Logger logger = LoggerFactory.getLogger(ReferenceCoordinateServiceImpl.class);

    private ReferenceCoordinateDAO referenceCoordinateDAO;

    public ReferenceCoordinateServiceImpl() {
        super();
    }

    @Override
    public ReferenceCoordinate findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        ReferenceCoordinate ret = null;
        try {
            ret = referenceCoordinateDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ReferenceCoordinateDAO getReferenceCoordinateDAO() {
        return referenceCoordinateDAO;
    }

    public void setReferenceCoordinateDAO(ReferenceCoordinateDAO referenceCoordinateDAO) {
        this.referenceCoordinateDAO = referenceCoordinateDAO;
    }

}
