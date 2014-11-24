package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.CanonicalVariant;

public interface CanonicalVariantDAO extends BaseEntityDAO<CanonicalVariant, Long> {

    public abstract List<CanonicalVariant> findByGeneName(String name) throws HearsayDAOException;

}
