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
import org.renci.hearsay.dao.GeneDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.Gene_;
import org.renci.hearsay.dao.model.Identifier;
import org.renci.hearsay.dao.model.Identifier_;
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
    public List<Gene> findByIdentifierValue(String value) throws HearsayDAOException {
        logger.debug("ENTERING findByIdentifierValue(String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Gene> crit = critBuilder.createQuery(getPersistentClass());
        Root<Gene> fromGene = crit.from(Gene.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        Join<Gene, Identifier> geneIdentifierJoin = fromGene.join(Gene_.identifiers);
        predicates.add(critBuilder.like(geneIdentifierJoin.get(Identifier_.value), value));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        crit.orderBy(critBuilder.asc(fromGene.get(Gene_.symbol)));
        TypedQuery<Gene> query = getEntityManager().createQuery(crit);
        List<Gene> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<Gene> findBySymbol(String symbol) throws HearsayDAOException {
        logger.debug("ENTERING findBySymbol(Gene)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Gene> crit = critBuilder.createQuery(getPersistentClass());
        Root<Gene> fromGene = crit.from(Gene.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(critBuilder.like(fromGene.get(Gene_.symbol), symbol));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Gene> query = getEntityManager().createQuery(crit);
        List<Gene> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<Gene> findByExample(Gene gene) throws HearsayDAOException {
        logger.debug("ENTERING findByExample(Gene)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Gene> crit = critBuilder.createQuery(getPersistentClass());
        Root<Gene> fromGene = crit.from(Gene.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (StringUtils.isNotEmpty(gene.getSymbol())) {
            predicates.add(critBuilder.like(fromGene.get(Gene_.symbol), gene.getSymbol()));
        }
        if (StringUtils.isNotEmpty(gene.getDescription())) {
            predicates.add(critBuilder.like(fromGene.get(Gene_.description), gene.getDescription()));
        }
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Gene> query = getEntityManager().createQuery(crit);
        List<Gene> ret = query.getResultList();
        return ret;
    }

}
