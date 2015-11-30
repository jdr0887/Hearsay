package org.renci.hearsay.dao.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.renci.hearsay.dao.BaseEntityDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.Persistable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseEntityDAOImpl<T extends Persistable, ID extends Serializable> implements BaseEntityDAO<T, ID> {

    private static final Logger logger = LoggerFactory.getLogger(BaseEntityDAOImpl.class);

    @PersistenceContext(name = "hearsay", unitName = "hearsay")
    private EntityManager entityManager;

    public BaseEntityDAOImpl() {
        super();
    }

    public abstract Class<T> getPersistentClass();

    @Override
    public Long save(T entity) throws HearsayDAOException {
        logger.debug("ENTERING save(T)");
        if (!entityManager.contains(entity) && entity.getId() != null) {
            entity = entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
        }
        return entity.getId();
    }

    @Override
    public void delete(T entity) throws HearsayDAOException {
        logger.debug("ENTERING delete(T)");
        T foundEntity = entityManager.find(getPersistentClass(), entity.getId());
        entityManager.remove(foundEntity);
    }

    @Override
    public void delete(List<T> entityList) throws HearsayDAOException {
        logger.debug("ENTERING delete(List<T>)");
        List<Long> idList = new ArrayList<Long>();
        for (T t : entityList) {
            idList.add(t.getId());
        }
        Query qDelete = entityManager.createQuery("delete from " + getPersistentClass().getSimpleName() + " a where a.id in (?1)");
        qDelete.setParameter(1, idList);
        qDelete.executeUpdate();
    }

    @Override
    public T findById(ID id) throws HearsayDAOException {
        logger.debug("ENTERING findById(T)");
        T ret = entityManager.find(getPersistentClass(), id);
        return ret;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
