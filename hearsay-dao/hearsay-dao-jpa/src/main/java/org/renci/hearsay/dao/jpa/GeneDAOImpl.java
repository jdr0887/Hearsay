package org.renci.hearsay.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.renci.hearsay.dao.GeneDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.Gene_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneDAOImpl extends BaseEntityDAOImpl<Gene, Long> implements GeneDAO {

    private final Logger logger = LoggerFactory.getLogger(GeneDAOImpl.class);

    public GeneDAOImpl() {
        super();
    }

    @Override
    public Class<Gene> getPersistentClass() {
        return Gene.class;
    }

    @Override
    public List<Gene> findAll() throws HearsayDAOException {
        logger.debug("ENTERING findAll()");
        TypedQuery<Gene> query = getEntityManager().createNamedQuery("Gene.findAll", Gene.class);
        List<Gene> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<Gene> findByName(String name) throws HearsayDAOException {
        logger.debug("ENTERING findByName(Gene)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Gene> crit = critBuilder.createQuery(getPersistentClass());
        Root<Gene> fromGene = crit.from(Gene.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(critBuilder.like(fromGene.get(Gene_.name), name));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Gene> query = getEntityManager().createQuery(crit);
        List<Gene> ret = query.getResultList();
        return ret;
    }

}
