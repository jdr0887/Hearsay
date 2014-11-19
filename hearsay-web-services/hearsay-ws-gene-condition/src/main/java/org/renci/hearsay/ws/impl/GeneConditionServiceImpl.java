package org.renci.hearsay.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.renci.hearsay.dao.GeneConditionDAO;
import org.renci.hearsay.dao.GeneDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.GeneCondition;
import org.renci.hearsay.ws.GeneConditionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneConditionServiceImpl implements GeneConditionService {

    private final Logger logger = LoggerFactory.getLogger(GeneConditionServiceImpl.class);

    private GeneConditionDAO geneConditionDAO;

    public GeneConditionServiceImpl() {
        super();
    }

    @Override
    public GeneCondition findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        GeneCondition ret = null;
        try {
            ret = geneConditionDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(GeneCondition geneCondition) {
        logger.debug("ENTERING save(GeneCondition)");
        Long ret = null;
        try {
            ret = geneConditionDAO.save(geneCondition);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public GeneConditionDAO getGeneConditionDAO() {
        return geneConditionDAO;
    }

    public void setGeneConditionDAO(GeneConditionDAO geneConditionDAO) {
        this.geneConditionDAO = geneConditionDAO;
    }

}
