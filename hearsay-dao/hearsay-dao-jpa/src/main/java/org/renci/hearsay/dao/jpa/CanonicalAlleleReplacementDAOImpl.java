package org.renci.hearsay.dao.jpa;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.CanonicalAlleleDAO;
import org.renci.hearsay.dao.CanonicalAlleleReplacementDAO;
import org.renci.hearsay.dao.model.CanonicalAllele;
import org.renci.hearsay.dao.model.CanonicalAlleleReplacement;

@OsgiServiceProvider(classes = { CanonicalAlleleReplacementDAO.class })
@Singleton
@Transactional(Transactional.TxType.SUPPORTS)
public class CanonicalAlleleReplacementDAOImpl extends BaseEntityDAOImpl<CanonicalAlleleReplacement, Long> implements CanonicalAlleleReplacementDAO {

    public CanonicalAlleleReplacementDAOImpl() {
        super();
    }

    @Override
    public Class<CanonicalAlleleReplacement> getPersistentClass() {
        return CanonicalAlleleReplacement.class;
    }

}
