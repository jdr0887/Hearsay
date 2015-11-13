package org.renci.hearsay.dao.jpa;

import javax.transaction.Transactional;

import org.renci.hearsay.dao.GeneSymbolDAO;
import org.renci.hearsay.dao.model.GeneSymbol;

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
