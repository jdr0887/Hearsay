package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.ComplexProteinCanonicalAlleleDAO;
import org.renci.hearsay.dao.model.ComplexProteinCanonicalAllele;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComplexProteinCanonicalAlleleDAOImpl extends BaseEntityDAOImpl<ComplexProteinCanonicalAllele, Long>
        implements ComplexProteinCanonicalAlleleDAO {

    private final Logger logger = LoggerFactory.getLogger(ComplexProteinCanonicalAlleleDAOImpl.class);

    public ComplexProteinCanonicalAlleleDAOImpl() {
        super();
    }

    @Override
    public Class<ComplexProteinCanonicalAllele> getPersistentClass() {
        return ComplexProteinCanonicalAllele.class;
    }

}
