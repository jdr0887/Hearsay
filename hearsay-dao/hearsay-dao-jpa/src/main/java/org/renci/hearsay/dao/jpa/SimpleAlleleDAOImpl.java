package org.renci.hearsay.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.SimpleAlleleDAO;
import org.renci.hearsay.dao.model.SimpleAllele;
import org.renci.hearsay.dao.model.SimpleAllele_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OsgiServiceProvider(classes = { SimpleAlleleDAO.class })
@Singleton
@Transactional
public class SimpleAlleleDAOImpl extends BaseEntityDAOImpl<SimpleAllele, Long> implements SimpleAlleleDAO {

    private final Logger logger = LoggerFactory.getLogger(SimpleAlleleDAOImpl.class);

    public SimpleAlleleDAOImpl() {
        super();
    }

    @Override
    public Class<SimpleAllele> getPersistentClass() {
        return SimpleAllele.class;
    }

    @Override
    public List<SimpleAllele> findByName(String name) throws HearsayDAOException {
        logger.debug("ENTERING findByName(String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<SimpleAllele> crit = critBuilder.createQuery(getPersistentClass());
        Root<SimpleAllele> fromSimpleAllele = crit.from(SimpleAllele.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (!name.endsWith("%")) {
            name += "%";
        }
        predicates.add(critBuilder.like(fromSimpleAllele.get(SimpleAllele_.name), name));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<SimpleAllele> query = getEntityManager().createQuery(crit);
        List<SimpleAllele> ret = query.getResultList();
        return ret;
    }

}
