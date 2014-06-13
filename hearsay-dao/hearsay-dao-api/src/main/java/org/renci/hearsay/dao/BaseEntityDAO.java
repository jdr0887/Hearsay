package org.renci.hearsay.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseEntityDAO<T extends Persistable, ID extends Serializable> {

    public abstract Long save(T entity) throws HearsayDAOException;

    public abstract void delete(T entity) throws HearsayDAOException;

    public abstract void delete(List<T> idList) throws HearsayDAOException;

    public abstract T findById(ID id) throws HearsayDAOException;

}
