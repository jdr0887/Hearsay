package org.renci.hearsay.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranscriptRefSeqDAO;
import org.renci.hearsay.dao.model.TranscriptSequence;
import org.renci.hearsay.ws.TranscriptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptServiceImpl implements TranscriptService {

    private final Logger logger = LoggerFactory.getLogger(TranscriptServiceImpl.class);

    private TranscriptRefSeqDAO transcriptDAO;

    @Override
    public TranscriptSequence findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        TranscriptSequence ret = null;
        try {
            ret = transcriptDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(TranscriptSequence transcript) {
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
    public List<TranscriptSequence> findAll() {
        List<TranscriptSequence> ret = new ArrayList<TranscriptSequence>();
        try {
            ret.addAll(transcriptDAO.findAll());
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<TranscriptSequence> findByGeneId(Long geneId) {
        List<TranscriptSequence> ret = new ArrayList<TranscriptSequence>();
        try {
            ret.addAll(transcriptDAO.findByGeneId(geneId));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<TranscriptSequence> findByAccession(String accession) {
        List<TranscriptSequence> ret = new ArrayList<TranscriptSequence>();
        try {
            ret.addAll(transcriptDAO.findByAccession(accession));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<TranscriptSequence> findByGeneName(String geneName) {
        List<TranscriptSequence> ret = new ArrayList<TranscriptSequence>();
        try {
            ret.addAll(transcriptDAO.findByGeneName(geneName));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public TranscriptRefSeqDAO getTranscriptDAO() {
        return transcriptDAO;
    }

    public void setTranscriptDAO(TranscriptRefSeqDAO transcriptDAO) {
        this.transcriptDAO = transcriptDAO;
    }

}
