package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranscriptVariantDAO;
import org.renci.hearsay.dao.model.TranscriptVariant;
import org.renci.hearsay.ws.TranscriptVariantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptVariantServiceImpl implements TranscriptVariantService {

    private final Logger logger = LoggerFactory.getLogger(TranscriptVariantServiceImpl.class);

    private TranscriptVariantDAO transcriptVariantDAO;

    public TranscriptVariantServiceImpl() {
        super();
    }

    @Override
    public TranscriptVariant findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        TranscriptVariant ret = null;
        try {
            ret = transcriptVariantDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(TranscriptVariant transcriptVariant) {
        logger.debug("ENTERING save(Transcript)");
        Long ret = null;
        try {
            ret = transcriptVariantDAO.save(transcriptVariant);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public TranscriptVariantDAO getTranscriptVariantDAO() {
        return transcriptVariantDAO;
    }

    public void setTranscriptVariantDAO(TranscriptVariantDAO transcriptVariantDAO) {
        this.transcriptVariantDAO = transcriptVariantDAO;
    }

}
