package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.CanonicalVariantDAO;
import org.renci.hearsay.dao.model.CanonicalVariant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VariantDAOImpl extends BaseEntityDAOImpl<CanonicalVariant, Long> implements CanonicalVariantDAO {

    private final Logger logger = LoggerFactory.getLogger(VariantDAOImpl.class);

    public VariantDAOImpl() {
        super();
    }

    @Override
    public Class<CanonicalVariant> getPersistentClass() {
        return CanonicalVariant.class;
    }

}
