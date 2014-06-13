package org.renci.hearsay.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranscriptDAO;
import org.renci.hearsay.dao.VariantDAO;
import org.renci.hearsay.dao.model.Transcript;
import org.renci.hearsay.dao.model.Variant;
import org.renci.hearsay.ws.VariantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VariantServiceImpl implements VariantService {

    private final Logger logger = LoggerFactory.getLogger(VariantServiceImpl.class);

    private VariantDAO variantDAO;

    @Override
    public Variant findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        Variant ret = null;
        try {
            ret = variantDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(Variant variant) {
        logger.debug("ENTERING save(Variant)");
        Long ret = null;
        try {
            ret = variantDAO.save(variant);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }


    public VariantDAO getVariantDAO() {
        return variantDAO;
    }

    public void setVariantDAO(VariantDAO variantDAO) {
        this.variantDAO = variantDAO;
    }

}
