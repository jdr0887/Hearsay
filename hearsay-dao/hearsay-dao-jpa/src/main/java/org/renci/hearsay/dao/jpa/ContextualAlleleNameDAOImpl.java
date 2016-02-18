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

import org.apache.commons.lang3.StringUtils;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.ContextualAlleleNameDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.ContextualAlleleName;
import org.renci.hearsay.dao.model.ContextualAlleleName_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OsgiServiceProvider(classes = { ContextualAlleleNameDAO.class })
@Singleton
@Transactional(Transactional.TxType.SUPPORTS)
public class ContextualAlleleNameDAOImpl extends BaseEntityDAOImpl<ContextualAlleleName, Long> implements ContextualAlleleNameDAO {

    private final Logger logger = LoggerFactory.getLogger(ContextualAlleleNameDAOImpl.class);

    public ContextualAlleleNameDAOImpl() {
        super();
    }

    @Override
    public Class<ContextualAlleleName> getPersistentClass() {
        return ContextualAlleleName.class;
    }

    @Override
    public List<ContextualAlleleName> findByExample(ContextualAlleleName name) throws HearsayDAOException {
        logger.debug("ENTERING findByExample(ContextualAlleleName)");
        List<ContextualAlleleName> ret = new ArrayList<ContextualAlleleName>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ContextualAlleleName> crit = critBuilder.createQuery(getPersistentClass());
            Root<ContextualAlleleName> fromContextualAlleleName = crit.from(ContextualAlleleName.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (StringUtils.isNotEmpty(name.getName())) {
                predicates.add(critBuilder.like(fromContextualAlleleName.get(ContextualAlleleName_.name), name.getName()));
            }
            if (name.getType() != null) {
                predicates.add(critBuilder.equal(fromContextualAlleleName.get(ContextualAlleleName_.type), name.getType()));
            }
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<ContextualAlleleName> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new HearsayDAOException(e);
        }
        return ret;
    }

}
