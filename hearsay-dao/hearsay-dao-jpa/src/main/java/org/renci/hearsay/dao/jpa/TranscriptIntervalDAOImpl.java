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
import org.renci.hearsay.dao.TranscriptIntervalDAO;
import org.renci.hearsay.dao.model.Transcript;
import org.renci.hearsay.dao.model.TranscriptInterval;
import org.renci.hearsay.dao.model.TranscriptInterval_;
import org.renci.hearsay.dao.model.Transcript_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptIntervalDAOImpl extends BaseEntityDAOImpl<TranscriptInterval, Long> implements
        TranscriptIntervalDAO {

    private final Logger logger = LoggerFactory.getLogger(TranscriptIntervalDAOImpl.class);

    public TranscriptIntervalDAOImpl() {
        super();
    }

    @Override
    public Class<TranscriptInterval> getPersistentClass() {
        return TranscriptInterval.class;
    }

    @Override
    public List<TranscriptInterval> findByTranscriptId(Long transcriptId) throws HearsayDAOException {
        logger.debug("ENTERING findByTranscriptId(Long)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TranscriptInterval> crit = critBuilder.createQuery(getPersistentClass());
        Root<TranscriptInterval> fromTranscriptInterval = crit.from(TranscriptInterval.class);
        Join<TranscriptInterval, Transcript> fromTranscript = fromTranscriptInterval
                .join(TranscriptInterval_.transcript);
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(critBuilder.equal(fromTranscript.get(Transcript_.id), transcriptId));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<TranscriptInterval> query = getEntityManager().createQuery(crit);
        List<TranscriptInterval> ret = query.getResultList();
        return ret;
    }

}
