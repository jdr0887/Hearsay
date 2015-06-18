package org.renci.hearsay.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.ReferenceSequenceDAO;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.Gene_;
import org.renci.hearsay.dao.model.Identifier;
import org.renci.hearsay.dao.model.Identifier_;
import org.renci.hearsay.dao.model.ReferenceSequence;
import org.renci.hearsay.dao.model.ReferenceSequence_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReferenceSequenceDAOImpl extends BaseEntityDAOImpl<ReferenceSequence, Long> implements
        ReferenceSequenceDAO {

    private final Logger logger = LoggerFactory.getLogger(ReferenceSequenceDAOImpl.class);

    public ReferenceSequenceDAOImpl() {
        super();
    }

    @Override
    public Class<ReferenceSequence> getPersistentClass() {
        return ReferenceSequence.class;
    }

    @Override
    public List<ReferenceSequence> findAll() throws HearsayDAOException {
        logger.debug("ENTERING findAll()");
        TypedQuery<ReferenceSequence> query = getEntityManager().createNamedQuery("ReferenceSequence.findAll",
                ReferenceSequence.class);
        List<ReferenceSequence> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<ReferenceSequence> findByGeneId(Long geneId) throws HearsayDAOException {
        logger.debug("ENTERING findByGeneId(Long)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ReferenceSequence> crit = critBuilder.createQuery(getPersistentClass());
        Root<ReferenceSequence> fromReferenceSequence = crit.from(ReferenceSequence.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        Join<ReferenceSequence, Gene> referenceSequenceGeneJoin = fromReferenceSequence.join(ReferenceSequence_.gene);
        predicates.add(critBuilder.equal(referenceSequenceGeneJoin.get(Gene_.id), geneId));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<ReferenceSequence> query = getEntityManager().createQuery(crit);
        List<ReferenceSequence> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<ReferenceSequence> findByIdentifierValue(String value) throws HearsayDAOException {
        logger.debug("ENTERING findByIdentifierValue(String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ReferenceSequence> crit = critBuilder.createQuery(getPersistentClass());
        Root<ReferenceSequence> fromReferenceSequence = crit.from(ReferenceSequence.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        Join<ReferenceSequence, Identifier> referenceSequenceIdentifierJoin = fromReferenceSequence
                .join(ReferenceSequence_.identifiers);
        predicates.add(critBuilder.like(referenceSequenceIdentifierJoin.get(Identifier_.value), value));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<ReferenceSequence> query = getEntityManager().createQuery(crit);
        List<ReferenceSequence> ret = query.getResultList();
        return ret;
    }

}
