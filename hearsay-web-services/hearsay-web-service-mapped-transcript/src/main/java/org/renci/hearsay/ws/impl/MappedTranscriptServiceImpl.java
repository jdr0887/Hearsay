package org.renci.hearsay.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.MappedTranscriptDAO;
import org.renci.hearsay.dao.model.MappedTranscript;
import org.renci.hearsay.ws.MappedTranscriptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MappedTranscriptServiceImpl implements MappedTranscriptService {

    private final Logger logger = LoggerFactory.getLogger(MappedTranscriptServiceImpl.class);

    private MappedTranscriptDAO mappedTranscriptDAO;

    @Override
    public MappedTranscript findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        MappedTranscript ret = null;
        try {
            ret = mappedTranscriptDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(MappedTranscript transcriptInterval) {
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
    public List<MappedTranscript> findByTranscriptId(Long transcriptId) {
        logger.debug("ENTERING findByTranscriptId(Long)");
        List<MappedTranscript> ret = new ArrayList<MappedTranscript>();
        try {
            ret.addAll(mappedTranscriptDAO.findByTranscriptId(transcriptId));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<MappedTranscript> findByTranscriptAccession(String accession) {
        logger.debug("ENTERING findByTranscriptAccession(String)");
        List<MappedTranscript> ret = new ArrayList<MappedTranscript>();
        try {
            ret.addAll(mappedTranscriptDAO.findByTranscriptAccession(accession));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<MappedTranscript> findByGeneName(String name) {
        logger.debug("ENTERING findByGeneName(String)");
        List<MappedTranscript> ret = new ArrayList<MappedTranscript>();
        try {
            ret.addAll(mappedTranscriptDAO.findByGeneName(name));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public MappedTranscriptDAO getMappedTranscriptDAO() {
        return mappedTranscriptDAO;
    }

    public void setMappedTranscriptDAO(MappedTranscriptDAO mappedTranscriptDAO) {
        this.mappedTranscriptDAO = mappedTranscriptDAO;
    }

}
