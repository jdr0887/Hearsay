package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.GeneConditionAssertionDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GeneConditionAssertion;
import org.renci.hearsay.ws.GeneConditionAssertionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneConditionAssertionServiceImpl implements GeneConditionAssertionService {

    private final Logger logger = LoggerFactory.getLogger(GeneConditionAssertionServiceImpl.class);

    private GeneConditionAssertionDAO geneConditionAssertionDAO;

    public GeneConditionAssertionServiceImpl() {
        super();
    }

    @Override
    public GeneConditionAssertion findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        GeneConditionAssertion ret = null;
        try {
            ret = geneConditionAssertionDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(GeneConditionAssertion geneConditionAssertion) {
        logger.debug("ENTERING save(GeneConditionAssertion)");
        Long ret = null;
        try {
            ret = geneConditionAssertionDAO.save(geneConditionAssertion);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public GeneConditionAssertionDAO getGeneConditionAssertionDAO() {
        return geneConditionAssertionDAO;
    }

    public void setGeneConditionAssertionDAO(GeneConditionAssertionDAO geneConditionAssertionDAO) {
        this.geneConditionAssertionDAO = geneConditionAssertionDAO;
    }

}
