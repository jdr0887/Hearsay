package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.AminoAcidReferenceCoordinateDAO;
import org.renci.hearsay.dao.model.AminoAcidReferenceCoordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AminoAcidReferenceCoordinateDAOImpl extends BaseEntityDAOImpl<AminoAcidReferenceCoordinate, Long>
        implements AminoAcidReferenceCoordinateDAO {

    private final Logger logger = LoggerFactory.getLogger(AminoAcidReferenceCoordinateDAOImpl.class);

    public AminoAcidReferenceCoordinateDAOImpl() {
        super();
    }

    @Override
    public Class<AminoAcidReferenceCoordinate> getPersistentClass() {
        return AminoAcidReferenceCoordinate.class;
    }

}
