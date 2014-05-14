package org.renci.hearsay.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranscriptDAO;
import org.renci.hearsay.dao.model.Transcript;
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

}
