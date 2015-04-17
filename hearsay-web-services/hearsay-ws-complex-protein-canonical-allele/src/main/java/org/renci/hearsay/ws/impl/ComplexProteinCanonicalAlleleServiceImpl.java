package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.ComplexProteinCanonicalAlleleDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.CanonicalAllele;
import org.renci.hearsay.ws.CanonicalAlleleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComplexProteinCanonicalAlleleServiceImpl implements CanonicalAlleleService {

    private final Logger logger = LoggerFactory.getLogger(ComplexProteinCanonicalAlleleServiceImpl.class);

    private ComplexProteinCanonicalAlleleDAO complexProteinCanonicalAlleleDAO;

    public ComplexProteinCanonicalAlleleServiceImpl() {
        super();
    }

    @Override
    public CanonicalAllele findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        CanonicalAllele ret = null;
        try {
            ret = complexProteinCanonicalAlleleDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ComplexProteinCanonicalAlleleDAO getComplexProteinCanonicalAlleleDAO() {
        return complexProteinCanonicalAlleleDAO;
    }

    public void setComplexProteinCanonicalAlleleDAO(ComplexProteinCanonicalAlleleDAO complexProteinCanonicalAlleleDAO) {
        this.complexProteinCanonicalAlleleDAO = complexProteinCanonicalAlleleDAO;
    }

}
