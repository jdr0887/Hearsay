package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.ComplexAminoAcidCanonicalAlleleDAO;
import org.renci.hearsay.dao.model.ComplexAminoAcidCanonicalAllele;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComplexAminoAcidCanonicalAlleleDAOImpl extends BaseEntityDAOImpl<ComplexAminoAcidCanonicalAllele, Long>
        implements ComplexAminoAcidCanonicalAlleleDAO {

    private final Logger logger = LoggerFactory.getLogger(ComplexAminoAcidCanonicalAlleleDAOImpl.class);

    public ComplexAminoAcidCanonicalAlleleDAOImpl() {
        super();
    }

    @Override
    public Class<ComplexAminoAcidCanonicalAllele> getPersistentClass() {
        return ComplexAminoAcidCanonicalAllele.class;
    }

}
