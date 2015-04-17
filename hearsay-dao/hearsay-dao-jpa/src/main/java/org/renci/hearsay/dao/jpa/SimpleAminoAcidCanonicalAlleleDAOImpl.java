package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.SimpleAminoAcidCanonicalAlleleDAO;
import org.renci.hearsay.dao.model.SimpleAminoAcidCanonicalAllele;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleAminoAcidCanonicalAlleleDAOImpl extends BaseEntityDAOImpl<SimpleAminoAcidCanonicalAllele, Long>
        implements SimpleAminoAcidCanonicalAlleleDAO {

    private final Logger logger = LoggerFactory.getLogger(SimpleAminoAcidCanonicalAlleleDAOImpl.class);

    public SimpleAminoAcidCanonicalAlleleDAOImpl() {
        super();
    }

    @Override
    public Class<SimpleAminoAcidCanonicalAllele> getPersistentClass() {
        return SimpleAminoAcidCanonicalAllele.class;
    }

}
