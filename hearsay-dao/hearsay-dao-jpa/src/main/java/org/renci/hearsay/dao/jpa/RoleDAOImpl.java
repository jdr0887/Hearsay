package org.renci.hearsay.dao.jpa;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.RoleDAO;
import org.renci.hearsay.dao.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OsgiServiceProvider(classes = { RoleDAO.class })
@Singleton
@Transactional
public class RoleDAOImpl extends BaseEntityDAOImpl<Role, Long> implements RoleDAO {

    private static final Logger logger = LoggerFactory.getLogger(RoleDAOImpl.class);

    public RoleDAOImpl() {
        super();
    }

    @Override
    public Class<Role> getPersistentClass() {
        return Role.class;
    }

    @Override
    public Role findByName(String name) throws HearsayDAOException {
        logger.debug("ENTERING findByName(String)");
        TypedQuery<Role> query = getEntityManager().createNamedQuery("Role.findByName", Role.class);
        query.setParameter("name", name);
        List<Role> results = query.getResultList();
        Role ret = null;
        if (CollectionUtils.isNotEmpty(results)) {
            ret = results.get(0);
        }
        return ret;
    }

}
