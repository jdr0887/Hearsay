package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.TranscriptReferenceCoordinateDAO;
import org.renci.hearsay.dao.model.TranscriptReferenceCoordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptReferenceCoordinateDAOImpl extends BaseEntityDAOImpl<TranscriptReferenceCoordinate, Long>
        implements TranscriptReferenceCoordinateDAO {

    private final Logger logger = LoggerFactory.getLogger(TranscriptReferenceCoordinateDAOImpl.class);

    public TranscriptReferenceCoordinateDAOImpl() {
        super();
    }

    @Override
    public Class<TranscriptReferenceCoordinate> getPersistentClass() {
        return TranscriptReferenceCoordinate.class;
    }

}
