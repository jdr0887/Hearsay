package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.MolecularConsequenceDAO;
import org.renci.hearsay.dao.model.MolecularConsequence;
import org.renci.hearsay.ws.MolecularConsequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MolecularConsequenceServiceImpl implements MolecularConsequenceService {

    private final Logger logger = LoggerFactory.getLogger(MolecularConsequenceServiceImpl.class);

    private MolecularConsequenceDAO molecularConsequenceDAO;

    public MolecularConsequenceServiceImpl() {
        super();
    }

    @Override
    public MolecularConsequence findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        MolecularConsequence ret = null;
        try {
            ret = molecularConsequenceDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public MolecularConsequenceDAO getMolecularConsequenceDAO() {
        return molecularConsequenceDAO;
    }

    public void setMolecularConsequenceDAO(MolecularConsequenceDAO molecularConsequenceDAO) {
        this.molecularConsequenceDAO = molecularConsequenceDAO;
    }

}
