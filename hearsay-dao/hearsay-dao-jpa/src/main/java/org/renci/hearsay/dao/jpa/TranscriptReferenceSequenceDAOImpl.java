package org.renci.hearsay.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranscriptReferenceSequenceDAO;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.Gene_;
import org.renci.hearsay.dao.model.TranscriptReferenceSequence;
import org.renci.hearsay.dao.model.TranscriptReferenceSequence_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptReferenceSequenceDAOImpl extends BaseEntityDAOImpl<TranscriptReferenceSequence, Long> implements
        TranscriptReferenceSequenceDAO {

    private final Logger logger = LoggerFactory.getLogger(TranscriptReferenceSequenceDAOImpl.class);

    public TranscriptReferenceSequenceDAOImpl() {
        super();
    }

    @Override
    public Class<TranscriptReferenceSequence> getPersistentClass() {
        return TranscriptReferenceSequence.class;
    }

    @Override
    public List<TranscriptReferenceSequence> findByExample(TranscriptReferenceSequence transcriptReferenceSequence)
            throws HearsayDAOException {
        logger.debug("ENTERING findByExample(TranscriptReferenceSequence)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TranscriptReferenceSequence> crit = critBuilder.createQuery(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();

        Root<TranscriptReferenceSequence> fromTranscriptReferenceSequence = crit
                .from(TranscriptReferenceSequence.class);

        if (StringUtils.isNotEmpty(transcriptReferenceSequence.getAccession())) {
            predicates.add(critBuilder.equal(
                    fromTranscriptReferenceSequence.get(TranscriptReferenceSequence_.accession),
                    transcriptReferenceSequence.getAccession()));
        }

        if (transcriptReferenceSequence.getGene() != null && transcriptReferenceSequence.getGene().getId() != null) {
            Join<TranscriptReferenceSequence, Gene> transcriptGeneJoin = fromTranscriptReferenceSequence
                    .join(TranscriptReferenceSequence_.gene);
            predicates.add(critBuilder.equal(transcriptGeneJoin.get(Gene_.id), transcriptReferenceSequence.getGene()
                    .getId()));
        }

        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<TranscriptReferenceSequence> query = getEntityManager().createQuery(crit);
        List<TranscriptReferenceSequence> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<TranscriptReferenceSequence> findByGeneId(Long geneId) throws HearsayDAOException {
        logger.debug("ENTERING findByGeneId(Long)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TranscriptReferenceSequence> crit = critBuilder.createQuery(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();
        Root<TranscriptReferenceSequence> fromTranscript = crit.from(TranscriptReferenceSequence.class);
        Join<TranscriptReferenceSequence, Gene> transcriptGeneJoin = fromTranscript
                .join(TranscriptReferenceSequence_.gene);
        predicates.add(critBuilder.equal(transcriptGeneJoin.get(Gene_.id), geneId));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<TranscriptReferenceSequence> query = getEntityManager().createQuery(crit);
        List<TranscriptReferenceSequence> ret = query.getResultList();
        return ret;
    }

}
