package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.TranslationVariantDAO;
import org.renci.hearsay.dao.model.TranslationVariant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranslationVariantDAOImpl extends BaseEntityDAOImpl<TranslationVariant, Long> implements TranslationVariantDAO {

    private final Logger logger = LoggerFactory.getLogger(TranslationVariantDAOImpl.class);

    public TranslationVariantDAOImpl() {
        super();
    }

    @Override
    public Class<TranslationVariant> getPersistentClass() {
        return TranslationVariant.class;
    }

}
