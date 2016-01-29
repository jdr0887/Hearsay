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
import org.renci.hearsay.dao.ContextualAlleleDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.ContextualAllele;
import org.renci.hearsay.dao.model.ContextualAlleleName;
import org.renci.hearsay.dao.model.ContextualAlleleName_;
import org.renci.hearsay.dao.model.ContextualAllele_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OsgiServiceProvider(classes = { ContextualAlleleDAO.class })
@Singleton
@Transactional(Transactional.TxType.SUPPORTS)
public class ContextualAlleleDAOImpl extends BaseEntityDAOImpl<ContextualAllele, Long> implements ContextualAlleleDAO {

    private static final Logger logger = LoggerFactory.getLogger(ContextualAlleleDAOImpl.class);

    public ContextualAlleleDAOImpl() {
        super();
    }

    @Override
    public Class<ContextualAllele> getPersistentClass() {
        return ContextualAllele.class;
    }

    @Override
    public List<ContextualAllele> findByName(String name) throws HearsayDAOException {
        logger.debug("ENTERING findByName(String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ContextualAllele> crit = critBuilder.createQuery(getPersistentClass());
        Root<ContextualAllele> root = crit.from(ContextualAllele.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (!name.endsWith("%")) {
            name += "%";
        }
        Join<ContextualAllele, ContextualAlleleName> contextualAlleleContextualAlleleNameJoin = root.join(ContextualAllele_.alleleNames);
        predicates.add(critBuilder.like(contextualAlleleContextualAlleleNameJoin.get(ContextualAlleleName_.name), name));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<ContextualAllele> query = getEntityManager().createQuery(crit);
        List<ContextualAllele> ret = query.getResultList();
        return ret;
    }

}
