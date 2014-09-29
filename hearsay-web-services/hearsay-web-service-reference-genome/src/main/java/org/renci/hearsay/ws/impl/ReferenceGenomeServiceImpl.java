package org.renci.hearsay.ws.impl;

import java.util.List;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.GenomicRefSeqDAO;
import org.renci.hearsay.dao.model.ReferenceGenome;
import org.renci.hearsay.ws.ReferenceGenomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReferenceGenomeServiceImpl implements ReferenceGenomeService {

    private final Logger logger = LoggerFactory.getLogger(ReferenceGenomeServiceImpl.class);

    private GenomicRefSeqDAO referenceGenomeDAO;

    @Override
    public List<ReferenceGenome> findBySource(String source) {
        logger.debug("ENTERING findBySource(String)");
        List<ReferenceGenome> ret = null;
        try {
            ret = referenceGenomeDAO.findBySource(source);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<ReferenceGenome> findAll() {
        logger.debug("ENTERING findAll()");
        List<ReferenceGenome> ret = null;
        try {
            ret = referenceGenomeDAO.findAll();
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public ReferenceGenome findBySourceAndVersion(String source, String version) {
        logger.debug("ENTERING findBySourceAndVersion(String, String)");
        ReferenceGenome ret = null;
        try {
            ret = referenceGenomeDAO.findBySourceAndVersion(source, version);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

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

    public GenomicRefSeqDAO getReferenceGenomeDAO() {
        return referenceGenomeDAO;
    }

    public void setReferenceGenomeDAO(GenomicRefSeqDAO referenceGenomeDAO) {
        this.referenceGenomeDAO = referenceGenomeDAO;
    }

}
