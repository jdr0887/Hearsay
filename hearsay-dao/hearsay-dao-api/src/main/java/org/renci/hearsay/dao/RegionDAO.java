package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.Region;

public interface RegionDAO extends BaseEntityDAO<Region, Long> {

    public List<Region> findByAlignmentId(Long alignmentId) throws HearsayDAOException;

}
