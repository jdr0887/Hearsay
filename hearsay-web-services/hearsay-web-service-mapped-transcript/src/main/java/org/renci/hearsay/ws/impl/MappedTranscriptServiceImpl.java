package org.renci.hearsay.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranscriptAlignmentDAO;
import org.renci.hearsay.dao.model.TranscriptAlignment;
import org.renci.hearsay.ws.MappedTranscriptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MappedTranscriptServiceImpl implements MappedTranscriptService {

    private final Logger logger = LoggerFactory.getLogger(MappedTranscriptServiceImpl.class);

    private TranscriptAlignmentDAO mappedTranscriptDAO;

    @Override
    public TranscriptAlignment findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        TranscriptAlignment ret = null;
        try {
            ret = mappedTranscriptDAO.findById(id);
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
            ret = mappedTranscriptDAO.save(transcriptInterval);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<TranscriptAlignment> findByTranscriptId(Long transcriptId) {
        logger.debug("ENTERING findByTranscriptId(Long)");
        List<TranscriptAlignment> ret = new ArrayList<TranscriptAlignment>();
        try {
            ret.addAll(mappedTranscriptDAO.findByTranscriptId(transcriptId));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<TranscriptAlignment> findByTranscriptAccession(String accession) {
        logger.debug("ENTERING findByTranscriptAccession(String)");
        List<TranscriptAlignment> ret = new ArrayList<TranscriptAlignment>();
        try {
            ret.addAll(mappedTranscriptDAO.findByTranscriptAccession(accession));
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
            ret.addAll(mappedTranscriptDAO.findByGeneName(name));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public TranscriptAlignmentDAO getMappedTranscriptDAO() {
        return mappedTranscriptDAO;
    }

    public void setMappedTranscriptDAO(TranscriptAlignmentDAO mappedTranscriptDAO) {
        this.mappedTranscriptDAO = mappedTranscriptDAO;
    }

}
