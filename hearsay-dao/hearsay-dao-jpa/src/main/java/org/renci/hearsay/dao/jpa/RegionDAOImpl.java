package org.renci.hearsay.dao.jpa;

import javax.transaction.Transactional;

import org.renci.hearsay.dao.RegionDAO;
import org.renci.hearsay.dao.model.Region;

@Transactional
public class RegionDAOImpl extends BaseEntityDAOImpl<Region, Long> implements RegionDAO {

    public RegionDAOImpl() {
        super();
    }

    @Override
    public Class<Region> getPersistentClass() {
        return Region.class;
    }

}
