package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.TranslationDAO;
import org.renci.hearsay.dao.model.Translation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranslationDAOImpl extends BaseEntityDAOImpl<Translation, Long> implements TranslationDAO {

    private final Logger logger = LoggerFactory.getLogger(TranslationDAOImpl.class);

    public TranslationDAOImpl() {
        super();
    }

    @Override
    public Class<Translation> getPersistentClass() {
        return Translation.class;
    }

}
