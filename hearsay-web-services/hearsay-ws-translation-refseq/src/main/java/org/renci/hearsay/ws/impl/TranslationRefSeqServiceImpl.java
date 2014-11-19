package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranslationRefSeqDAO;
import org.renci.hearsay.dao.model.TranslationRefSeq;
import org.renci.hearsay.ws.TranslationRefSeqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranslationRefSeqServiceImpl implements TranslationRefSeqService {

    private final Logger logger = LoggerFactory.getLogger(TranslationRefSeqServiceImpl.class);

    private TranslationRefSeqDAO translationRefSeqDAO;

    public TranslationRefSeqServiceImpl() {
        super();
    }

    @Override
    public TranslationRefSeq findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        TranslationRefSeq ret = null;
        try {
            ret = translationRefSeqDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(TranslationRefSeq translationRefSeq) {
        return null;
    }

    public TranslationRefSeqDAO getTranslationRefSeqDAO() {
        return translationRefSeqDAO;
    }

    public void setTranslationRefSeqDAO(TranslationRefSeqDAO translationRefSeqDAO) {
        this.translationRefSeqDAO = translationRefSeqDAO;
    }

}
