package org.renci.hearsay.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.FeatureDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Feature;
import org.renci.hearsay.dao.model.Feature_;
import org.renci.hearsay.dao.model.ReferenceSequence;
import org.renci.hearsay.dao.model.ReferenceSequence_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OsgiServiceProvider(classes = { FeatureDAO.class })
@Singleton
@Transactional
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
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Feature> crit = critBuilder.createQuery(getPersistentClass());
        Root<Feature> fromFeature = crit.from(Feature.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        Join<Feature, ReferenceSequence> featureReferenceSequenceJoin = fromFeature.join(Feature_.referenceSequences);
        predicates.add(critBuilder.equal(featureReferenceSequenceJoin.get(ReferenceSequence_.id), referenceSequenceId));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Feature> query = getEntityManager().createQuery(crit);
        List<Feature> ret = query.getResultList();
        return ret;
    }

}
