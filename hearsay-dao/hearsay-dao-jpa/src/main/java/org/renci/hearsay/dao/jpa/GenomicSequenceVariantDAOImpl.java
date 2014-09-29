package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.GenomicVariantDAO;
import org.renci.hearsay.dao.model.GenomicSequenceVariant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenomicSequenceVariantDAOImpl extends BaseEntityDAOImpl<GenomicSequenceVariant, Long> implements
        GenomicVariantDAO {

    private final Logger logger = LoggerFactory.getLogger(GenomicSequenceVariantDAOImpl.class);

    public GenomicSequenceVariantDAOImpl() {
        super();
    }

    @Override
    public Class<GenomicSequenceVariant> getPersistentClass() {
        return GenomicSequenceVariant.class;
    }

}
