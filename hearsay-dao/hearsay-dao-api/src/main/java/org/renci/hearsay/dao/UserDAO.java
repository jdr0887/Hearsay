package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.User;

public interface UserDAO extends BaseEntityDAO<User, Long> {

    public List<User> findByRole(String role) throws HearsayDAOException;

}
