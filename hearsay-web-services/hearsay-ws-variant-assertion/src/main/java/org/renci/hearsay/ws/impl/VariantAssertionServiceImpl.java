package org.renci.hearsay.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.VariantAssertionDAO;
import org.renci.hearsay.dao.model.VariantAssertion;
import org.renci.hearsay.ws.VariantAssertionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VariantAssertionServiceImpl implements VariantAssertionService {

    private final Logger logger = LoggerFactory.getLogger(VariantAssertionServiceImpl.class);

    private VariantAssertionDAO variantAssertionDAO;

    public VariantAssertionServiceImpl() {
        super();
    }

    @Override
    public List<VariantAssertion> findByGenomicVariantId(Long id) {
        logger.debug("ENTERING findById(Long)");
        List<VariantAssertion> ret = new ArrayList<VariantAssertion>();
        try {
            ret.addAll(variantAssertionDAO.findByGenomicVariantId(id));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public VariantAssertionDAO getVariantAssertionDAO() {
        return variantAssertionDAO;
    }

    public void setVariantAssertionDAO(VariantAssertionDAO variantAssertionDAO) {
        this.variantAssertionDAO = variantAssertionDAO;
    }

}
