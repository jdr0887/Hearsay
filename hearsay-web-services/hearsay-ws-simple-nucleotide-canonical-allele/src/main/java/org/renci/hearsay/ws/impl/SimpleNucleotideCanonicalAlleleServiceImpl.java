package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.SimpleNucleotideCanonicalAlleleDAO;
import org.renci.hearsay.dao.model.SimpleNucleotideCanonicalAllele;
import org.renci.hearsay.ws.SimpleNucleotideCanonicalAlleleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleNucleotideCanonicalAlleleServiceImpl implements SimpleNucleotideCanonicalAlleleService {

    private final Logger logger = LoggerFactory.getLogger(SimpleNucleotideCanonicalAlleleServiceImpl.class);

    private SimpleNucleotideCanonicalAlleleDAO simpleNucleotideCanonicalAlleleDAO;

    public SimpleNucleotideCanonicalAlleleServiceImpl() {
        super();
    }

    @Override
    public SimpleNucleotideCanonicalAllele findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        SimpleNucleotideCanonicalAllele ret = null;
        try {
            ret = simpleNucleotideCanonicalAlleleDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public SimpleNucleotideCanonicalAlleleDAO getSimpleNucleotideCanonicalAlleleDAO() {
        return simpleNucleotideCanonicalAlleleDAO;
    }

    public void setSimpleNucleotideCanonicalAlleleDAO(
            SimpleNucleotideCanonicalAlleleDAO simpleNucleotideCanonicalAlleleDAO) {
        this.simpleNucleotideCanonicalAlleleDAO = simpleNucleotideCanonicalAlleleDAO;
    }

}
