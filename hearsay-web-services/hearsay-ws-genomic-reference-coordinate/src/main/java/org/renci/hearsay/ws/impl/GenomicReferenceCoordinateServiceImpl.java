package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.GenomicReferenceCoordinateDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GenomicReferenceCoordinate;
import org.renci.hearsay.ws.GenomicReferenceCoordinateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenomicReferenceCoordinateServiceImpl implements GenomicReferenceCoordinateService {

    private final Logger logger = LoggerFactory.getLogger(GenomicReferenceCoordinateServiceImpl.class);

    private GenomicReferenceCoordinateDAO genomicReferenceCoordinateDAO;

    public GenomicReferenceCoordinateServiceImpl() {
        super();
    }

    @Override
    public GenomicReferenceCoordinate findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        GenomicReferenceCoordinate ret = null;
        try {
            ret = genomicReferenceCoordinateDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public GenomicReferenceCoordinateDAO getGenomicReferenceCoordinateDAO() {
        return genomicReferenceCoordinateDAO;
    }

    public void setGenomicReferenceCoordinateDAO(GenomicReferenceCoordinateDAO genomicReferenceCoordinateDAO) {
        this.genomicReferenceCoordinateDAO = genomicReferenceCoordinateDAO;
    }

}
