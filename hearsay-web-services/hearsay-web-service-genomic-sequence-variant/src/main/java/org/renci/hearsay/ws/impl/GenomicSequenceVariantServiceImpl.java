package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.GenomicVariantDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GenomicSequenceVariant;
import org.renci.hearsay.ws.GenomicSequenceVariantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenomicSequenceVariantServiceImpl implements GenomicSequenceVariantService {

    private final Logger logger = LoggerFactory.getLogger(GenomicSequenceVariantServiceImpl.class);

    private GenomicVariantDAO genomicSequenceVariantDAO;

    @Override
    public GenomicSequenceVariant findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        GenomicSequenceVariant ret = null;
        try {
            ret = genomicSequenceVariantDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(GenomicSequenceVariant genomicSequenceVariant) {
        logger.debug("ENTERING save(GenomicSequenceVariant)");
        Long ret = null;
        try {
            ret = genomicSequenceVariantDAO.save(genomicSequenceVariant);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public GenomicVariantDAO getGenomicSequenceVariantDAO() {
        return genomicSequenceVariantDAO;
    }

    public void setGenomicSequenceVariantDAO(GenomicVariantDAO genomicSequenceVariantDAO) {
        this.genomicSequenceVariantDAO = genomicSequenceVariantDAO;
    }

}
