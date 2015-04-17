package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.ComplexNucleotideCanonicalAlleleDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.ComplexNucleotideCanonicalAllele;
import org.renci.hearsay.ws.ComplexNucleotideCanonicalAlleleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComplexNucleotideCanonicalAlleleServiceImpl implements ComplexNucleotideCanonicalAlleleService {

    private final Logger logger = LoggerFactory.getLogger(ComplexNucleotideCanonicalAlleleServiceImpl.class);

    private ComplexNucleotideCanonicalAlleleDAO complexNucleotideCanonicalAlleleDAO;

    public ComplexNucleotideCanonicalAlleleServiceImpl() {
        super();
    }

    @Override
    public ComplexNucleotideCanonicalAllele findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        ComplexNucleotideCanonicalAllele ret = null;
        try {
            ret = complexNucleotideCanonicalAlleleDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ComplexNucleotideCanonicalAlleleDAO getComplexNucleotideCanonicalAlleleDAO() {
        return complexNucleotideCanonicalAlleleDAO;
    }

    public void setComplexNucleotideCanonicalAlleleDAO(
            ComplexNucleotideCanonicalAlleleDAO complexNucleotideCanonicalAlleleDAO) {
        this.complexNucleotideCanonicalAlleleDAO = complexNucleotideCanonicalAlleleDAO;
    }

}
