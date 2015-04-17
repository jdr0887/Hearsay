package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.SimpleNucleotideCanonicalAlleleDAO;
import org.renci.hearsay.dao.model.SimpleNucleotideCanonicalAllele;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleNucleotideCanonicalAlleleDAOImpl extends BaseEntityDAOImpl<SimpleNucleotideCanonicalAllele, Long>
        implements SimpleNucleotideCanonicalAlleleDAO {

    private final Logger logger = LoggerFactory.getLogger(SimpleNucleotideCanonicalAlleleDAOImpl.class);

    public SimpleNucleotideCanonicalAlleleDAOImpl() {
        super();
    }

    @Override
    public Class<SimpleNucleotideCanonicalAllele> getPersistentClass() {
        return SimpleNucleotideCanonicalAllele.class;
    }

}
