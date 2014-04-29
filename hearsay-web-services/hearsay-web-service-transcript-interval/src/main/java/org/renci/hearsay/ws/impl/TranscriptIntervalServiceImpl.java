package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranscriptIntervalDAO;
import org.renci.hearsay.dao.model.TranscriptInterval;
import org.renci.hearsay.ws.TranscriptIntervalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptIntervalServiceImpl implements TranscriptIntervalService {

    private final Logger logger = LoggerFactory.getLogger(TranscriptIntervalServiceImpl.class);

    private TranscriptIntervalDAO transcriptIntervalDAO;

    @Override
    public TranscriptInterval findById(Long id) {
        logger.info("ENTERING findById(Long)");
        TranscriptInterval ret = null;
        try {
            ret = transcriptIntervalDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(TranscriptInterval transcriptInterval) {
        logger.info("ENTERING save(Document)");
        Long ret = null;
        try {
            ret = transcriptIntervalDAO.save(transcriptInterval);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public TranscriptIntervalDAO getTranscriptIntervalDAO() {
        return transcriptIntervalDAO;
    }

    public void setTranscriptIntervalDAO(TranscriptIntervalDAO transcriptIntervalDAO) {
        this.transcriptIntervalDAO = transcriptIntervalDAO;
    }

}
