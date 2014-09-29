package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.VariantEffectDAO;
import org.renci.hearsay.dao.model.VariantRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VariantEffectDAOImpl extends BaseEntityDAOImpl<VariantRepresentation, Long> implements VariantEffectDAO {

    private final Logger logger = LoggerFactory.getLogger(VariantEffectDAOImpl.class);

    public VariantEffectDAOImpl() {
        super();
    }

    @Override
    public Class<VariantRepresentation> getPersistentClass() {
        return VariantRepresentation.class;
    }

}
