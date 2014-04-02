package org.renci.hearsay.dao.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.renci.hearsay.dao.BaseDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Persistable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseDAOImpl<T extends Persistable, ID extends Serializable> implements BaseDAO<T, ID> {

    private final Logger logger = LoggerFactory.getLogger(BaseDAOImpl.class);

    @PersistenceUnit(name = "hearsay", unitName = "hearsay")
    private EntityManager entityManager;

    public BaseDAOImpl() {
        super();
    }

    public abstract Class<T> getPersistentClass();

    @Override
    public Long save(T entity) throws HearsayDAOException {
        logger.debug("ENTERING save(T)");
        if (!entityManager.contains(entity) && entity.getPrimaryKey() != null) {
            entity = entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
        }
        return entity.getPrimaryKey();
    }

    @Override
    public void delete(T entity) throws HearsayDAOException {
        logger.debug("ENTERING delete(T)");
        T foundEntity = entityManager.find(getPersistentClass(), entity.getPrimaryKey());
        entityManager.remove(foundEntity);
    }

    @Override
    public void delete(List<T> entityList) throws HearsayDAOException {
        logger.debug("ENTERING delete(List<T>)");
        List<Long> idList = new ArrayList<Long>();
        for (T t : entityList) {
            idList.add(t.getPrimaryKey());
        }
        Query qDelete = entityManager.createQuery("delete from " + getPersistentClass().getSimpleName()
                + " a where a.id in (?1)");
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
