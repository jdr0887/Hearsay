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
    public List<Feature> findByTranscriptRefSeqId(Long id) throws HearsayDAOException {
        logger.debug("ENTERING findAll()");
        TypedQuery<Feature> query = getEntityManager().createNamedQuery("Feature.findByTranscriptRefSeqId",
                Feature.class);
        query.setParameter("id", id);
        List<Feature> ret = query.getResultList();
        return ret;
    }

}
