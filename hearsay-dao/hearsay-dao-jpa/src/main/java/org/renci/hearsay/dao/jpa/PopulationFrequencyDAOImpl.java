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

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.PopulationFrequencyDAO;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.Gene_;
import org.renci.hearsay.dao.model.PopulationFrequency;
import org.renci.hearsay.dao.model.PopulationFrequency_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OsgiServiceProvider(classes = { PopulationFrequencyDAO.class })
@Singleton
@Transactional(Transactional.TxType.SUPPORTS)
public class PopulationFrequencyDAOImpl extends BaseEntityDAOImpl<PopulationFrequency, Long> implements PopulationFrequencyDAO {

    private final Logger logger = LoggerFactory.getLogger(PopulationFrequencyDAOImpl.class);

    public PopulationFrequencyDAOImpl() {
        super();
    }

    @Override
    public Class<PopulationFrequency> getPersistentClass() {
        return PopulationFrequency.class;
    }

    @Override
    public List<PopulationFrequency> findBySourceAndVersion(String source, String version) throws HearsayDAOException {
        logger.debug("ENTERING findBySourceAndVersion(String, String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<PopulationFrequency> crit = critBuilder.createQuery(getPersistentClass());
        Root<PopulationFrequency> fromPopulationFrequency = crit.from(PopulationFrequency.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(critBuilder.equal(fromPopulationFrequency.get(PopulationFrequency_.source), source));
        predicates.add(critBuilder.equal(fromPopulationFrequency.get(PopulationFrequency_.version), version));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<PopulationFrequency> query = getEntityManager().createQuery(crit);
        List<PopulationFrequency> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<PopulationFrequency> findByGeneId(Long geneId) throws HearsayDAOException {
        logger.debug("ENTERING findByGeneId(Long)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<PopulationFrequency> crit = critBuilder.createQuery(getPersistentClass());
        Root<PopulationFrequency> fromPopulationFrequency = crit.from(PopulationFrequency.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        Join<PopulationFrequency, Gene> populationFrequencyGeneJoin = fromPopulationFrequency.join(PopulationFrequency_.gene);
        predicates.add(critBuilder.equal(populationFrequencyGeneJoin.get(Gene_.id), geneId));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<PopulationFrequency> query = getEntityManager().createQuery(crit);
        List<PopulationFrequency> ret = query.getResultList();
        return ret;
    }

}
