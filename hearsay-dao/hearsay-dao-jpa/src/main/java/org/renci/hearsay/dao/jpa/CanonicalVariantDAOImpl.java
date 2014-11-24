package org.renci.hearsay.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.renci.hearsay.dao.CanonicalVariantDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.CanonicalVariant;
import org.renci.hearsay.dao.model.CanonicalVariant_;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.Gene_;
import org.renci.hearsay.dao.model.VariantRepresentation;
import org.renci.hearsay.dao.model.VariantRepresentation_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CanonicalVariantDAOImpl extends BaseEntityDAOImpl<CanonicalVariant, Long> implements CanonicalVariantDAO {

    private final Logger logger = LoggerFactory.getLogger(CanonicalVariantDAOImpl.class);

    public CanonicalVariantDAOImpl() {
        super();
    }

    @Override
    public Class<CanonicalVariant> getPersistentClass() {
        return CanonicalVariant.class;
    }

    @Override
    public List<CanonicalVariant> findByGeneName(String name) throws HearsayDAOException {
        logger.debug("ENTERING findByGeneName(String)");
        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CanonicalVariant> crit = critBuilder.createQuery(getPersistentClass());
        Root<CanonicalVariant> root = crit.from(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();
        SetJoin<CanonicalVariant, VariantRepresentation> canonicalVariantVariantRepresentationJoin = root
                .join(CanonicalVariant_.variants);
        Join<VariantRepresentation, Gene> variantRepresentationGeneJoin = canonicalVariantVariantRepresentationJoin
                .join(VariantRepresentation_.gene);
        predicates.add(critBuilder.equal(variantRepresentationGeneJoin.get(Gene_.name), name));
        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<CanonicalVariant> query = getEntityManager().createQuery(crit);
        List<CanonicalVariant> ret = query.getResultList();
        return ret;
    }

}
