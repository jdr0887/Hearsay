package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.RegionDAO;
import org.renci.hearsay.dao.model.Region;
import org.renci.hearsay.ws.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegionServiceImpl implements RegionService {

    private final Logger logger = LoggerFactory.getLogger(RegionServiceImpl.class);

    private RegionDAO regionDAO;

    @Override
    public Region findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        Region ret = null;
        try {
            ret = regionDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Long save(Region region) {
        logger.debug("ENTERING save(Region)");
        Long ret = null;
        try {
            ret = regionDAO.save(region);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public RegionDAO getRegionDAO() {
        return regionDAO;
    }

    public void setRegionDAO(RegionDAO regionDAO) {
        this.regionDAO = regionDAO;
    }

}
