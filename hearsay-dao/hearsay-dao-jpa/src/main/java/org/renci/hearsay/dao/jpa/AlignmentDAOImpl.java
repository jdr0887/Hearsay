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

import org.apache.commons.collections.CollectionUtils;
import org.apache.openjpa.persistence.OpenJPAPersistence;
import org.apache.openjpa.persistence.OpenJPAQuery;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.AlignmentDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Alignment;
import org.renci.hearsay.dao.model.Alignment_;
import org.renci.hearsay.dao.model.ReferenceSequence;
import org.renci.hearsay.dao.model.ReferenceSequence_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OsgiServiceProvider(classes = { AlignmentDAO.class })
@Transactional(Transactional.TxType.SUPPORTS)
@Singleton
public class AlignmentDAOImpl extends BaseEntityDAOImpl<Alignment, Long> implements AlignmentDAO {

    private final Logger logger = LoggerFactory.getLogger(AlignmentDAOImpl.class);

    public AlignmentDAOImpl() {
        super();
    }

    @Override
    public Class<Alignment> getPersistentClass() {
        return Alignment.class;
    }

    @Override
    public List<Alignment> findAll() throws HearsayDAOException {
        logger.debug("ENTERING findAll()");
        TypedQuery<Alignment> query = getEntityManager().createNamedQuery("Alignment.findAll", Alignment.class);
        List<Alignment> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<Alignment> findByReferenceSequenceId(Long referenceSequenceId) throws HearsayDAOException {
        logger.debug("ENTERING findByReferenceSequenceId(Long)");
        List<Alignment> ret = new ArrayList<Alignment>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Alignment> crit = critBuilder.createQuery(getPersistentClass());
            Root<Alignment> fromAlignment = crit.from(Alignment.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<Alignment, ReferenceSequence> alignmentReferenceSequenceJoin = fromAlignment.join(Alignment_.referenceSequences);
            predicates.add(critBuilder.equal(alignmentReferenceSequenceJoin.get(ReferenceSequence_.id), referenceSequenceId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<Alignment> query = getEntityManager().createQuery(crit);
            OpenJPAQuery<Alignment> openjpaQuery = OpenJPAPersistence.cast(query);
            openjpaQuery.getFetchPlan().addFetchGroup("includeRegions");
            ret.addAll(openjpaQuery.getResultList());
        } catch (Exception e) {
            throw new HearsayDAOException(e);
        }
        return ret;
    }

    @Override
    public Alignment findById(Long id) throws HearsayDAOException {
        logger.debug("ENTERING findById(Long)");
        Alignment ret = null;
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Alignment> crit = critBuilder.createQuery(getPersistentClass());
            Root<Alignment> fromAlignment = crit.from(Alignment.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.equal(fromAlignment.get(Alignment_.id), id));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<Alignment> query = getEntityManager().createQuery(crit);
            OpenJPAQuery<Alignment> openjpaQuery = OpenJPAPersistence.cast(query);
            openjpaQuery.getFetchPlan().addFetchGroup("includeRegions");
            List<Alignment> results = openjpaQuery.getResultList();
            if (CollectionUtils.isNotEmpty(results)) {
                ret = results.get(0);
            }
        } catch (Exception e) {
            throw new HearsayDAOException(e);
        }
        return ret;
    }

}
