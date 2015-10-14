package org.renci.hearsay.dao.jpa;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.GeneSymbolDAO;
import org.renci.hearsay.dao.model.GeneSymbol;

@OsgiServiceProvider(classes = { GeneSymbolDAO.class })
@Singleton
@Transactional
public class GeneSymbolDAOImpl extends BaseEntityDAOImpl<GeneSymbol, Long> implements GeneSymbolDAO {

    public GeneSymbolDAOImpl() {
        super();
    }

    @Override
    public Class<GeneSymbol> getPersistentClass() {
        return GeneSymbol.class;
    }

}
