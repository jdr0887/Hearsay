package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.GeneSymbol;

public interface GeneSymbolDAO extends BaseEntityDAO<GeneSymbol, Long> {

    public List<GeneSymbol> findBySymbol(String symbol) throws HearsayDAOException;

}
