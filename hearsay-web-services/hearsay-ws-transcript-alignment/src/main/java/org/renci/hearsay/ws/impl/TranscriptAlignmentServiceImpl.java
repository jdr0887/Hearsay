package org.renci.hearsay.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranscriptAlignmentDAO;
import org.renci.hearsay.dao.model.TranscriptAlignment;
import org.renci.hearsay.ws.TranscriptAlignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptAlignmentServiceImpl implements TranscriptAlignmentService {

    private final Logger logger = LoggerFactory.getLogger(TranscriptAlignmentServiceImpl.class);

    private TranscriptAlignmentDAO transcriptAlignmentDAO;

    @Override
    public TranscriptAlignment findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        TranscriptAlignment ret = null;
        try {
            ret = transcriptAlignmentDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(TranscriptAlignment transcriptInterval) {
        logger.debug("ENTERING save(Document)");
        Long ret = null;
        try {
            ret = transcriptAlignmentDAO.save(transcriptInterval);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<TranscriptAlignment> findByTranscriptRefSeqId(Long transcriptRefSeqId) {
        logger.debug("ENTERING findByTranscriptRefSeqId(Long)");
        List<TranscriptAlignment> ret = new ArrayList<TranscriptAlignment>();
        try {
            ret.addAll(transcriptAlignmentDAO.findByTranscriptRefSeqId(transcriptRefSeqId));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<TranscriptAlignment> findByTranscriptRefSeqAccession(String accession) {
        logger.debug("ENTERING findByAccession(String)");
        List<TranscriptAlignment> ret = new ArrayList<TranscriptAlignment>();
        try {
            ret.addAll(transcriptAlignmentDAO.findByTranscriptRefSeqAccession(accession));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<TranscriptAlignment> findByGeneName(String name) {
        logger.debug("ENTERING findByGeneName(String)");
        List<TranscriptAlignment> ret = new ArrayList<TranscriptAlignment>();
        try {
            ret.addAll(transcriptAlignmentDAO.findByGeneName(name));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public TranscriptAlignmentDAO getTranscriptAlignmentDAO() {
        return transcriptAlignmentDAO;
    }

    public void setTranscriptAlignmentDAO(TranscriptAlignmentDAO transcriptAlignmentDAO) {
        this.transcriptAlignmentDAO = transcriptAlignmentDAO;
    }

}
