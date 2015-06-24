package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.LocationDAO;
import org.renci.hearsay.dao.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocationDAOImpl extends BaseEntityDAOImpl<Location, Long> implements LocationDAO {

    private final Logger logger = LoggerFactory.getLogger(LocationDAOImpl.class);

    public LocationDAOImpl() {
        super();
    }

    @Override
    public Class<Location> getPersistentClass() {
        return Location.class;
    }

}
