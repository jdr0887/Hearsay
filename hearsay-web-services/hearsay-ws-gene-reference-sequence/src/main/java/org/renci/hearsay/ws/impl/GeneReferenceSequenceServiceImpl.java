package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.GeneReferenceSequenceDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GeneReferenceSequence;
import org.renci.hearsay.ws.GeneReferenceSequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneReferenceSequenceServiceImpl implements GeneReferenceSequenceService {

    private final Logger logger = LoggerFactory.getLogger(GeneReferenceSequenceServiceImpl.class);

    private GeneReferenceSequenceDAO geneReferenceSequenceDAO;

    public GeneReferenceSequenceServiceImpl() {
        super();
    }

    @Override
    public GeneReferenceSequence findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        GeneReferenceSequence ret = null;
        try {
            ret = geneReferenceSequenceDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public GeneReferenceSequenceDAO getGeneReferenceSequenceDAO() {
        return geneReferenceSequenceDAO;
    }

    public void setGeneReferenceSequenceDAO(GeneReferenceSequenceDAO geneReferenceSequenceDAO) {
        this.geneReferenceSequenceDAO = geneReferenceSequenceDAO;
    }

}
