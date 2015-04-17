package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.AminoAcidReferenceCoordinateDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.AminoAcidReferenceCoordinate;
import org.renci.hearsay.ws.AminoAcidReferenceCoordinateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AminoAcidReferenceCoordinateServiceImpl implements AminoAcidReferenceCoordinateService {

    private final Logger logger = LoggerFactory.getLogger(AminoAcidReferenceCoordinateServiceImpl.class);

    private AminoAcidReferenceCoordinateDAO aminoAcidReferenceCoordinateDAO;

    public AminoAcidReferenceCoordinateServiceImpl() {
        super();
    }

    @Override
    public AminoAcidReferenceCoordinate findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        AminoAcidReferenceCoordinate ret = null;
        try {
            ret = aminoAcidReferenceCoordinateDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public AminoAcidReferenceCoordinateDAO getAminoAcidReferenceCoordinateDAO() {
        return aminoAcidReferenceCoordinateDAO;
    }

    public void setAminoAcidReferenceCoordinateDAO(AminoAcidReferenceCoordinateDAO aminoAcidReferenceCoordinateDAO) {
        this.aminoAcidReferenceCoordinateDAO = aminoAcidReferenceCoordinateDAO;
    }

}
