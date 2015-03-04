package org.renci.hearsay.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.VariantAssertionDAO;
import org.renci.hearsay.dao.model.VariantAssertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VariantAssertionDAOImpl extends BaseEntityDAOImpl<VariantAssertion, Long> implements VariantAssertionDAO {

    private final Logger logger = LoggerFactory.getLogger(VariantAssertionDAOImpl.class);

    public VariantAssertionDAOImpl() {
        super();
    }

    @Override
    public Class<VariantAssertion> getPersistentClass() {
        return VariantAssertion.class;
    }

    @Override
    public List<VariantAssertion> findByGenomicVariantId(Long genomicVariantId) throws HearsayDAOException {
        logger.debug("ENTERING findByGenomicVariantId(Long)");
        TypedQuery<VariantAssertion> query = getEntityManager().createNamedQuery(
                "VariantAssertion.findByGenomicVariantId", VariantAssertion.class);
        query.setParameter("id", genomicVariantId);
        List<VariantAssertion> ret = query.getResultList();
        return ret;
    }

}
