package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.SimpleTranscriptAlleleDAO;
import org.renci.hearsay.dao.model.SimpleTranscriptAllele;
import org.renci.hearsay.ws.SimpleTranscriptAlleleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleTranscriptAlleleServiceImpl implements SimpleTranscriptAlleleService {

    private final Logger logger = LoggerFactory.getLogger(SimpleTranscriptAlleleServiceImpl.class);

    private SimpleTranscriptAlleleDAO simpleTranscriptAlleleDAO;

    public SimpleTranscriptAlleleServiceImpl() {
        super();
    }

    @Override
    public SimpleTranscriptAllele findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        SimpleTranscriptAllele ret = null;
        try {
            ret = simpleTranscriptAlleleDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public SimpleTranscriptAlleleDAO getSimpleTranscriptAlleleDAO() {
        return simpleTranscriptAlleleDAO;
    }

    public void setSimpleTranscriptAlleleDAO(SimpleTranscriptAlleleDAO simpleTranscriptAlleleDAO) {
        this.simpleTranscriptAlleleDAO = simpleTranscriptAlleleDAO;
    }

}
