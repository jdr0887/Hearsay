package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.GeneSymbolDAO;
import org.renci.hearsay.dao.model.GeneSymbol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneSymbolDAOImpl extends BaseEntityDAOImpl<GeneSymbol, Long> implements GeneSymbolDAO {

    private final Logger logger = LoggerFactory.getLogger(GeneSymbolDAOImpl.class);

    public GeneSymbolDAOImpl() {
        super();
    }

    @Override
    public Class<GeneSymbol> getPersistentClass() {
        return GeneSymbol.class;
    }

}
