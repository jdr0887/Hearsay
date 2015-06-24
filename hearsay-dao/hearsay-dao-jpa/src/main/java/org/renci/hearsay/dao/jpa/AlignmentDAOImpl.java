package org.renci.hearsay.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.renci.hearsay.dao.AlignmentDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Alignment;
import org.renci.hearsay.dao.model.Alignment_;
import org.renci.hearsay.dao.model.ReferenceSequence;
import org.renci.hearsay.dao.model.ReferenceSequence_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public List<Alignment> findByReferenceSequenceId(Long referenceSequenceId) throws HearsayDAOException {
        logger.debug("ENTERING findByReferenceSequenceId(Long)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Alignment> crit = critBuilder.createQuery(getPersistentClass());
        Root<Alignment> fromTranscriptInterval = crit.from(Alignment.class);
        Join<Alignment, ReferenceSequence> fromTranscript = fromTranscriptInterval.join(Alignment_.referenceSequence);
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(critBuilder.equal(fromTranscript.get(ReferenceSequence_.id), referenceSequenceId));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Alignment> query = getEntityManager().createQuery(crit);
        List<Alignment> ret = query.getResultList();
        return ret;
    }

}
