package org.renci.hearsay.dao;

import java.io.Serializable;
import java.util.List;

import org.renci.hearsay.dao.model.Persistable;

public interface BaseDAO<T extends Persistable, ID extends Serializable> {

    /**
     * 
     * @param entity
     */
    public abstract Long save(T entity) throws HearsayDAOException;

    /**
     * 
     * @param entity
     */
    public abstract void delete(T entity) throws HearsayDAOException;

    /**
     * 
     * @param id
     * @throws MaPSeqDAOException
     */
    public abstract void delete(List<T> idList) throws HearsayDAOException;

    /**
     * 
     * @param id
     * @return
     */
    public abstract T findById(ID id) throws HearsayDAOException;

}
