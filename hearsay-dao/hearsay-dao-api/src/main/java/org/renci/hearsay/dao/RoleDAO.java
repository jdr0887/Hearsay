package org.renci.hearsay.dao;

import org.renci.hearsay.dao.model.Role;

public interface RoleDAO extends BaseEntityDAO<Role, Long> {

    public Role findByName(String name) throws HearsayDAOException;

}
