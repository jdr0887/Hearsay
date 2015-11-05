package org.renci.hearsay.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.DiagnosticResultDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.DiagnosticResult;
import org.renci.hearsay.dao.model.DiagnosticResult_;
import org.renci.hearsay.dao.model.Participant;
import org.renci.hearsay.dao.model.Participant_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OsgiServiceProvider(classes = { DiagnosticResultDAO.class })
@Singleton
@Transactional
public class DiagnosticResultDAOImpl extends BaseEntityDAOImpl<DiagnosticResult, Long> implements DiagnosticResultDAO {

    private final Logger logger = LoggerFactory.getLogger(DiagnosticResultDAOImpl.class);

    public DiagnosticResultDAOImpl() {
        super();
    }

    @Override
    public Class<DiagnosticResult> getPersistentClass() {
        return DiagnosticResult.class;
    }

    @Override
    public List<DiagnosticResult> findByParticipantName(String name) throws HearsayDAOException {
        logger.debug("ENTERING findByParticipantName(String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<DiagnosticResult> crit = critBuilder.createQuery(getPersistentClass());
        Root<DiagnosticResult> fromDiagnosticResult = crit.from(DiagnosticResult.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        Join<DiagnosticResult, Participant> diagnosticResultParticipantJoin = fromDiagnosticResult.join(DiagnosticResult_.participant);
        predicates.add(critBuilder.equal(diagnosticResultParticipantJoin.get(Participant_.name), name));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<DiagnosticResult> query = getEntityManager().createQuery(crit);
        List<DiagnosticResult> ret = query.getResultList();
        return ret;
    }

}
