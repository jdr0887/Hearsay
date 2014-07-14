package org.renci.hearsay.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.ReferenceGenomeDAO;
import org.renci.hearsay.dao.model.ReferenceGenome;
import org.renci.hearsay.dao.model.ReferenceGenome_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReferenceGenomeDAOImpl extends BaseEntityDAOImpl<ReferenceGenome, Long> implements ReferenceGenomeDAO {

    private final Logger logger = LoggerFactory.getLogger(ReferenceGenomeDAOImpl.class);

    public ReferenceGenomeDAOImpl() {
        super();
    }

    @Override
    public Class<ReferenceGenome> getPersistentClass() {
        return ReferenceGenome.class;
    }

    @Override
    public List<ReferenceGenome> findAll() throws HearsayDAOException {
        logger.debug("ENTERING findAll()");
        TypedQuery<ReferenceGenome> query = getEntityManager().createNamedQuery("ReferenceGenome.findAll",
                ReferenceGenome.class);
        List<ReferenceGenome> ret = query.getResultList();
        return ret;
    }

    @Override
    public ReferenceGenome findBySourceAndVersion(String source, String version) throws HearsayDAOException {
        logger.debug("ENTERING findBySourceAndVersion(String, String)");
        TypedQuery<ReferenceGenome> query = getEntityManager().createNamedQuery(
                "ReferenceGenome.findBySourceAndVersion", ReferenceGenome.class);
        query.setParameter("source", source);
        query.setParameter("version", version);
        List<ReferenceGenome> ret = new ArrayList<ReferenceGenome>();
        ret.addAll(query.getResultList());
        return ret.get(0);
    }

    @Override
    public List<ReferenceGenome> findBySource(String source) throws HearsayDAOException {
        logger.debug("ENTERING findBySource(String)");
        TypedQuery<ReferenceGenome> query = getEntityManager().createNamedQuery("ReferenceGenome.findBySource",
                ReferenceGenome.class);
        query.setParameter("source", source);
        List<ReferenceGenome> ret = new ArrayList<ReferenceGenome>();
        ret.addAll(query.getResultList());
        return ret;
    }

    @Override
    public List<ReferenceGenome> findByExample(ReferenceGenome referenceGenome) throws HearsayDAOException {
        logger.debug("ENTERING findByExample(ReferenceGenome)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ReferenceGenome> crit = critBuilder.createQuery(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();

        Root<ReferenceGenome> fromReferenceGenome = crit.from(ReferenceGenome.class);

        if (StringUtils.isNotEmpty(referenceGenome.getSource())) {
            predicates.add(critBuilder.equal(fromReferenceGenome.get(ReferenceGenome_.source),
                    referenceGenome.getSource()));
        }

        if (StringUtils.isNotEmpty(referenceGenome.getVersion())) {
            predicates.add(critBuilder.equal(fromReferenceGenome.get(ReferenceGenome_.version),
                    referenceGenome.getVersion()));
        }

        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<ReferenceGenome> query = getEntityManager().createQuery(crit);
        List<ReferenceGenome> ret = query.getResultList();
        return ret;
    }

}
