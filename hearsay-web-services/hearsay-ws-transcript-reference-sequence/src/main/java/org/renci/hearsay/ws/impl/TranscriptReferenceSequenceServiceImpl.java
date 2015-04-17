package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranscriptReferenceSequenceDAO;
import org.renci.hearsay.dao.model.TranscriptReferenceSequence;
import org.renci.hearsay.ws.TranscriptReferenceSequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptReferenceSequenceServiceImpl implements TranscriptReferenceSequenceService {

    private final Logger logger = LoggerFactory.getLogger(TranscriptReferenceSequenceServiceImpl.class);

    private TranscriptReferenceSequenceDAO transcriptReferenceSequenceDAO;

    public TranscriptReferenceSequenceServiceImpl() {
        super();
    }

    @Override
    public TranscriptReferenceSequence findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        TranscriptReferenceSequence ret = null;
        try {
            ret = transcriptReferenceSequenceDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public TranscriptReferenceSequenceDAO getTranscriptReferenceSequenceDAO() {
        return transcriptReferenceSequenceDAO;
    }

    public void setTranscriptReferenceSequenceDAO(TranscriptReferenceSequenceDAO transcriptReferenceSequenceDAO) {
        this.transcriptReferenceSequenceDAO = transcriptReferenceSequenceDAO;
    }

}
