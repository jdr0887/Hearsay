package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.ChromosomeReferenceSequenceDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.ChromosomeReferenceSequence;
import org.renci.hearsay.ws.ChromosomeReferenceSequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChromosomeReferenceSequenceServiceImpl implements ChromosomeReferenceSequenceService {

    private final Logger logger = LoggerFactory.getLogger(ChromosomeReferenceSequenceServiceImpl.class);

    private ChromosomeReferenceSequenceDAO chromosomeReferenceSequenceDAO;

    public ChromosomeReferenceSequenceServiceImpl() {
        super();
    }

    @Override
    public ChromosomeReferenceSequence findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        ChromosomeReferenceSequence ret = null;
        try {
            ret = chromosomeReferenceSequenceDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ChromosomeReferenceSequenceDAO getChromosomeReferenceSequenceDAO() {
        return chromosomeReferenceSequenceDAO;
    }

    public void setChromosomeReferenceSequenceDAO(ChromosomeReferenceSequenceDAO chromosomeReferenceSequenceDAO) {
        this.chromosomeReferenceSequenceDAO = chromosomeReferenceSequenceDAO;
    }

}
