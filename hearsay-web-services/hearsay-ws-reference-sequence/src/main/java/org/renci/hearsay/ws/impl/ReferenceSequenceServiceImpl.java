package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.ReferenceSequenceDAO;
import org.renci.hearsay.dao.model.ReferenceSequence;
import org.renci.hearsay.ws.ReferenceSequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReferenceSequenceServiceImpl implements ReferenceSequenceService {

    private final Logger logger = LoggerFactory.getLogger(ReferenceSequenceServiceImpl.class);

    private ReferenceSequenceDAO referenceSequenceDAO;

    public ReferenceSequenceServiceImpl() {
        super();
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

    public ReferenceSequenceDAO getReferenceSequenceDAO() {
        return referenceSequenceDAO;
    }

    public void setReferenceSequenceDAO(ReferenceSequenceDAO referenceSequenceDAO) {
        this.referenceSequenceDAO = referenceSequenceDAO;
    }

}
