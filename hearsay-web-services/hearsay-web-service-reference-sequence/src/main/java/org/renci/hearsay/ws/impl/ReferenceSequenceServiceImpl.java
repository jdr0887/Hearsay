package org.renci.hearsay.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.ReferenceSequenceDAO;
import org.renci.hearsay.dao.model.ReferenceSequence;
import org.renci.hearsay.ws.ReferenceSequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReferenceSequenceServiceImpl implements ReferenceSequenceService {

    private final Logger logger = LoggerFactory.getLogger(ReferenceSequenceServiceImpl.class);

    private ReferenceSequenceDAO referenceSequenceDAO;

    @Override
    public List<ReferenceSequence> findByAccession(String accession) {
        logger.debug("ENTERING findByAccession(String)");
        List<ReferenceSequence> ret = new ArrayList<ReferenceSequence>();
        try {
            ret.addAll(referenceSequenceDAO.findByAccession(accession));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<ReferenceSequence> findByReferenceGenomeId(Long id) {
        logger.debug("ENTERING findByReferenceGenomeId(Long)");
        List<ReferenceSequence> ret = new ArrayList<ReferenceSequence>();
        try {
            ret.addAll(referenceSequenceDAO.findByReferenceGenomeId(id));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public ReferenceSequence findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        ReferenceSequence ret = null;
        try {
            ret = referenceSequenceDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(ReferenceSequence referenceSequence) {
        logger.debug("ENTERING save(Transcript)");
        Long ret = null;
        try {
            ret = referenceSequenceDAO.save(referenceSequence);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ReferenceSequenceDAO getReferenceSequenceDAO() {
        return referenceSequenceDAO;
    }

    public void setReferenceSequenceDAO(ReferenceSequenceDAO referenceSequenceDAO) {
        this.referenceSequenceDAO = referenceSequenceDAO;
    }

}
