package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.ComplexNucleotideCanonicalAlleleDAO;
import org.renci.hearsay.dao.model.ComplexNucleotideCanonicalAllele;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComplexNucleotideCanonicalAlleleDAOImpl extends BaseEntityDAOImpl<ComplexNucleotideCanonicalAllele, Long>
        implements ComplexNucleotideCanonicalAlleleDAO {

    private final Logger logger = LoggerFactory.getLogger(ComplexNucleotideCanonicalAlleleDAOImpl.class);

    public ComplexNucleotideCanonicalAlleleDAOImpl() {
        super();
    }

    @Override
    public Class<ComplexNucleotideCanonicalAllele> getPersistentClass() {
        return ComplexNucleotideCanonicalAllele.class;
    }

}
