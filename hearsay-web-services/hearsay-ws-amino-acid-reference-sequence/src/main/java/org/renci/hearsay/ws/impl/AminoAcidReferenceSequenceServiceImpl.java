package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.AminoAcidReferenceSequenceDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.AminoAcidReferenceSequence;
import org.renci.hearsay.ws.AminoAcidReferenceSequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AminoAcidReferenceSequenceServiceImpl implements AminoAcidReferenceSequenceService {

    private final Logger logger = LoggerFactory.getLogger(AminoAcidReferenceSequenceServiceImpl.class);

    private AminoAcidReferenceSequenceDAO aminoAcidReferenceSequenceDAO;

    public AminoAcidReferenceSequenceServiceImpl() {
        super();
    }

    @Override
    public AminoAcidReferenceSequence findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        AminoAcidReferenceSequence ret = null;
        try {
            ret = aminoAcidReferenceSequenceDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public AminoAcidReferenceSequenceDAO getAminoAcidReferenceSequenceDAO() {
        return aminoAcidReferenceSequenceDAO;
    }

    public void setAminoAcidReferenceSequenceDAO(AminoAcidReferenceSequenceDAO aminoAcidReferenceSequenceDAO) {
        this.aminoAcidReferenceSequenceDAO = aminoAcidReferenceSequenceDAO;
    }

}
