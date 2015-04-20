package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.GenomicReferenceSequenceDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GenomicReferenceSequence;
import org.renci.hearsay.ws.GenomicReferenceSequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenomicReferenceSequenceServiceImpl implements GenomicReferenceSequenceService {

    private final Logger logger = LoggerFactory.getLogger(GenomicReferenceSequenceServiceImpl.class);

    private GenomicReferenceSequenceDAO genomicReferenceSequenceDAO;

    public GenomicReferenceSequenceServiceImpl() {
        super();
    }

    @Override
    public GenomicReferenceSequence findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        GenomicReferenceSequence ret = null;
        try {
            ret = genomicReferenceSequenceDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public GenomicReferenceSequenceDAO getGenomicReferenceSequenceDAO() {
        return genomicReferenceSequenceDAO;
    }

    public void setGenomicReferenceSequenceDAO(GenomicReferenceSequenceDAO genomicReferenceSequenceDAO) {
        this.genomicReferenceSequenceDAO = genomicReferenceSequenceDAO;
    }

}
