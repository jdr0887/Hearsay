package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.RegionDAO;
import org.renci.hearsay.dao.model.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegionDAOImpl extends BaseEntityDAOImpl<Region, Long> implements RegionDAO {

    private final Logger logger = LoggerFactory.getLogger(RegionDAOImpl.class);

    public RegionDAOImpl() {
        super();
    }

    @Override
    public Class<Region> getPersistentClass() {
        return Region.class;
    }

}
