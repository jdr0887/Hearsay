package org.renci.hearsay.dao.rs;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.LocationDAO;
import org.renci.hearsay.dao.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @author jdr0887
 */
@Component
public class LocationDAOImpl extends BaseEntityDAOImpl<Location, Long> implements LocationDAO {

    private final Logger logger = LoggerFactory.getLogger(LocationDAOImpl.class);

    public LocationDAOImpl() {
        super(Location.class);
    }

    @Override
    public Long save(Location entity) throws HearsayDAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Location findById(Long id) throws HearsayDAOException {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Location location = client.path("findById/{id}", id).accept(MediaType.APPLICATION_JSON).get(Location.class);
        return location;
    }

}
