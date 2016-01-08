package org.renci.hearsay.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.renci.hearsay.dao.GenomeReferenceDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GenomeReference;
import org.renci.hearsay.ws.GenomeReferenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenomeReferenceServiceImpl implements GenomeReferenceService {

    private final Logger logger = LoggerFactory.getLogger(GenomeReferenceServiceImpl.class);

    private GenomeReferenceDAO genomeReferenceDAO;

    public GenomeReferenceServiceImpl() {
        super();
    }

    @Override
    public GenomeReference findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        GenomeReference ret = null;
        try {
            ret = genomeReferenceDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<GenomeReference> findAll() {
        logger.debug("ENTERING findAll()");
        List<GenomeReference> ret = new ArrayList<GenomeReference>();
        try {
            ret.addAll(genomeReferenceDAO.findAll());
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public GenomeReferenceDAO getGenomeReferenceDAO() {
        return genomeReferenceDAO;
    }

    public void setGenomeReferenceDAO(GenomeReferenceDAO genomeReferenceDAO) {
        this.genomeReferenceDAO = genomeReferenceDAO;
    }

}
