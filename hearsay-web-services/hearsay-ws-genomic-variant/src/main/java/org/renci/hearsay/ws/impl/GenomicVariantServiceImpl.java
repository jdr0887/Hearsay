package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.GenomicVariantDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GenomicVariant;
import org.renci.hearsay.ws.GenomicVariantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenomicVariantServiceImpl implements GenomicVariantService {

    private final Logger logger = LoggerFactory.getLogger(GenomicVariantServiceImpl.class);

    private GenomicVariantDAO genomicVariantDAO;

    public GenomicVariantServiceImpl() {
        super();
    }

    @Override
    public GenomicVariant findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        GenomicVariant ret = null;
        try {
            ret = genomicVariantDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public GenomicVariantDAO getGenomicVariantDAO() {
        return genomicVariantDAO;
    }

    public void setGenomicVariantDAO(GenomicVariantDAO genomicVariantDAO) {
        this.genomicVariantDAO = genomicVariantDAO;
    }

}
