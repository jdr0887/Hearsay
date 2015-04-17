package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.CanonicalAlleleDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.SimpleAminoAcidAlleleDAO;
import org.renci.hearsay.dao.model.SimpleAminoAcidAllele;
import org.renci.hearsay.ws.SimpleAminoAcidAlleleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleAminoAcidAlleleServiceImpl implements SimpleAminoAcidAlleleService {

    private final Logger logger = LoggerFactory.getLogger(SimpleAminoAcidAlleleServiceImpl.class);

    private SimpleAminoAcidAlleleDAO simpleAminoAcidAlleleDAO;

    public SimpleAminoAcidAlleleServiceImpl() {
        super();
    }

    @Override
    public SimpleAminoAcidAllele findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        SimpleAminoAcidAllele ret = null;
        try {
            ret = simpleAminoAcidAlleleDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public SimpleAminoAcidAlleleDAO getSimpleAminoAcidAlleleDAO() {
        return simpleAminoAcidAlleleDAO;
    }

    public void setSimpleAminoAcidAlleleDAO(SimpleAminoAcidAlleleDAO simpleAminoAcidAlleleDAO) {
        this.simpleAminoAcidAlleleDAO = simpleAminoAcidAlleleDAO;
    }

}
