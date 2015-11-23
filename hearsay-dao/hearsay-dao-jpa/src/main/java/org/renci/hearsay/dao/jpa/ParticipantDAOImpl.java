package org.renci.hearsay.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.ParticipantDAO;
import org.renci.hearsay.dao.model.Participant;
import org.renci.hearsay.dao.model.Participant_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParticipantDAOImpl extends BaseEntityDAOImpl<Participant, Long> implements ParticipantDAO {

    private final Logger logger = LoggerFactory.getLogger(ParticipantDAOImpl.class);

    public ParticipantDAOImpl() {
        super();
    }

    @Override
    public Class<Participant> getPersistentClass() {
        return Participant.class;
    }

    @Override
    public List<Participant> findByName(String name) throws HearsayDAOException {
        logger.debug("ENTERING findByParticipantName(String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Participant> crit = critBuilder.createQuery(getPersistentClass());
        Root<Participant> fromDiagnosticResult = crit.from(Participant.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (StringUtils.isNotEmpty(name) && !name.endsWith("%")) {
            name += name + "%";
        }
        predicates.add(critBuilder.like(fromDiagnosticResult.get(Participant_.name), name));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Participant> query = getEntityManager().createQuery(crit);
        List<Participant> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<Participant> findAll() throws HearsayDAOException {
        logger.debug("ENTERING findAll()");
        TypedQuery<Participant> query = getEntityManager().createNamedQuery("Participant.findAll", Participant.class);
        List<Participant> ret = query.getResultList();
        return ret;
    }

}
