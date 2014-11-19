package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranslationVariantDAO;
import org.renci.hearsay.dao.model.TranslationVariant;
import org.renci.hearsay.ws.TranslationVariantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranslationVariantServiceImpl implements TranslationVariantService {

    private final Logger logger = LoggerFactory.getLogger(TranslationVariantServiceImpl.class);

    private TranslationVariantDAO translationVariantDAO;

    public TranslationVariantServiceImpl() {
        super();
    }

    @Override
    public TranslationVariant findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        TranslationVariant ret = null;
        try {
            ret = translationVariantDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(TranslationVariant transcriptVariant) {
        logger.debug("ENTERING save(TranslationVariant)");
        Long ret = null;
        try {
            ret = translationVariantDAO.save(transcriptVariant);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public TranslationVariantDAO getTranslationVariantDAO() {
        return translationVariantDAO;
    }

    public void setTranslationVariantDAO(TranslationVariantDAO translationVariantDAO) {
        this.translationVariantDAO = translationVariantDAO;
    }

}
