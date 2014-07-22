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
import org.renci.hearsay.dao.model.MappedTranscript;
import org.renci.hearsay.dao.model.ReferenceGenome;
import org.renci.hearsay.dao.model.ReferenceGenome_;
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
    public List<ReferenceSequence> findByAccession(String accession) throws HearsayDAOException {
        logger.debug("ENTERING findByAccession(String)");
        TypedQuery<ReferenceSequence> query = getEntityManager().createNamedQuery("ReferenceSequence.findByAccession",
                ReferenceSequence.class);
        query.setParameter("accession", accession);
        return query.getResultList();
    }

    @Override
    public List<ReferenceSequence> findByReferenceGenomeId(Long id) throws HearsayDAOException {
        logger.debug("ENTERING findByReferenceGenomeId(Long)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ReferenceSequence> crit = critBuilder.createQuery(getPersistentClass());
        Root<ReferenceSequence> fromReferenceSequence = crit.from(ReferenceSequence.class);
        Join<ReferenceSequence, ReferenceGenome> fromReferenceGenome = fromReferenceSequence
                .join(ReferenceSequence_.referenceGenomes);
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(critBuilder.equal(fromReferenceGenome.get(ReferenceGenome_.id), id));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<ReferenceSequence> query = getEntityManager().createQuery(crit);
        List<ReferenceSequence> ret = query.getResultList();
        return ret;
    }

}
