package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.AlignmentDAO;
import org.renci.hearsay.dao.model.Alignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlignmentDAOImpl extends BaseEntityDAOImpl<Alignment, Long> implements AlignmentDAO {

    private final Logger logger = LoggerFactory.getLogger(AlignmentDAOImpl.class);

    public AlignmentDAOImpl() {
        super();
    }

    @Override
    public Class<Alignment> getPersistentClass() {
        return Alignment.class;
    }

}
