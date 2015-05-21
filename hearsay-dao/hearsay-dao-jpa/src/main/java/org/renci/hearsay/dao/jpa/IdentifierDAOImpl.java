package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.IdentifierDAO;
import org.renci.hearsay.dao.model.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdentifierDAOImpl extends BaseEntityDAOImpl<Identifier, Long> implements IdentifierDAO {

    private final Logger logger = LoggerFactory.getLogger(IdentifierDAOImpl.class);

    public IdentifierDAOImpl() {
        super();
    }

    @Override
    public Class<Identifier> getPersistentClass() {
        return Identifier.class;
    }

}
