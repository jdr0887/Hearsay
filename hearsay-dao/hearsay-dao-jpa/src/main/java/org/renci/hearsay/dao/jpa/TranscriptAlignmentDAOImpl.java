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
import org.renci.hearsay.dao.TranscriptAlignmentDAO;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.Gene_;
import org.renci.hearsay.dao.model.TranscriptAlignment;
import org.renci.hearsay.dao.model.TranscriptAlignment_;
import org.renci.hearsay.dao.model.TranscriptRefSeq;
import org.renci.hearsay.dao.model.TranscriptRefSeq_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptAlignmentDAOImpl extends BaseEntityDAOImpl<TranscriptAlignment, Long> implements
        TranscriptAlignmentDAO {

    private final Logger logger = LoggerFactory.getLogger(TranscriptAlignmentDAOImpl.class);

    public TranscriptAlignmentDAOImpl() {
        super();
    }

    @Override
    public Class<TranscriptAlignment> getPersistentClass() {
        return TranscriptAlignment.class;
    }

    @Override
    public List<TranscriptAlignment> findByTranscriptRefSeqId(Long transcriptId) throws HearsayDAOException {
        logger.debug("ENTERING findByTranscriptId(Long)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TranscriptAlignment> crit = critBuilder.createQuery(getPersistentClass());
        Root<TranscriptAlignment> fromTranscriptInterval = crit.from(TranscriptAlignment.class);
        Join<TranscriptAlignment, TranscriptRefSeq> fromTranscript = fromTranscriptInterval
                .join(TranscriptAlignment_.transcriptRefSeq);
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(critBuilder.equal(fromTranscript.get(TranscriptRefSeq_.id), transcriptId));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<TranscriptAlignment> query = getEntityManager().createQuery(crit);
        List<TranscriptAlignment> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<TranscriptAlignment> findByTranscriptAccession(String accession) throws HearsayDAOException {
        logger.debug("ENTERING findByTranscriptAccession(String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TranscriptAlignment> crit = critBuilder.createQuery(getPersistentClass());
        Root<TranscriptAlignment> fromTranscriptInterval = crit.from(TranscriptAlignment.class);
        Join<TranscriptAlignment, TranscriptSequence> fromTranscript = fromTranscriptInterval
                .join(MappedTranscript_.transcript);
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(critBuilder.equal(fromTranscript.get(Transcript_.accession), accession));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<TranscriptAlignment> query = getEntityManager().createQuery(crit);
        List<TranscriptAlignment> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<TranscriptAlignment> findByGeneName(String name) throws HearsayDAOException {
        logger.debug("ENTERING findByGeneName(String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TranscriptAlignment> crit = critBuilder.createQuery(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();
        Root<TranscriptAlignment> fromMappedTranscript = crit.from(TranscriptAlignment.class);
        Join<TranscriptAlignment, TranscriptSequence> transcriptJoin = fromMappedTranscript
                .join(MappedTranscript_.transcript);
        Join<TranscriptSequence, Gene> geneJoin = transcriptJoin.join(Transcript_.gene);
        predicates.add(critBuilder.equal(geneJoin.get(Gene_.name), name));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<TranscriptAlignment> query = getEntityManager().createQuery(crit);
        List<TranscriptAlignment> ret = query.getResultList();
        return ret;
    }

}
