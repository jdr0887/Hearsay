package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.CanonicalVariantDAO;
import org.renci.hearsay.dao.model.CanonicalVariant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CanonicalVariantDAOImpl extends BaseEntityDAOImpl<CanonicalVariant, Long> implements CanonicalVariantDAO {

    private final Logger logger = LoggerFactory.getLogger(CanonicalVariantDAOImpl.class);

    public CanonicalVariantDAOImpl() {
        super();
    }

    @Override
    public Class<CanonicalVariant> getPersistentClass() {
        return CanonicalVariant.class;
    }

}
