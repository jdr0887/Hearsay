package org.renci.hearsay.dao.jpa;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.ContextualAlleleNameDAO;
import org.renci.hearsay.dao.model.ContextualAlleleName;

@OsgiServiceProvider(classes = { ContextualAlleleNameDAO.class })
@Singleton
@Transactional(Transactional.TxType.SUPPORTS)
public class ContextualAlleleNameDAOImpl extends BaseEntityDAOImpl<ContextualAlleleName, Long> implements ContextualAlleleNameDAO {

    public ContextualAlleleNameDAOImpl() {
        super();
    }

    @Override
    public Class<ContextualAlleleName> getPersistentClass() {
        return ContextualAlleleName.class;
    }

}
