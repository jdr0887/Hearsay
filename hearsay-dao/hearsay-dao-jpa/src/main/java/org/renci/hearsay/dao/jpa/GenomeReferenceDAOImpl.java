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

import org.apache.commons.lang3.StringUtils;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.GenomeReferenceDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GenomeReference;
import org.renci.hearsay.dao.model.GenomeReference_;
import org.renci.hearsay.dao.model.Identifier;
import org.renci.hearsay.dao.model.Identifier_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OsgiServiceProvider(classes = { GenomeReferenceDAO.class })
@Singleton
@Transactional(Transactional.TxType.SUPPORTS)
public class GenomeReferenceDAOImpl extends BaseEntityDAOImpl<GenomeReference, Long> implements GenomeReferenceDAO {

    private final Logger logger = LoggerFactory.getLogger(GenomeReferenceDAOImpl.class);

    public GenomeReferenceDAOImpl() {
        super();
    }

    @Override
    public Class<GenomeReference> getPersistentClass() {
        return GenomeReference.class;
    }

    @Override
    public List<GenomeReference> findAll() throws HearsayDAOException {
        logger.debug("ENTERING findAll()");
        List<GenomeReference> ret = new ArrayList<GenomeReference>();
        try {
            TypedQuery<GenomeReference> query = getEntityManager().createNamedQuery("GenomeReference.findAll", GenomeReference.class);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new HearsayDAOException(e);
        }
        return ret;
    }

    @Override
    public List<GenomeReference> findByName(String name) throws HearsayDAOException {
        logger.debug("ENTERING findByName(String name)");
        List<GenomeReference> ret = new ArrayList<GenomeReference>();
        try {
            TypedQuery<GenomeReference> query = getEntityManager().createNamedQuery("GenomeReference.findByName", GenomeReference.class);
            query.setParameter("name", name);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new HearsayDAOException(e);
        }
        return ret;
    }

    @Override
    public List<GenomeReference> findByExample(GenomeReference genomeReference) throws HearsayDAOException {
        logger.debug("ENTERING findByExample(GenomeReference)");
        List<GenomeReference> ret = new ArrayList<GenomeReference>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<GenomeReference> crit = critBuilder.createQuery(getPersistentClass());
            Root<GenomeReference> root = crit.from(GenomeReference.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (StringUtils.isNotEmpty(genomeReference.getName())) {
                predicates.add(critBuilder.like(root.get(GenomeReference_.name), genomeReference.getName()));
            }
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<GenomeReference> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new HearsayDAOException(e);
        }
        return ret;
    }

    @Override
    public List<GenomeReference> findByIdentifierSystemAndValue(String system, String value) throws HearsayDAOException {
        logger.debug("ENTERING findByIdentifierValue(String)");
        List<GenomeReference> ret = new ArrayList<GenomeReference>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<GenomeReference> crit = critBuilder.createQuery(getPersistentClass());
            Root<GenomeReference> root = crit.from(GenomeReference.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<GenomeReference, Identifier> genomeReferenceIdentifierJoin = root.join(GenomeReference_.identifiers);
            predicates.add(critBuilder.like(genomeReferenceIdentifierJoin.get(Identifier_.system), system));
            predicates.add(critBuilder.like(genomeReferenceIdentifierJoin.get(Identifier_.value), value));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.orderBy(critBuilder.asc(root.get(GenomeReference_.name)));
            TypedQuery<GenomeReference> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new HearsayDAOException(e);
        }
        return ret;
    }

}
