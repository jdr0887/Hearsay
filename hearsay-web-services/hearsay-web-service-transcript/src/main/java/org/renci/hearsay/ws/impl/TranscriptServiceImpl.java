package org.renci.hearsay.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranscriptDAO;
import org.renci.hearsay.dao.model.Transcript;
import org.renci.hearsay.ws.TranscriptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptServiceImpl implements TranscriptService {

    private final Logger logger = LoggerFactory.getLogger(TranscriptServiceImpl.class);

    private TranscriptDAO transcriptDAO;

    @Override
    public Transcript findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        Transcript ret = null;
        try {
            ret = transcriptDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(Transcript transcript) {
        logger.debug("ENTERING save(Transcript)");
        Long ret = null;
        try {
            ret = transcriptDAO.save(transcript);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<Transcript> findAll() {
        List<Transcript> ret = new ArrayList<Transcript>();
        try {
            ret.addAll(transcriptDAO.findAll());
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<Transcript> findByGeneId(Long geneId) {
        List<Transcript> ret = new ArrayList<Transcript>();
        try {
            ret.addAll(transcriptDAO.findByGeneId(geneId));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public TranscriptDAO getTranscriptDAO() {
        return transcriptDAO;
    }

    public void setTranscriptDAO(TranscriptDAO transcriptDAO) {
        this.transcriptDAO = transcriptDAO;
    }

}
