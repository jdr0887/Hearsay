package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.SimpleGenomicAlleleDAO;
import org.renci.hearsay.dao.model.SimpleGenomicAllele;
import org.renci.hearsay.ws.SimpleGenomicAlleleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleGenomicAlleleServiceImpl implements SimpleGenomicAlleleService {

    private final Logger logger = LoggerFactory.getLogger(SimpleGenomicAlleleServiceImpl.class);

    private SimpleGenomicAlleleDAO simpleGenomicAlleleDAO;

    public SimpleGenomicAlleleServiceImpl() {
        super();
    }

    @Override
    public SimpleGenomicAllele findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        SimpleGenomicAllele ret = null;
        try {
            ret = simpleGenomicAlleleDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public SimpleGenomicAlleleDAO getSimpleGenomicAlleleDAO() {
        return simpleGenomicAlleleDAO;
    }

    public void setSimpleGenomicAlleleDAO(SimpleGenomicAlleleDAO simpleGenomicAlleleDAO) {
        this.simpleGenomicAlleleDAO = simpleGenomicAlleleDAO;
    }

}
