package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranslationDAO;
import org.renci.hearsay.dao.model.TranslationSequence;
import org.renci.hearsay.ws.TranslationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranslationServiceImpl implements TranslationService {

    private final Logger logger = LoggerFactory.getLogger(TranslationServiceImpl.class);

    private TranslationDAO translationDAO;

    @Override
    public TranslationSequence findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        TranslationSequence ret = null;
        try {
            ret = translationDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(TranslationSequence translation) {
        logger.debug("ENTERING save(Translation)");
        Long ret = null;
        try {
            ret = translationDAO.save(translation);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public TranslationDAO getTranslationDAO() {
        return translationDAO;
    }

    public void setTranslationDAO(TranslationDAO translationDAO) {
        this.translationDAO = translationDAO;
    }

}
