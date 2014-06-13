package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranslationDAO;
import org.renci.hearsay.dao.model.Translation;
import org.renci.hearsay.ws.TranslationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranslationServiceImpl implements TranslationService {

    private final Logger logger = LoggerFactory.getLogger(TranslationServiceImpl.class);

    private TranslationDAO translationDAO;

    @Override
    public Translation findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        Translation ret = null;
        try {
            ret = translationDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(Translation translation) {
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
