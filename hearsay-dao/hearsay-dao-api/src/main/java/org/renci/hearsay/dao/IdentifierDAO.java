package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.Identifier;

public interface IdentifierDAO extends BaseEntityDAO<Identifier, Long> {

    public List<Identifier> findByExample(Identifier identifier) throws HearsayDAOException;

}
