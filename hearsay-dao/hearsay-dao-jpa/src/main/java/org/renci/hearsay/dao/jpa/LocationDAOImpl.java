package org.renci.hearsay.dao.jpa;

import javax.transaction.Transactional;

import org.renci.hearsay.dao.LocationDAO;
import org.renci.hearsay.dao.model.Location;

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
