package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.ReferenceCoordinateDAO;
import org.renci.hearsay.dao.model.ReferenceCoordinate;

public class ReferenceCoordinateDAOImpl extends BaseEntityDAOImpl<ReferenceCoordinate, Long> implements
        ReferenceCoordinateDAO {

    public ReferenceCoordinateDAOImpl() {
        super();
    }

    @Override
    public Class<ReferenceCoordinate> getPersistentClass() {
        return ReferenceCoordinate.class;
    }

}
