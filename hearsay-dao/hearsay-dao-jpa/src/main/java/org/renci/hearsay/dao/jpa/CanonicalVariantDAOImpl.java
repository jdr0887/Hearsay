package org.renci.hearsay.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.renci.hearsay.dao.CanonicalVariantDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.CanonicalVariant;
import org.renci.hearsay.dao.model.CanonicalVariant_;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.Gene_;
import org.renci.hearsay.dao.model.GenomicVariant;
import org.renci.hearsay.dao.model.GenomicVariant_;
import org.renci.hearsay.dao.model.TranscriptVariant;
import org.renci.hearsay.dao.model.TranscriptVariant_;
import org.renci.hearsay.dao.model.TranslationVariant;
import org.renci.hearsay.dao.model.TranslationVariant_;
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

        List<CanonicalVariant> ret = new ArrayList<>();

        CriteriaBuilder critBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CanonicalVariant> crit = critBuilder.createQuery(getPersistentClass());
        Root<CanonicalVariant> root = crit.from(getPersistentClass());
        List<Predicate> predicates = new ArrayList<Predicate>();

        SetJoin<CanonicalVariant, GenomicVariant> canonicalVariantGenomicVariantJoin = root.join(
                CanonicalVariant_.genomicVariants, JoinType.LEFT);
        Join<GenomicVariant, Gene> genomicVariantGeneJoin = canonicalVariantGenomicVariantJoin
                .join(GenomicVariant_.gene);
        predicates.add(critBuilder.equal(genomicVariantGeneJoin.get(Gene_.name), name));

        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<CanonicalVariant> query = getEntityManager().createQuery(crit);
        ret.addAll(query.getResultList());

        critBuilder = getEntityManager().getCriteriaBuilder();
        crit = critBuilder.createQuery(getPersistentClass());
        root = crit.from(getPersistentClass());
        predicates = new ArrayList<Predicate>();

        SetJoin<CanonicalVariant, TranscriptVariant> canonicalVariantTranscriptVariantJoin = root.join(
                CanonicalVariant_.transcriptVariants, JoinType.LEFT);
        Join<TranscriptVariant, Gene> transcriptVariantGeneJoin = canonicalVariantTranscriptVariantJoin
                .join(TranscriptVariant_.gene);
        predicates.add(critBuilder.equal(transcriptVariantGeneJoin.get(Gene_.name), name));

        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        query = getEntityManager().createQuery(crit);
        ret.addAll(query.getResultList());

        critBuilder = getEntityManager().getCriteriaBuilder();
        crit = critBuilder.createQuery(getPersistentClass());
        root = crit.from(getPersistentClass());
        predicates = new ArrayList<Predicate>();

        SetJoin<CanonicalVariant, TranslationVariant> canonicalVariantTranslationVariantJoin = root.join(
                CanonicalVariant_.translationVariants, JoinType.LEFT);
        Join<TranslationVariant, Gene> translationVariantGeneJoin = canonicalVariantTranslationVariantJoin
                .join(TranslationVariant_.gene);
        predicates.add(critBuilder.equal(translationVariantGeneJoin.get(Gene_.name), name));

        crit.where(predicates.toArray(new Predicate[predicates.size()]));
        query = getEntityManager().createQuery(crit);
        ret.addAll(query.getResultList());

        return ret;
    }

}
