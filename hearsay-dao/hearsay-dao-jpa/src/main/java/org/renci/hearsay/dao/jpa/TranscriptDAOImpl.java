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
import org.renci.hearsay.dao.TranscriptRefSeqDAO;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.Gene_;
import org.renci.hearsay.dao.model.TranscriptSequence;
import org.renci.hearsay.dao.model.Transcript_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptDAOImpl extends BaseEntityDAOImpl<TranscriptSequence, Long> implements TranscriptRefSeqDAO {

    private final Logger logger = LoggerFactory.getLogger(TranscriptDAOImpl.class);

    public TranscriptDAOImpl() {
        super();
    }

    @Override
    public Class<TranscriptSequence> getPersistentClass() {
        return TranscriptSequence.class;
    }

    @Override
    public List<TranscriptSequence> findAll() throws HearsayDAOException {
        logger.debug("ENTERING findAll()");
        TypedQuery<TranscriptSequence> query = getEntityManager().createNamedQuery("Transcript.findAll", TranscriptSequence.class);
        List<TranscriptSequence> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<TranscriptSequence> findByExample(TranscriptSequence t) throws HearsayDAOException {
        logger.debug("ENTERING findByExample(Transcript)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TranscriptSequence> crit = critBuilder.createQuery(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();

        Root<TranscriptSequence> fromTranscript = crit.from(TranscriptSequence.class);

        if (StringUtils.isNotEmpty(t.getAccession())) {
            predicates.add(critBuilder.equal(fromTranscript.get(Transcript_.accession), t.getAccession()));
        }

        if (t.getGene() != null && t.getGene().getId() != null) {
            Join<TranscriptSequence, Gene> transcriptGeneJoin = fromTranscript.join(Transcript_.gene);
            predicates.add(critBuilder.equal(transcriptGeneJoin.get(Gene_.id), t.getGene().getId()));
        }

        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<TranscriptSequence> query = getEntityManager().createQuery(crit);
        List<TranscriptSequence> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<TranscriptSequence> findByGeneId(Long geneId) throws HearsayDAOException {
        logger.debug("ENTERING findByGeneId(Long)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TranscriptSequence> crit = critBuilder.createQuery(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();
        Root<TranscriptSequence> fromTranscript = crit.from(TranscriptSequence.class);
        Join<TranscriptSequence, Gene> transcriptGeneJoin = fromTranscript.join(Transcript_.gene);
        predicates.add(critBuilder.equal(transcriptGeneJoin.get(Gene_.id), geneId));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<TranscriptSequence> query = getEntityManager().createQuery(crit);
        List<TranscriptSequence> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<TranscriptSequence> findByGeneName(String geneName) throws HearsayDAOException {
        logger.debug("ENTERING findByGeneName(String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TranscriptSequence> crit = critBuilder.createQuery(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();
        Root<TranscriptSequence> fromTranscript = crit.from(TranscriptSequence.class);
        Join<TranscriptSequence, Gene> transcriptGeneJoin = fromTranscript.join(Transcript_.gene);
        predicates.add(critBuilder.like(transcriptGeneJoin.get(Gene_.name), geneName));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<TranscriptSequence> query = getEntityManager().createQuery(crit);
        List<TranscriptSequence> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<TranscriptSequence> findByAccession(String accession) throws HearsayDAOException {
        logger.debug("ENTERING findByAccession(String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TranscriptSequence> crit = critBuilder.createQuery(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();
        Root<TranscriptSequence> fromTranscript = crit.from(TranscriptSequence.class);
        predicates.add(critBuilder.like(fromTranscript.get(Transcript_.accession), accession));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<TranscriptSequence> query = getEntityManager().createQuery(crit);
        List<TranscriptSequence> ret = query.getResultList();
        return ret;
    }

}
