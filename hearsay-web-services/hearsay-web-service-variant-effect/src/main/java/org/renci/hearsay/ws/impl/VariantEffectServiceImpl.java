package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.VariantEffectDAO;
import org.renci.hearsay.dao.model.VariantRepresentation;
import org.renci.hearsay.ws.VariantEffectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VariantEffectServiceImpl implements VariantEffectService {

    private final Logger logger = LoggerFactory.getLogger(VariantEffectServiceImpl.class);

    private VariantEffectDAO variantEffectDAO;

    @Override
    public VariantRepresentation findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        VariantRepresentation ret = null;
        try {
            ret = variantEffectDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(VariantRepresentation variantEffect) {
        logger.debug("ENTERING save(VariantEffect)");
        Long ret = null;
        try {
            ret = variantEffectDAO.save(variantEffect);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public VariantEffectDAO getVariantEffectDAO() {
        return variantEffectDAO;
    }

    public void setVariantEffectDAO(VariantEffectDAO variantEffectDAO) {
        this.variantEffectDAO = variantEffectDAO;
    }

}
