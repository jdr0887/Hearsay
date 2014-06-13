package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.VariantEffectDAO;
import org.renci.hearsay.dao.model.VariantEffect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VariantEffectDAOImpl extends BaseEntityDAOImpl<VariantEffect, Long> implements VariantEffectDAO {

    private final Logger logger = LoggerFactory.getLogger(VariantEffectDAOImpl.class);

    public VariantEffectDAOImpl() {
        super();
    }

    @Override
    public Class<VariantEffect> getPersistentClass() {
        return VariantEffect.class;
    }

}
