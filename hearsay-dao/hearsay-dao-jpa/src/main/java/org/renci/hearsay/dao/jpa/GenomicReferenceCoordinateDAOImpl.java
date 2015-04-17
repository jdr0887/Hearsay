package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.GenomicReferenceCoordinateDAO;
import org.renci.hearsay.dao.model.GenomicReferenceCoordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenomicReferenceCoordinateDAOImpl extends BaseEntityDAOImpl<GenomicReferenceCoordinate, Long> implements
        GenomicReferenceCoordinateDAO {

    private final Logger logger = LoggerFactory.getLogger(GenomicReferenceCoordinateDAOImpl.class);

    public GenomicReferenceCoordinateDAOImpl() {
        super();
    }

    @Override
    public Class<GenomicReferenceCoordinate> getPersistentClass() {
        return GenomicReferenceCoordinate.class;
    }

}
