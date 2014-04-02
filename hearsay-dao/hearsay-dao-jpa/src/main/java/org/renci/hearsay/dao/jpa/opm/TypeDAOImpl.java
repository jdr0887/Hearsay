package org.renci.hearsay.dao.jpa.opm;

import org.renci.hearsay.dao.jpa.BaseDAOImpl;
import org.renci.hearsay.dao.model.opm.Type;
import org.renci.hearsay.dao.opm.TypeDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TypeDAOImpl extends BaseDAOImpl<Type, Long> implements TypeDAO {

    private final Logger logger = LoggerFactory.getLogger(TypeDAOImpl.class);

    public TypeDAOImpl() {
        super();
    }

    @Override
    public Class<Type> getPersistentClass() {
        return Type.class;
    }

}
