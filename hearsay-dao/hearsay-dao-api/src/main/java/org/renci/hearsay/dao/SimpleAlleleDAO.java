package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.SimpleAllele;

public interface SimpleAlleleDAO extends BaseEntityDAO<SimpleAllele, Long> {

    public List<SimpleAllele> findByName(String name) throws HearsayDAOException;

}
