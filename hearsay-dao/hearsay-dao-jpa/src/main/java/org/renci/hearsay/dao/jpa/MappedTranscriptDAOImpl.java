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
import org.renci.hearsay.dao.MappedTranscriptDAO;
import org.renci.hearsay.dao.model.MappedTranscript;
import org.renci.hearsay.dao.model.MappedTranscript_;
import org.renci.hearsay.dao.model.Transcript;
import org.renci.hearsay.dao.model.Transcript_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MappedTranscriptDAOImpl extends BaseEntityDAOImpl<MappedTranscript, Long> implements MappedTranscriptDAO {

    private final Logger logger = LoggerFactory.getLogger(MappedTranscriptDAOImpl.class);

    public MappedTranscriptDAOImpl() {
        super();
    }

    @Override
    public Class<MappedTranscript> getPersistentClass() {
        return MappedTranscript.class;
    }

    @Override
    public List<MappedTranscript> findByTranscriptId(Long transcriptId) throws HearsayDAOException {
        logger.debug("ENTERING findByTranscriptId(Long)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MappedTranscript> crit = critBuilder.createQuery(getPersistentClass());
        Root<MappedTranscript> fromTranscriptInterval = crit.from(MappedTranscript.class);
        Join<MappedTranscript, Transcript> fromTranscript = fromTranscriptInterval.join(MappedTranscript_.transcript);
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(critBuilder.equal(fromTranscript.get(Transcript_.id), transcriptId));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<MappedTranscript> query = getEntityManager().createQuery(crit);
        List<MappedTranscript> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<MappedTranscript> findByExample(MappedTranscript t) throws HearsayDAOException {
        logger.debug("ENTERING findByExample(TranscriptInterval)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MappedTranscript> crit = critBuilder.createQuery(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();

        Root<MappedTranscript> fromTranscriptInterval = crit.from(MappedTranscript.class);

        if (t.getRegionType() != null) {
            predicates.add(critBuilder.equal(fromTranscriptInterval.get(MappedTranscript_.regionType),
                    t.getRegionType()));
        }

        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<MappedTranscript> query = getEntityManager().createQuery(crit);
        List<MappedTranscript> ret = query.getResultList();
        return ret;
    }

}
