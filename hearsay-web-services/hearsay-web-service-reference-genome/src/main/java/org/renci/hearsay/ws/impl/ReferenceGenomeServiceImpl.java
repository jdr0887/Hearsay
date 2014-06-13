package org.renci.hearsay.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.ReferenceGenomeDAO;
import org.renci.hearsay.dao.model.ReferenceGenome;
import org.renci.hearsay.dao.model.Transcript;
import org.renci.hearsay.ws.ReferenceGenomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReferenceGenomeServiceImpl implements ReferenceGenomeService {

    private final Logger logger = LoggerFactory.getLogger(ReferenceGenomeServiceImpl.class);

    private ReferenceGenomeDAO referenceGenomeDAO;

    @Override
    public ReferenceGenome findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        ReferenceGenome ret = null;
        try {
            ret = referenceGenomeDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(ReferenceGenome referenceGenome) {
        logger.debug("ENTERING save(ReferenceGenome)");
        Long ret = null;
        try {
            ret = referenceGenomeDAO.save(referenceGenome);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ReferenceGenomeDAO getReferenceGenomeDAO() {
        return referenceGenomeDAO;
    }

    public void setReferenceGenomeDAO(ReferenceGenomeDAO referenceGenomeDAO) {
        this.referenceGenomeDAO = referenceGenomeDAO;
    }

}
