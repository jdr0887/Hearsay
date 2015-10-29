package org.renci.hearsay.dao.jpa;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.RoleDAO;
import org.renci.hearsay.dao.model.Role;

@OsgiServiceProvider(classes = { RoleDAO.class })
@Singleton
@Transactional
public class RoleDAOImpl extends BaseEntityDAOImpl<Role, Long> implements RoleDAO {

    public RoleDAOImpl() {
        super();
    }

    @Override
    public Class<Role> getPersistentClass() {
        return Role.class;
    }

}
