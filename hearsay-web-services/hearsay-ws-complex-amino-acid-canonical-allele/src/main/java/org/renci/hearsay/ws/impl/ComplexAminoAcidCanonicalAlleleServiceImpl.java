package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.ComplexAminoAcidCanonicalAlleleDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.CanonicalAllele;
import org.renci.hearsay.ws.CanonicalAlleleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComplexAminoAcidCanonicalAlleleServiceImpl implements CanonicalAlleleService {

    private final Logger logger = LoggerFactory.getLogger(ComplexAminoAcidCanonicalAlleleServiceImpl.class);

    private ComplexAminoAcidCanonicalAlleleDAO complexAminoAcidCanonicalAlleleDAO;

    public ComplexAminoAcidCanonicalAlleleServiceImpl() {
        super();
    }

    @Override
    public CanonicalAllele findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        CanonicalAllele ret = null;
        try {
            ret = complexAminoAcidCanonicalAlleleDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ComplexAminoAcidCanonicalAlleleDAO getComplexAminoAcidCanonicalAlleleDAO() {
        return complexAminoAcidCanonicalAlleleDAO;
    }

    public void setComplexAminoAcidCanonicalAlleleDAO(
            ComplexAminoAcidCanonicalAlleleDAO complexAminoAcidCanonicalAlleleDAO) {
        this.complexAminoAcidCanonicalAlleleDAO = complexAminoAcidCanonicalAlleleDAO;
    }

}
