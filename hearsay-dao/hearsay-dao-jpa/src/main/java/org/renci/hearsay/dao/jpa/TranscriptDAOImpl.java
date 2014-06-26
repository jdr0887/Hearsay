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
import org.renci.hearsay.dao.TranscriptDAO;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.Gene_;
import org.renci.hearsay.dao.model.Transcript;
import org.renci.hearsay.dao.model.Transcript_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptDAOImpl extends BaseEntityDAOImpl<Transcript, Long> implements TranscriptDAO {

    private final Logger logger = LoggerFactory.getLogger(TranscriptDAOImpl.class);

    public TranscriptDAOImpl() {
        super();
    }

    @Override
    public Class<Transcript> getPersistentClass() {
        return Transcript.class;
    }

    @Override
    public List<Transcript> findAll() throws HearsayDAOException {
        logger.debug("ENTERING findAll()");
        TypedQuery<Transcript> query = getEntityManager().createNamedQuery("Transcript.findAll", Transcript.class);
        List<Transcript> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<Transcript> findByExample(Transcript t) throws HearsayDAOException {
        logger.debug("ENTERING findByExample(Transcript)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Transcript> crit = critBuilder.createQuery(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();

        Root<Transcript> fromTranscript = crit.from(Transcript.class);

        if (StringUtils.isNotEmpty(t.getAccession())) {
            predicates.add(critBuilder.equal(fromTranscript.get(Transcript_.accession), t.getAccession()));
        }

        if (t.getGene() != null && t.getGene().getId() != null) {
            Join<Transcript, Gene> transcriptGeneJoin = fromTranscript.join(Transcript_.gene);
            predicates.add(critBuilder.equal(transcriptGeneJoin.get(Gene_.id), t.getGene().getId()));
        }

        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Transcript> query = getEntityManager().createQuery(crit);
        List<Transcript> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<Transcript> findByGeneId(Long geneId) throws HearsayDAOException {
        logger.debug("ENTERING findByGeneId(Long)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Transcript> crit = critBuilder.createQuery(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();
        Root<Transcript> fromTranscript = crit.from(Transcript.class);
        Join<Transcript, Gene> transcriptGeneJoin = fromTranscript.join(Transcript_.gene);
        predicates.add(critBuilder.equal(transcriptGeneJoin.get(Gene_.id), geneId));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Transcript> query = getEntityManager().createQuery(crit);
        List<Transcript> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<Transcript> findByGeneName(String geneName) throws HearsayDAOException {
        logger.debug("ENTERING findByGeneName(String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Transcript> crit = critBuilder.createQuery(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();
        Root<Transcript> fromTranscript = crit.from(Transcript.class);
        Join<Transcript, Gene> transcriptGeneJoin = fromTranscript.join(Transcript_.gene);
        predicates.add(critBuilder.like(transcriptGeneJoin.get(Gene_.name), geneName));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Transcript> query = getEntityManager().createQuery(crit);
        List<Transcript> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<Transcript> findByAccession(String accession) throws HearsayDAOException {
        logger.debug("ENTERING findByAccession(String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Transcript> crit = critBuilder.createQuery(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();
        Root<Transcript> fromTranscript = crit.from(Transcript.class);
        predicates.add(critBuilder.like(fromTranscript.get(Transcript_.accession), accession));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Transcript> query = getEntityManager().createQuery(crit);
        List<Transcript> ret = query.getResultList();
        return ret;
    }

}
