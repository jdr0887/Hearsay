package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.Gene;

public interface GeneDAO extends BaseEntityDAO<Gene, Long> {

    public List<Gene> findAll() throws HearsayDAOException;

    public List<Gene> findBySymbol(String symbol) throws HearsayDAOException;

    public List<Gene> findByExample(Gene gene) throws HearsayDAOException;

}
