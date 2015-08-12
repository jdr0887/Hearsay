package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.GeneSymbolDAO;
import org.renci.hearsay.dao.model.GeneSymbol;

public class GeneSymbolDAOImpl extends BaseEntityDAOImpl<GeneSymbol, Long> implements GeneSymbolDAO {

    public GeneSymbolDAOImpl() {
        super();
    }

    @Override
    public Class<GeneSymbol> getPersistentClass() {
        return GeneSymbol.class;
    }

}
