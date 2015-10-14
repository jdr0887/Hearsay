package org.renci.hearsay.dao.jpa;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.LocationDAO;
import org.renci.hearsay.dao.model.Location;

@OsgiServiceProvider(classes = { LocationDAO.class })
@Singleton
@Transactional
public class LocationDAOImpl extends BaseEntityDAOImpl<Location, Long> implements LocationDAO {

    public LocationDAOImpl() {
        super();
    }

    @Override
    public Class<Location> getPersistentClass() {
        return Location.class;
    }

}
