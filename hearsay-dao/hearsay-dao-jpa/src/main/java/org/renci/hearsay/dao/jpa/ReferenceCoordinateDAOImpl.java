package org.renci.hearsay.dao.jpa;

import javax.transaction.Transactional;

import org.renci.hearsay.dao.ReferenceCoordinateDAO;
import org.renci.hearsay.dao.model.ReferenceCoordinate;

@Transactional
public class ReferenceCoordinateDAOImpl extends BaseEntityDAOImpl<ReferenceCoordinate, Long> implements ReferenceCoordinateDAO {

    public ReferenceCoordinateDAOImpl() {
        super();
    }

    @Override
    public Class<ReferenceCoordinate> getPersistentClass() {
        return ReferenceCoordinate.class;
    }

}
