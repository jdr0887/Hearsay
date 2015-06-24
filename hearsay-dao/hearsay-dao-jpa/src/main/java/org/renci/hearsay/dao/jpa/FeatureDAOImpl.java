package org.renci.hearsay.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import org.renci.hearsay.dao.FeatureDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeatureDAOImpl extends BaseEntityDAOImpl<Feature, Long> implements FeatureDAO {

    private final Logger logger = LoggerFactory.getLogger(FeatureDAOImpl.class);

    public FeatureDAOImpl() {
        super();
    }

    @Override
    public Class<Feature> getPersistentClass() {
        return Feature.class;
    }

    @Override
    public List<Feature> findByReferenceSequenceId(Long referenceSequenceId) throws HearsayDAOException {
        logger.debug("ENTERING findByReferenceSequenceId(Long)");
        TypedQuery<Feature> query = getEntityManager().createNamedQuery("Feature.findByReferenceSequenceId",
                Feature.class);
        query.setParameter("referenceSequenceId", referenceSequenceId);
        List<Feature> ret = query.getResultList();
        return ret;
    }

}
