package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranscriptReferenceCoordinateDAO;
import org.renci.hearsay.dao.model.TranscriptReferenceCoordinate;
import org.renci.hearsay.ws.TranscriptReferenceCoordinateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptReferenceCoordinateServiceImpl implements TranscriptReferenceCoordinateService {

    private final Logger logger = LoggerFactory.getLogger(TranscriptReferenceCoordinateServiceImpl.class);

    private TranscriptReferenceCoordinateDAO transcriptReferenceCoordinateDAO;

    public TranscriptReferenceCoordinateServiceImpl() {
        super();
    }

    @Override
    public TranscriptReferenceCoordinate findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        TranscriptReferenceCoordinate ret = null;
        try {
            ret = transcriptReferenceCoordinateDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public TranscriptReferenceCoordinateDAO getTranscriptReferenceCoordinateDAO() {
        return transcriptReferenceCoordinateDAO;
    }

    public void setTranscriptReferenceCoordinateDAO(TranscriptReferenceCoordinateDAO transcriptReferenceCoordinateDAO) {
        this.transcriptReferenceCoordinateDAO = transcriptReferenceCoordinateDAO;
    }

}
