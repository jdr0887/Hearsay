package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.CanonicalAlleleDAO;
import org.renci.hearsay.dao.model.CanonicalAllele;

public class CanonicalAlleleDAOImpl extends BaseEntityDAOImpl<CanonicalAllele, Long> implements CanonicalAlleleDAO {

    public CanonicalAlleleDAOImpl() {
        super();
    }

    @Override
    public Class<CanonicalAllele> getPersistentClass() {
        return CanonicalAllele.class;
    }

}
