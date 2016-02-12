package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.CanonicalAllele;

public interface CanonicalAlleleDAO extends BaseEntityDAO<CanonicalAllele, Long> {

    public List<CanonicalAllele> findByIdentifierSystemAndValue(String system, String value) throws HearsayDAOException;

    public List<CanonicalAllele> findByIdentifierSystemAndValue(String fetchPlan, String system, String value) throws HearsayDAOException;

}
