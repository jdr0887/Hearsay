package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.IndividualVariantDAO;
import org.renci.hearsay.dao.model.IndividualVariant;
import org.renci.hearsay.ws.IndividualVariantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndividualServiceImpl implements IndividualVariantService {

    private final Logger logger = LoggerFactory.getLogger(IndividualServiceImpl.class);

    private IndividualVariantDAO individualVariantDAO;

    @Override
    public IndividualVariant findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        IndividualVariant ret = null;
        try {
            ret = individualVariantDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public IndividualVariantDAO getIndividualVariantDAO() {
        return individualVariantDAO;
    }

    public void setIndividualVariantDAO(IndividualVariantDAO individualVariantDAO) {
        this.individualVariantDAO = individualVariantDAO;
    }

}
