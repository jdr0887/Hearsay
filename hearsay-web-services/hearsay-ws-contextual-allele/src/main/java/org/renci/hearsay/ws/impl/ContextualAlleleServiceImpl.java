package org.renci.hearsay.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.ContextualAlleleDAO;
import org.renci.hearsay.dao.model.ContextualAllele;
import org.renci.hearsay.ws.ContextualAlleleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextualAlleleServiceImpl implements ContextualAlleleService {

    private static final Logger logger = LoggerFactory.getLogger(ContextualAlleleServiceImpl.class);

    private ContextualAlleleDAO contextualAlleleDAO;

    public ContextualAlleleServiceImpl() {
        super();
    }

    @Override
    public ContextualAllele findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        ContextualAllele ret = null;
        try {
            ret = contextualAlleleDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<ContextualAllele> findByName(String name) {
        logger.debug("ENTERING findByName(String)");
        List<ContextualAllele> ret = new ArrayList<ContextualAllele>();
        try {
            ret.addAll(contextualAlleleDAO.findByName(name));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ContextualAlleleDAO getContextualAlleleDAO() {
        return contextualAlleleDAO;
    }

    public void setContextualAlleleDAO(ContextualAlleleDAO contextualAlleleDAO) {
        this.contextualAlleleDAO = contextualAlleleDAO;
    }

}
