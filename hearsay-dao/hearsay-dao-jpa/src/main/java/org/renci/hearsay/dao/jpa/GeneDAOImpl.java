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

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.openjpa.persistence.OpenJPAPersistence;
import org.apache.openjpa.persistence.OpenJPAQuery;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.GeneDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Chromosome;
import org.renci.hearsay.dao.model.Chromosome_;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.Gene_;
import org.renci.hearsay.dao.model.Identifier;
import org.renci.hearsay.dao.model.Identifier_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OsgiServiceProvider(classes = { GeneDAO.class })
@Singleton
@Transactional(Transactional.TxType.SUPPORTS)
public class GeneDAOImpl extends BaseEntityDAOImpl<Gene, Long> implements GeneDAO {

    private static final Logger logger = LoggerFactory.getLogger(GeneDAOImpl.class);

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
        List<Gene> ret = new ArrayList<Gene>();
        try {
            TypedQuery<Gene> query = getEntityManager().createNamedQuery("Gene.findAll", Gene.class);
            OpenJPAQuery<Gene> openjpaQuery = OpenJPAPersistence.cast(query);
            openjpaQuery.getFetchPlan().addFetchGroup("withChromosomesAndAliases");
            ret.addAll(openjpaQuery.getResultList());
        } catch (Exception e) {
            throw new HearsayDAOException(e);
        }
        return ret;
    }

    @Override
    public List<Gene> findByIdentifierSystemAndValue(String system, String value) throws HearsayDAOException {
        logger.debug("ENTERING findByIdentifierValue(String)");
        List<Gene> ret = new ArrayList<Gene>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Gene> crit = critBuilder.createQuery(getPersistentClass());
            Root<Gene> fromGene = crit.from(Gene.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<Gene, Identifier> geneIdentifierJoin = fromGene.join(Gene_.identifiers);
            predicates.add(critBuilder.like(geneIdentifierJoin.get(Identifier_.system), system));
            predicates.add(critBuilder.like(geneIdentifierJoin.get(Identifier_.value), value));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            crit.orderBy(critBuilder.asc(fromGene.get(Gene_.symbol)));
            TypedQuery<Gene> query = getEntityManager().createQuery(crit);
            OpenJPAQuery<Gene> openjpaQuery = OpenJPAPersistence.cast(query);
            openjpaQuery.getFetchPlan().addFetchGroup("withChromosomesAndAliases");
            ret.addAll(openjpaQuery.getResultList());
        } catch (Exception e) {
            throw new HearsayDAOException(e);
        }
        return ret;
    }

    @Override
    public List<Gene> findBySymbol(String symbol) throws HearsayDAOException {
        logger.debug("ENTERING findBySymbol(Gene)");
        List<Gene> ret = new ArrayList<Gene>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Gene> crit = critBuilder.createQuery(getPersistentClass());
            Root<Gene> fromGene = crit.from(Gene.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(critBuilder.like(fromGene.get(Gene_.symbol), symbol));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<Gene> query = getEntityManager().createQuery(crit);
            OpenJPAQuery<Gene> openjpaQuery = OpenJPAPersistence.cast(query);
            openjpaQuery.getFetchPlan().addFetchGroup("withChromosomesAndAliases");
            ret.addAll(openjpaQuery.getResultList());
        } catch (Exception e) {
            throw new HearsayDAOException(e);
        }
        return ret;
    }

    @Override
    public List<Gene> findByExample(Gene gene) throws HearsayDAOException {
        logger.debug("ENTERING findByExample(Gene)");
        List<Gene> ret = new ArrayList<Gene>();
        try {
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

            if (CollectionUtils.isNotEmpty(gene.getChromosomes())) {
                Join<Gene, Chromosome> geneChromosomesJoin = fromGene.join(Gene_.chromosomes);
                for (Chromosome c : gene.getChromosomes()) {
                    predicates.add(critBuilder.equal(geneChromosomesJoin.get(Chromosome_.name), c.getName()));
                }
            }
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<Gene> query = getEntityManager().createQuery(crit);
            OpenJPAQuery<Gene> openjpaQuery = OpenJPAPersistence.cast(query);
            openjpaQuery.getFetchPlan().addFetchGroup("withChromosomesAndAliases");
            ret.addAll(openjpaQuery.getResultList());
        } catch (Exception e) {
            throw new HearsayDAOException(e);
        }
        return ret;
    }

}
