package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.CanonicalAlleleDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.CanonicalAllele;
import org.renci.hearsay.ws.CanonicalAlleleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CanonicalAlleleServiceImpl implements CanonicalAlleleService {

    private final Logger logger = LoggerFactory.getLogger(CanonicalAlleleServiceImpl.class);

    private CanonicalAlleleDAO canonicalAlleleDAO;

    public CanonicalAlleleServiceImpl() {
        super();
    }

    @Override
    public CanonicalAllele findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        CanonicalAllele ret = null;
        try {
            ret = canonicalAlleleDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public CanonicalAlleleDAO getCanonicalAlleleDAO() {
        return canonicalAlleleDAO;
    }

    public void setCanonicalAlleleDAO(CanonicalAlleleDAO canonicalAlleleDAO) {
        this.canonicalAlleleDAO = canonicalAlleleDAO;
    }

}
