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
import org.apache.openjpa.persistence.OpenJPAPersistence;
import org.apache.openjpa.persistence.OpenJPAQuery;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.CanonicalAlleleDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.CanonicalAllele;
import org.renci.hearsay.dao.model.CanonicalAllele_;
import org.renci.hearsay.dao.model.Identifier;
import org.renci.hearsay.dao.model.Identifier_;
import org.renci.hearsay.dao.model.ReferenceSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OsgiServiceProvider(classes = { CanonicalAlleleDAO.class })
@Singleton
@Transactional(Transactional.TxType.SUPPORTS)
public class CanonicalAlleleDAOImpl extends BaseEntityDAOImpl<CanonicalAllele, Long> implements CanonicalAlleleDAO {

    private static final Logger logger = LoggerFactory.getLogger(CanonicalAlleleDAOImpl.class);

    public CanonicalAlleleDAOImpl() {
        super();
    }

    @Override
    public Class<CanonicalAllele> getPersistentClass() {
        return CanonicalAllele.class;
    }

    @Override
    public List<CanonicalAllele> findByIdentifierSystemAndValue(String system, String value) throws HearsayDAOException {
        logger.debug("ENTERING findByIdentifierSystemAndValue(String, String)");
        List<CanonicalAllele> ret = new ArrayList<CanonicalAllele>();
        ret.addAll(findByIdentifierSystemAndValue(null, system, value));
        return ret;
    }

    @Override
    public List<CanonicalAllele> findByIdentifierSystemAndValue(String fetchPlan, String system, String value) throws HearsayDAOException {
        logger.debug("ENTERING findByIdentifierSystemAndValue(String, String, String)");
        List<CanonicalAllele> ret = new ArrayList<CanonicalAllele>();
        try {
            CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<CanonicalAllele> crit = critBuilder.createQuery(getPersistentClass());
            Root<CanonicalAllele> fromCanonicalAllele = crit.from(getPersistentClass());
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<CanonicalAllele, Identifier> canonicalAlleleIdentifierJoin = fromCanonicalAllele.join(CanonicalAllele_.identifiers);
            if (!value.endsWith("%")) {
                value += "%";
            }
            predicates.add(critBuilder.like(canonicalAlleleIdentifierJoin.get(Identifier_.value), value));
            predicates.add(critBuilder.equal(canonicalAlleleIdentifierJoin.get(Identifier_.system), system));
            crit.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<CanonicalAllele> query = getEntityManager().createQuery(crit);
            if (StringUtils.isNotEmpty(fetchPlan)) {
                OpenJPAQuery<CanonicalAllele> openjpaQuery = OpenJPAPersistence.cast(query);
                openjpaQuery.getFetchPlan().addFetchGroup(fetchPlan);
                ret.addAll(openjpaQuery.getResultList());
            } else {
                ret.addAll(query.getResultList());
            }
        } catch (Exception e) {
            throw new HearsayDAOException(e);
        }
        return ret;
    }

}
