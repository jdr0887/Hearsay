package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.ContextualAllele;

public interface ContextualAlleleDAO extends BaseEntityDAO<ContextualAllele, Long> {

    public List<ContextualAllele> findByName(String name) throws HearsayDAOException;

}
