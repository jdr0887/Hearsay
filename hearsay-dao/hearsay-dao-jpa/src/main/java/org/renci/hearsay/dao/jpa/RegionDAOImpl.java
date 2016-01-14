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
import org.renci.hearsay.dao.RegionDAO;
import org.renci.hearsay.dao.model.Alignment;
import org.renci.hearsay.dao.model.Alignment_;
import org.renci.hearsay.dao.model.Region;
import org.renci.hearsay.dao.model.Region_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OsgiServiceProvider(classes = { RegionDAO.class })
@Singleton
@Transactional(Transactional.TxType.SUPPORTS)
public class RegionDAOImpl extends BaseEntityDAOImpl<Region, Long> implements RegionDAO {

    private static final Logger logger = LoggerFactory.getLogger(RegionDAOImpl.class);

    public RegionDAOImpl() {
        super();
    }

    @Override
    public Class<Region> getPersistentClass() {
        return Region.class;
    }

    @Override
    public List<Region> findByAlignmentId(Long alignmentId) throws HearsayDAOException {
        logger.debug("ENTERING findByAlignmentId(Long)");
        List<Region> ret = new ArrayList<Region>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Region> crit = critBuilder.createQuery(getPersistentClass());
            Root<Region> fromRegion = crit.from(Region.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<Region, Alignment> regionAlignmentJoin = fromRegion.join(Region_.alignment);
            predicates.add(critBuilder.equal(regionAlignmentJoin.get(Alignment_.id), alignmentId));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<Region> query = getEntityManager().createQuery(crit);
            ret.addAll(query.getResultList());
        } catch (Exception e) {
            throw new HearsayDAOException(e);
        }
        return ret;
    }

}
