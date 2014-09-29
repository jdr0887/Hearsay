package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.TranslationRefSeqDAO;
import org.renci.hearsay.dao.model.TranslationRefSeq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranslationRefSeqDAOImpl extends BaseEntityDAOImpl<TranslationRefSeq, Long> implements TranslationRefSeqDAO {

    private final Logger logger = LoggerFactory.getLogger(TranslationRefSeqDAOImpl.class);

    public TranslationRefSeqDAOImpl() {
        super();
    }

    @Override
    public Class<TranslationRefSeq> getPersistentClass() {
        return TranslationRefSeq.class;
    }

}
