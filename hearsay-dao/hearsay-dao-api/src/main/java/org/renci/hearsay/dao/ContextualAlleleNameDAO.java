package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.ContextualAlleleName;

public interface ContextualAlleleNameDAO extends BaseEntityDAO<ContextualAlleleName, Long> {

    public List<ContextualAlleleName> findByExample(ContextualAlleleName name) throws HearsayDAOException;

}
