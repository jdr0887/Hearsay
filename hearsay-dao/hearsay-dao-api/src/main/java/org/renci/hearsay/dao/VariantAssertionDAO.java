package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.VariantAssertion;

public interface VariantAssertionDAO extends BaseEntityDAO<VariantAssertion, Long> {

    public List<VariantAssertion> findByGenomicVariantId(Long genomicVariantId) throws HearsayDAOException;

}
