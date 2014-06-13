package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.TranscriptVariantDAO;
import org.renci.hearsay.dao.model.TranscriptVariant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptVariantDAOImpl extends BaseEntityDAOImpl<TranscriptVariant, Long> implements
        TranscriptVariantDAO {

    private final Logger logger = LoggerFactory.getLogger(TranscriptVariantDAOImpl.class);

    public TranscriptVariantDAOImpl() {
        super();
    }

    @Override
    public Class<TranscriptVariant> getPersistentClass() {
        return TranscriptVariant.class;
    }

}
