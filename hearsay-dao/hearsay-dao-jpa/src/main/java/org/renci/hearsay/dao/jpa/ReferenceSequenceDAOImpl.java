package org.renci.hearsay.dao.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.openjpa.persistence.OpenJPAPersistence;
import org.apache.openjpa.persistence.OpenJPAQuery;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.ReferenceSequenceDAO;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.Gene_;
import org.renci.hearsay.dao.model.GenomeReference;
import org.renci.hearsay.dao.model.GenomeReference_;
import org.renci.hearsay.dao.model.Identifier;
import org.renci.hearsay.dao.model.Identifier_;
import org.renci.hearsay.dao.model.ReferenceSequence;
import org.renci.hearsay.dao.model.ReferenceSequence_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OsgiServiceProvider(classes = { ReferenceSequenceDAO.class })
@Singleton
@Transactional(Transactional.TxType.SUPPORTS)
public class ReferenceSequenceDAOImpl extends BaseEntityDAOImpl<ReferenceSequence, Long> implements ReferenceSequenceDAO {

    private static final Logger logger = LoggerFactory.getLogger(ReferenceSequenceDAOImpl.class);

    public ReferenceSequenceDAOImpl() {
        super();
    }

    @Override
    public Class<ReferenceSequence> getPersistentClass() {
        return ReferenceSequence.class;
    }

    @Override
    public List<ReferenceSequence> findByGeneId(Long geneId) throws HearsayDAOException {
        logger.debug("ENTERING findByGeneId(Long)");
        List<ReferenceSequence> ret = new ArrayList<ReferenceSequence>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ReferenceSequence> crit = critBuilder.createQuery(getPersistentClass());
            Root<ReferenceSequence> fromReferenceSequence = crit.from(ReferenceSequence.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<ReferenceSequence, Gene> referenceSequenceGeneJoin = fromReferenceSequence.join(ReferenceSequence_.gene);
            predicates.add(critBuilder.equal(referenceSequenceGeneJoin.get(Gene_.id), geneId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<ReferenceSequence> query = getEntityManager().createQuery(crit);
            OpenJPAQuery<ReferenceSequence> openjpaQuery = OpenJPAPersistence.cast(query);
            openjpaQuery.getFetchPlan().addFetchGroup("includeManyToOnes");
            ret.addAll(openjpaQuery.getResultList());
        } catch (Exception e) {
            throw new HearsayDAOException(e);
        }
        return ret;
    }

    @Override
    public List<ReferenceSequence> findByExample(ReferenceSequence referenceSequence) throws HearsayDAOException {
        logger.debug("ENTERING findByExample(ReferenceSequence)");
        List<ReferenceSequence> ret = new ArrayList<ReferenceSequence>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ReferenceSequence> crit = critBuilder.createQuery(getPersistentClass());
            Root<ReferenceSequence> fromReferenceSequence = crit.from(ReferenceSequence.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (referenceSequence.getGene() != null) {
                Join<ReferenceSequence, Gene> referenceSequenceGeneJoin = fromReferenceSequence.join(ReferenceSequence_.gene);
                predicates.add(critBuilder.equal(referenceSequenceGeneJoin.get(Gene_.id), referenceSequence.getGene().getId()));
            }

            if (referenceSequence.getType() != null) {
                predicates.add(critBuilder.equal(fromReferenceSequence.get(ReferenceSequence_.type), referenceSequence.getType()));
            }

            if (referenceSequence.getStrandType() != null) {
                predicates.add(
                        critBuilder.equal(fromReferenceSequence.get(ReferenceSequence_.strandType), referenceSequence.getStrandType()));
            }

            if (referenceSequence.getGenomeReference() != null) {
                Join<ReferenceSequence, GenomeReference> referenceSequenceGenomeReferenceJoin = fromReferenceSequence
                        .join(ReferenceSequence_.genomeReference);
                predicates.add(critBuilder.equal(referenceSequenceGenomeReferenceJoin.get(GenomeReference_.id),
                        referenceSequence.getGenomeReference().getId()));
            }

            if (CollectionUtils.isNotEmpty(referenceSequence.getIdentifiers())) {
                Join<ReferenceSequence, Identifier> referenceSequenceIdentifierJoin = fromReferenceSequence
                        .join(ReferenceSequence_.identifiers);
                for (Identifier identifier : referenceSequence.getIdentifiers()) {
                    predicates.add(critBuilder.equal(referenceSequenceIdentifierJoin.get(Identifier_.id), identifier.getId()));
                }
            }

            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<ReferenceSequence> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new HearsayDAOException(e);
        }
        return ret;
    }

    @Override
    public List<ReferenceSequence> findByIdentifierSystemAndValue(String system, String value) throws HearsayDAOException {
        logger.debug("ENTERING findByIdentifierSystemAndValue(String, String)");
        List<ReferenceSequence> ret = new ArrayList<ReferenceSequence>();
        ret.addAll(findByIdentifierSystemAndValue("includeManyToOnes", system, value));
        return ret;
    }

    @Override
    public List<ReferenceSequence> findByIdentifierSystemAndValue(String fetchPlan, String system, String value)
            throws HearsayDAOException {
        logger.debug("ENTERING findByIdentifierSystemAndValue(String, String, String)");
        List<ReferenceSequence> ret = new ArrayList<ReferenceSequence>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ReferenceSequence> crit = critBuilder.createQuery(getPersistentClass());
            Root<ReferenceSequence> fromReferenceSequence = crit.from(ReferenceSequence.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<ReferenceSequence, Identifier> referenceSequenceIdentifierJoin = fromReferenceSequence
                    .join(ReferenceSequence_.identifiers);
            if (!value.endsWith("%")) {
                value += "%";
            }
            predicates.add(critBuilder.like(referenceSequenceIdentifierJoin.get(Identifier_.value), value));
            predicates.add(critBuilder.equal(referenceSequenceIdentifierJoin.get(Identifier_.system), system));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<ReferenceSequence> query = getEntityManager().createQuery(crit);
            OpenJPAQuery<ReferenceSequence> openjpaQuery = OpenJPAPersistence.cast(query);
            openjpaQuery.getFetchPlan().addFetchGroup(fetchPlan);
            ret.addAll(openjpaQuery.getResultList());
        } catch (Exception e) {
            throw new HearsayDAOException(e);
        }
        return ret;
    }

    @Override
    public List<ReferenceSequence> findByIdentifierSystem(String system) throws HearsayDAOException {
        logger.debug("ENTERING findByIdentifierSystem(String)");
        List<ReferenceSequence> ret = new ArrayList<ReferenceSequence>();
        ret.addAll(findByIdentifierSystem("includeManyToOnes", system));
        return ret;
    }

    @Override
    public List<ReferenceSequence> findByIdentifierSystem(String fetchPlan, String system) throws HearsayDAOException {
        logger.debug("ENTERING findByIdentifierSystem(String, String)");
        List<ReferenceSequence> ret = new ArrayList<ReferenceSequence>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ReferenceSequence> crit = critBuilder.createQuery(getPersistentClass());
            Root<ReferenceSequence> fromReferenceSequence = crit.from(ReferenceSequence.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<ReferenceSequence, Identifier> referenceSequenceIdentifierJoin = fromReferenceSequence
                    .join(ReferenceSequence_.identifiers);
            predicates.add(critBuilder.equal(referenceSequenceIdentifierJoin.get(Identifier_.system), system));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<ReferenceSequence> query = getEntityManager().createQuery(crit);
            OpenJPAQuery<ReferenceSequence> openjpaQuery = OpenJPAPersistence.cast(query);
            openjpaQuery.getFetchPlan().addFetchGroup(fetchPlan);
            ret.addAll(openjpaQuery.getResultList());
        } catch (Exception e) {
            throw new HearsayDAOException(e);
        }
        return ret;
    }

    @Override
    public List<ReferenceSequence> findByIdentifiers(List<Identifier> idList) throws HearsayDAOException {
        logger.debug("ENTERING findByIdentifiers(Identifier)");
        List<ReferenceSequence> ret = new ArrayList<ReferenceSequence>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ReferenceSequence> crit = critBuilder.createQuery(getPersistentClass());
            Root<ReferenceSequence> fromReferenceSequence = crit.from(ReferenceSequence.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            idList.forEach(a -> predicates.add(critBuilder.isMember(a, fromReferenceSequence.<Collection> get("identifiers"))));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.distinct(true);
            TypedQuery<ReferenceSequence> query = getEntityManager().createQuery(crit);
            OpenJPAQuery<ReferenceSequence> openjpaQuery = OpenJPAPersistence.cast(query);
            openjpaQuery.getFetchPlan().addFetchGroup("includeManyToOnes");
            ret.addAll(openjpaQuery.getResultList());
        } catch (Exception e) {
            throw new HearsayDAOException(e);
        }
        return ret;
    }

}
