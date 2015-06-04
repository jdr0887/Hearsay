package org.renci.hearsay.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.renci.hearsay.dao.GenomeReferenceDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GenomeReference;
import org.renci.hearsay.dao.model.GenomeReference_;
import org.renci.hearsay.dao.model.Identifier;
import org.renci.hearsay.dao.model.Identifier_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        TypedQuery<GenomeReference> query = getEntityManager().createNamedQuery("GenomeReference.findAll",
                GenomeReference.class);
        List<GenomeReference> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<GenomeReference> findByExample(GenomeReference genomeReference) throws HearsayDAOException {
        logger.debug("ENTERING findByExample(GenomeReference)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<GenomeReference> crit = critBuilder.createQuery(getPersistentClass());
        Root<GenomeReference> root = crit.from(GenomeReference.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (StringUtils.isNotEmpty(genomeReference.getName())) {
            predicates.add(critBuilder.like(root.get(GenomeReference_.name), genomeReference.getName()));
        }
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<GenomeReference> query = getEntityManager().createQuery(crit);
        List<GenomeReference> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<GenomeReference> findByIdentifierValue(String value) throws HearsayDAOException {
        logger.debug("ENTERING findByIdentifierValue(String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<GenomeReference> crit = critBuilder.createQuery(getPersistentClass());
        Root<GenomeReference> root = crit.from(GenomeReference.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        Join<GenomeReference, Identifier> genomeReferenceIdentifierJoin = root.join(GenomeReference_.identifiers);
        predicates.add(critBuilder.like(genomeReferenceIdentifierJoin.get(Identifier_.value), value));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        crit.orderBy(critBuilder.asc(root.get(GenomeReference_.name)));
        TypedQuery<GenomeReference> query = getEntityManager().createQuery(crit);
        List<GenomeReference> ret = query.getResultList();
        return ret;
    }

}
