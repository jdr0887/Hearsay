package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.GeneSymbolDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GeneSymbol;
import org.renci.hearsay.ws.GeneSymbolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneSymbolServiceImpl implements GeneSymbolService {

    private final Logger logger = LoggerFactory.getLogger(GeneSymbolServiceImpl.class);

    private GeneSymbolDAO geneSymbolDAO;

    public GeneSymbolServiceImpl() {
        super();
    }

    @Override
    public GeneSymbol findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        GeneSymbol ret = null;
        try {
            ret = geneSymbolDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public GeneSymbolDAO getGeneSymbolDAO() {
        return geneSymbolDAO;
    }

    public void setGeneSymbolDAO(GeneSymbolDAO geneSymbolDAO) {
        this.geneSymbolDAO = geneSymbolDAO;
    }

}
