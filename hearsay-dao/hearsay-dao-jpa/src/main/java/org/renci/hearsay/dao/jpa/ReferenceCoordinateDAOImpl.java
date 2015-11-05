package org.renci.hearsay.dao.jpa;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.ReferenceCoordinateDAO;
import org.renci.hearsay.dao.model.ReferenceCoordinate;

@OsgiServiceProvider(classes = { ReferenceCoordinateDAO.class })
@Singleton
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
