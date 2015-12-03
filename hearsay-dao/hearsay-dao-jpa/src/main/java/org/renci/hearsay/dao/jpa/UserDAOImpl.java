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

import org.apache.commons.collections4.CollectionUtils;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.UserDAO;
import org.renci.hearsay.dao.model.Role;
import org.renci.hearsay.dao.model.Role_;
import org.renci.hearsay.dao.model.User;
import org.renci.hearsay.dao.model.User_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OsgiServiceProvider(classes = { UserDAO.class })
@Singleton
@Transactional
public class UserDAOImpl extends BaseEntityDAOImpl<User, Long> implements UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    public UserDAOImpl() {
        super();
    }

    @Override
    public Class<User> getPersistentClass() {
        return User.class;
    }

    @Override
    public List<User> findByRole(String name) throws HearsayDAOException {
        logger.debug("ENTERING findByRole(String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> crit = critBuilder.createQuery(getPersistentClass());
        Root<User> fromUser = crit.from(User.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        Join<User, Role> userRolesJoin = fromUser.join(User_.roles);
        predicates.add(critBuilder.equal(userRolesJoin.get(Role_.name), name));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<User> query = getEntityManager().createQuery(crit);
        List<User> ret = query.getResultList();
        return ret;
    }

    @Override
    public User findByUsername(String name) throws HearsayDAOException {
        logger.debug("ENTERING findByUsername(String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> crit = critBuilder.createQuery(getPersistentClass());
        Root<User> fromUser = crit.from(User.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(critBuilder.equal(fromUser.get(User_.username), name));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<User> query = getEntityManager().createQuery(crit);
        List<User> ret = query.getResultList();
        if (CollectionUtils.isNotEmpty(ret)) {
            return ret.get(0);
        }
        return null;
    }

}
