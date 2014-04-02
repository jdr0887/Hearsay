package org.renci.hearsay.dao.jpa.opm;

import org.renci.hearsay.dao.jpa.BaseDAOImpl;
import org.renci.hearsay.dao.model.opm.Value;
import org.renci.hearsay.dao.opm.ValueDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValueDAOImpl extends BaseDAOImpl<Value, Long> implements ValueDAO {

    private final Logger logger = LoggerFactory.getLogger(ValueDAOImpl.class);

    public ValueDAOImpl() {
        super();
    }

    @Override
    public Class<Value> getPersistentClass() {
        return Value.class;
    }

}
