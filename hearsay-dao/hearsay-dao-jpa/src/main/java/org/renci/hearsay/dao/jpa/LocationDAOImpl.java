package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.LocationDAO;
import org.renci.hearsay.dao.model.Location;

public class LocationDAOImpl extends BaseEntityDAOImpl<Location, Long> implements LocationDAO {

    public LocationDAOImpl() {
        super();
    }

    @Override
    public Class<Location> getPersistentClass() {
        return Location.class;
    }

}
