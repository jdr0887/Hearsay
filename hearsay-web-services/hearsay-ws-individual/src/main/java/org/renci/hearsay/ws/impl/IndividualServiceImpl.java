package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.IndividualDAO;
import org.renci.hearsay.dao.model.Individual;
import org.renci.hearsay.ws.IndividualService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndividualServiceImpl implements IndividualService {

    private final Logger logger = LoggerFactory.getLogger(IndividualServiceImpl.class);

    private IndividualDAO individualDAO;

    @Override
    public Individual findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        Individual ret = null;
        try {
            ret = individualDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public IndividualDAO getIndividualDAO() {
        return individualDAO;
    }

    public void setIndividualDAO(IndividualDAO individualDAO) {
        this.individualDAO = individualDAO;
    }

}
