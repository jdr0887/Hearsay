package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.GenomicVariantDAO;
import org.renci.hearsay.dao.model.GenomicVariant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenomicVariantDAOImpl extends BaseEntityDAOImpl<GenomicVariant, Long> implements GenomicVariantDAO {

    private final Logger logger = LoggerFactory.getLogger(GenomicVariantDAOImpl.class);

    public GenomicVariantDAOImpl() {
        super();
    }

    @Override
    public Class<GenomicVariant> getPersistentClass() {
        return GenomicVariant.class;
    }

}
