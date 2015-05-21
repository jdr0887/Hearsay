package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.ReferenceCoordinateDAO;
import org.renci.hearsay.dao.model.ReferenceCoordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReferenceCoordinateDAOImpl extends BaseEntityDAOImpl<ReferenceCoordinate, Long> implements
        ReferenceCoordinateDAO {

    private final Logger logger = LoggerFactory.getLogger(ReferenceCoordinateDAOImpl.class);

    public ReferenceCoordinateDAOImpl() {
        super();
    }

    @Override
    public Class<ReferenceCoordinate> getPersistentClass() {
        return ReferenceCoordinate.class;
    }

}
