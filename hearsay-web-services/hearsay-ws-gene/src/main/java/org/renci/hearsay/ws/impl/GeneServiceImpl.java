package org.renci.hearsay.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.renci.hearsay.dao.GeneDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.ws.GeneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneServiceImpl implements GeneService {

    private final Logger logger = LoggerFactory.getLogger(GeneServiceImpl.class);

    private GeneDAO geneDAO;

    public GeneServiceImpl() {
        super();
    }

    @Override
    public Gene findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        Gene ret = null;
        try {
            ret = geneDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(Gene gene) {
        logger.debug("ENTERING save(Gene)");
        Long ret = null;
        try {
            ret = geneDAO.save(gene);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<Gene> findAll() {
        logger.debug("ENTERING findAll()");
        List<Gene> ret = new ArrayList<Gene>();
        try {
            ret.addAll(geneDAO.findAll());
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<Gene> findBySymbol(String symbol) {
        logger.debug("ENTERING findAll()");
        List<Gene> ret = new ArrayList<Gene>();
        try {
            ret.addAll(geneDAO.findBySymbol(symbol));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public GeneDAO getGeneDAO() {
        return geneDAO;
    }

    public void setGeneDAO(GeneDAO geneDAO) {
        this.geneDAO = geneDAO;
    }

}
