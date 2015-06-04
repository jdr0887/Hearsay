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
import org.renci.hearsay.dao.IdentifierDAO;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.Gene_;
import org.renci.hearsay.dao.model.Identifier;
import org.renci.hearsay.dao.model.Identifier_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdentifierDAOImpl extends BaseEntityDAOImpl<Identifier, Long> implements IdentifierDAO {

    private final Logger logger = LoggerFactory.getLogger(IdentifierDAOImpl.class);

    public IdentifierDAOImpl() {
        super();
    }

    @Override
    public Class<Identifier> getPersistentClass() {
        return Identifier.class;
    }

    @Override
    public List<Identifier> findByExample(Identifier identifier) throws HearsayDAOException {
        logger.debug("ENTERING findByExample(Identifier)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Identifier> crit = critBuilder.createQuery(getPersistentClass());
        Root<Identifier> fromIdentifier = crit.from(Identifier.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (StringUtils.isNotEmpty(identifier.getSystem())) {
            predicates.add(critBuilder.like(fromIdentifier.get(Identifier_.system), identifier.getSystem()));
        }
        if (StringUtils.isNotEmpty(identifier.getValue())) {
            predicates.add(critBuilder.like(fromIdentifier.get(Identifier_.value), identifier.getValue()));
        }
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Identifier> query = getEntityManager().createQuery(crit);
        List<Identifier> ret = query.getResultList();
        return ret;
    }

}
