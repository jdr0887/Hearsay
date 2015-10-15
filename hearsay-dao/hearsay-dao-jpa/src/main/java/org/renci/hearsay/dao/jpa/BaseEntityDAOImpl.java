package org.renci.hearsay.dao.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.renci.hearsay.dao.BaseEntityDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.Persistable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseEntityDAOImpl<T extends Persistable, ID extends Serializable>
        implements BaseEntityDAO<T, ID> {

    private static final Logger logger = LoggerFactory.getLogger(BaseEntityDAOImpl.class);

    @PersistenceUnit(name = "hearsay", unitName = "hearsay")
    private EntityManager entityManager;

    public BaseEntityDAOImpl() {
        super();
    }

    public abstract Class<T> getPersistentClass();

    @Override
    @Transactional
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
    @Transactional
    public void delete(T entity) throws HearsayDAOException {
        logger.debug("ENTERING delete(T)");
        T foundEntity = entityManager.find(getPersistentClass(), entity.getId());
        entityManager.remove(foundEntity);
    }

    @Override
    @Transactional
    public void delete(List<T> entityList) throws HearsayDAOException {
        logger.debug("ENTERING delete(List<T>)");
        List<Long> idList = new ArrayList<Long>();
        for (T t : entityList) {
            idList.add(t.getId());
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> criteriaDelete = criteriaBuilder.createCriteriaDelete(getPersistentClass());
        Root<T> root = criteriaDelete.from(getPersistentClass());
        criteriaDelete.where(root.<Long> get("id").in(idList));
        entityManager.createQuery(criteriaDelete).executeUpdate();
    }

    @Override
    @Transactional
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
