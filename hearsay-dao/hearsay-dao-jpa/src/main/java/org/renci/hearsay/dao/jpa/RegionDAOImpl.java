package org.renci.hearsay.dao.jpa;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.RegionDAO;
import org.renci.hearsay.dao.model.Region;

@OsgiServiceProvider(classes = { RegionDAO.class })
@Singleton
@Transactional(Transactional.TxType.SUPPORTS)
public class RegionDAOImpl extends BaseEntityDAOImpl<Region, Long> implements RegionDAO {

    public RegionDAOImpl() {
        super();
    }

    @Override
    public Class<Region> getPersistentClass() {
        return Region.class;
    }

}
