package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.CanonicalVariantDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.CanonicalVariant;
import org.renci.hearsay.ws.CanonicalVariantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CanonicalVariantServiceImpl implements CanonicalVariantService {

    private final Logger logger = LoggerFactory.getLogger(CanonicalVariantServiceImpl.class);

    private CanonicalVariantDAO canonicalVariantDAO;

    public CanonicalVariantServiceImpl() {
        super();
    }

    @Override
    public CanonicalVariant findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        CanonicalVariant ret = null;
        try {
            ret = canonicalVariantDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public CanonicalVariantDAO getCanonicalVariantDAO() {
        return canonicalVariantDAO;
    }

    public void setCanonicalVariantDAO(CanonicalVariantDAO canonicalVariantDAO) {
        this.canonicalVariantDAO = canonicalVariantDAO;
    }

}
