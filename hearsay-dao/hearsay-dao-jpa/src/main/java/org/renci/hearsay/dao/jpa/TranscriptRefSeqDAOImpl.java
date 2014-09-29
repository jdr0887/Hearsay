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
import org.renci.hearsay.dao.model.TranscriptRefSeq;
import org.renci.hearsay.dao.model.TranscriptRefSeq_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptRefSeqDAOImpl extends BaseEntityDAOImpl<TranscriptRefSeq, Long> implements TranscriptRefSeqDAO {

    private final Logger logger = LoggerFactory.getLogger(TranscriptRefSeqDAOImpl.class);

    public TranscriptRefSeqDAOImpl() {
        super();
    }

    @Override
    public Class<TranscriptRefSeq> getPersistentClass() {
        return TranscriptRefSeq.class;
    }

    @Override
    public List<TranscriptRefSeq> findAll() throws HearsayDAOException {
        logger.debug("ENTERING findAll()");
        TypedQuery<TranscriptRefSeq> query = getEntityManager().createNamedQuery("TranscriptRefSeq.findAll",
                TranscriptRefSeq.class);
        List<TranscriptRefSeq> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<TranscriptRefSeq> findByExample(TranscriptRefSeq t) throws HearsayDAOException {
        logger.debug("ENTERING findByExample(Transcript)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TranscriptRefSeq> crit = critBuilder.createQuery(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();

        Root<TranscriptRefSeq> fromTranscript = crit.from(TranscriptRefSeq.class);

        if (StringUtils.isNotEmpty(t.getAccession())) {
            predicates.add(critBuilder.equal(fromTranscript.get(TranscriptRefSeq_.accession), t.getAccession()));
        }

        if (t.getGene() != null && t.getGene().getId() != null) {
            Join<TranscriptRefSeq, Gene> transcriptGeneJoin = fromTranscript.join(TranscriptRefSeq_.gene);
            predicates.add(critBuilder.equal(transcriptGeneJoin.get(Gene_.id), t.getGene().getId()));
        }

        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<TranscriptRefSeq> query = getEntityManager().createQuery(crit);
        List<TranscriptRefSeq> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<TranscriptRefSeq> findByGeneId(Long geneId) throws HearsayDAOException {
        logger.debug("ENTERING findByGeneId(Long)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TranscriptRefSeq> crit = critBuilder.createQuery(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();
        Root<TranscriptRefSeq> fromTranscript = crit.from(TranscriptRefSeq.class);
        Join<TranscriptRefSeq, Gene> transcriptGeneJoin = fromTranscript.join(TranscriptRefSeq_.gene);
        predicates.add(critBuilder.equal(transcriptGeneJoin.get(Gene_.id), geneId));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<TranscriptRefSeq> query = getEntityManager().createQuery(crit);
        List<TranscriptRefSeq> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<TranscriptRefSeq> findByGeneName(String geneName) throws HearsayDAOException {
        logger.debug("ENTERING findByGeneName(String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TranscriptRefSeq> crit = critBuilder.createQuery(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();
        Root<TranscriptRefSeq> fromTranscript = crit.from(TranscriptRefSeq.class);
        Join<TranscriptRefSeq, Gene> transcriptGeneJoin = fromTranscript.join(TranscriptRefSeq_.gene);
        predicates.add(critBuilder.like(transcriptGeneJoin.get(Gene_.name), geneName));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<TranscriptRefSeq> query = getEntityManager().createQuery(crit);
        List<TranscriptRefSeq> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<TranscriptRefSeq> findByAccession(String accession) throws HearsayDAOException {
        logger.debug("ENTERING findByAccession(String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TranscriptRefSeq> crit = critBuilder.createQuery(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();
        Root<TranscriptRefSeq> fromTranscript = crit.from(TranscriptRefSeq.class);
        predicates.add(critBuilder.like(fromTranscript.get(TranscriptRefSeq_.accession), accession));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<TranscriptRefSeq> query = getEntityManager().createQuery(crit);
        List<TranscriptRefSeq> ret = query.getResultList();
        return ret;
    }

}
