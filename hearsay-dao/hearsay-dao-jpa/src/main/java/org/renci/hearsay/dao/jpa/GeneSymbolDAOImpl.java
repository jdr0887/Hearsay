package org.renci.hearsay.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.GeneSymbolDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GeneSymbol;
import org.renci.hearsay.dao.model.GeneSymbol_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OsgiServiceProvider(classes = { GeneSymbolDAO.class })
@Singleton
@Transactional
public class GeneSymbolDAOImpl extends BaseEntityDAOImpl<GeneSymbol, Long> implements GeneSymbolDAO {

    private static final Logger logger = LoggerFactory.getLogger(GeneSymbolDAOImpl.class);

    public GeneSymbolDAOImpl() {
        super();
    }

    @Override
    public Class<GeneSymbol> getPersistentClass() {
        return GeneSymbol.class;
    }

    @Override
    public List<GeneSymbol> findBySymbol(String symbol) throws HearsayDAOException {
        logger.debug("ENTERING findBySymbol(String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<GeneSymbol> crit = critBuilder.createQuery(getPersistentClass());
        Root<GeneSymbol> fromGeneSymbol = crit.from(GeneSymbol.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(critBuilder.like(fromGeneSymbol.get(GeneSymbol_.symbol), symbol));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<GeneSymbol> query = getEntityManager().createQuery(crit);
        List<GeneSymbol> ret = query.getResultList();
        return ret;
    }

}
