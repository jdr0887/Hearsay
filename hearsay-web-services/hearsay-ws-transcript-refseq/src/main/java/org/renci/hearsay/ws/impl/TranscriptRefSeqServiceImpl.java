package org.renci.hearsay.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranscriptRefSeqDAO;
import org.renci.hearsay.dao.model.TranscriptRefSeq;
import org.renci.hearsay.ws.TranscriptRefSeqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptRefSeqServiceImpl implements TranscriptRefSeqService {

    private final Logger logger = LoggerFactory.getLogger(TranscriptRefSeqServiceImpl.class);

    private TranscriptRefSeqDAO transcriptRefSeqDAO;

    @Override
    public TranscriptRefSeq findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        TranscriptRefSeq ret = null;
        try {
            ret = transcriptRefSeqDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(TranscriptRefSeq transcript) {
        logger.debug("ENTERING save(Transcript)");
        Long ret = null;
        try {
            ret = transcriptRefSeqDAO.save(transcript);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<TranscriptRefSeq> findAll() {
        List<TranscriptRefSeq> ret = new ArrayList<TranscriptRefSeq>();
        try {
            ret.addAll(transcriptRefSeqDAO.findAll());
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<TranscriptRefSeq> findByGeneId(Long geneId) {
        List<TranscriptRefSeq> ret = new ArrayList<TranscriptRefSeq>();
        try {
            ret.addAll(transcriptRefSeqDAO.findByGeneId(geneId));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<TranscriptRefSeq> findByAccession(String accession) {
        List<TranscriptRefSeq> ret = new ArrayList<TranscriptRefSeq>();
        try {
            ret.addAll(transcriptRefSeqDAO.findByAccession(accession));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<TranscriptRefSeq> findByGeneName(String geneName) {
        List<TranscriptRefSeq> ret = new ArrayList<TranscriptRefSeq>();
        try {
            ret.addAll(transcriptRefSeqDAO.findByGeneName(geneName));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public TranscriptRefSeqDAO getTranscriptRefSeqDAO() {
        return transcriptRefSeqDAO;
    }

    public void setTranscriptRefSeqDAO(TranscriptRefSeqDAO transcriptRefSeqDAO) {
        this.transcriptRefSeqDAO = transcriptRefSeqDAO;
    }

}
