package org.renci.hearsay.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranscriptDAO;
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
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Transcript> crit = critBuilder.createQuery(getPersistentClass());
        TypedQuery<Transcript> query = getEntityManager().createQuery(crit);
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

        if (StringUtils.isNotEmpty(t.getGeneName())) {
            predicates.add(critBuilder.equal(fromTranscript.get(Transcript_.geneName), t.getGeneName()));
        }

        if (StringUtils.isNotEmpty(t.getHgncSymbol())) {
            predicates.add(critBuilder.equal(fromTranscript.get(Transcript_.hgncSymbol), t.getHgncSymbol()));
        }

        if (StringUtils.isNotEmpty(t.getProteinRefSeqAccession())) {
            predicates.add(critBuilder.equal(fromTranscript.get(Transcript_.proteinRefSeqAccession),
                    t.getProteinRefSeqAccession()));
        }

        if (t.getStrandType() != null) {
            predicates.add(critBuilder.equal(fromTranscript.get(Transcript_.strandType), t.getStrandType()));
        }

        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Transcript> query = getEntityManager().createQuery(crit);
        List<Transcript> ret = query.getResultList();
        return ret;
    }

}