package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.CanonicalAlleleDAO;
import org.renci.hearsay.dao.model.CanonicalAllele;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CanonicalAlleleDAOImpl extends BaseEntityDAOImpl<CanonicalAllele, Long> implements CanonicalAlleleDAO {

    private final Logger logger = LoggerFactory.getLogger(CanonicalAlleleDAOImpl.class);

    public CanonicalAlleleDAOImpl() {
        super();
    }

    @Override
    public Class<CanonicalAllele> getPersistentClass() {
        return CanonicalAllele.class;
    }

}
