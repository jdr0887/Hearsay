package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.SimpleAminoAcidCanonicalAlleleDAO;
import org.renci.hearsay.dao.model.SimpleAminoAcidCanonicalAllele;
import org.renci.hearsay.ws.SimpleAminoAcidCanonicalAlleleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleAminoAcidCanonicalAlleleServiceImpl implements SimpleAminoAcidCanonicalAlleleService {

    private final Logger logger = LoggerFactory.getLogger(SimpleAminoAcidCanonicalAlleleServiceImpl.class);

    private SimpleAminoAcidCanonicalAlleleDAO simpleAminoAcidCanonicalAlleleDAO;

    public SimpleAminoAcidCanonicalAlleleServiceImpl() {
        super();
    }

    @Override
    public SimpleAminoAcidCanonicalAllele findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        SimpleAminoAcidCanonicalAllele ret = null;
        try {
            ret = simpleAminoAcidCanonicalAlleleDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public SimpleAminoAcidCanonicalAlleleDAO getSimpleAminoAcidCanonicalAlleleDAO() {
        return simpleAminoAcidCanonicalAlleleDAO;
    }

    public void setSimpleAminoAcidCanonicalAlleleDAO(SimpleAminoAcidCanonicalAlleleDAO simpleAminoAcidCanonicalAlleleDAO) {
        this.simpleAminoAcidCanonicalAlleleDAO = simpleAminoAcidCanonicalAlleleDAO;
    }

}
