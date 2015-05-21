package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.IntronOffsetDAO;
import org.renci.hearsay.dao.model.IntronOffset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntronOffsetDAOImpl extends BaseEntityDAOImpl<IntronOffset, Long> implements IntronOffsetDAO {

    private final Logger logger = LoggerFactory.getLogger(IntronOffsetDAOImpl.class);

    public IntronOffsetDAOImpl() {
        super();
    }

    @Override
    public Class<IntronOffset> getPersistentClass() {
        return IntronOffset.class;
    }

}
