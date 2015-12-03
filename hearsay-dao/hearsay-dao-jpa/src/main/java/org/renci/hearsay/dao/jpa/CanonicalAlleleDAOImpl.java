package org.renci.hearsay.dao.jpa;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.CanonicalAlleleDAO;
import org.renci.hearsay.dao.model.CanonicalAllele;

@OsgiServiceProvider(classes = { CanonicalAlleleDAO.class })
@Singleton
@Transactional
public class CanonicalAlleleDAOImpl extends BaseEntityDAOImpl<CanonicalAllele, Long> implements CanonicalAlleleDAO {

    public CanonicalAlleleDAOImpl() {
        super();
    }

    @Override
    public Class<CanonicalAllele> getPersistentClass() {
        return CanonicalAllele.class;
    }

}
