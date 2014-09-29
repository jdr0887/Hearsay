package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.TranslationRefSeqDAO;
import org.renci.hearsay.dao.model.TranslationSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranslationDAOImpl extends BaseEntityDAOImpl<TranslationSequence, Long> implements TranslationRefSeqDAO {

    private final Logger logger = LoggerFactory.getLogger(TranslationDAOImpl.class);

    public TranslationDAOImpl() {
        super();
    }

    @Override
    public Class<TranslationSequence> getPersistentClass() {
        return TranslationSequence.class;
    }

}
