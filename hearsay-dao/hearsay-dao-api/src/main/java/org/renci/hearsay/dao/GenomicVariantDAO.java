package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.GenomicVariant;

public interface GenomicVariantDAO extends BaseEntityDAO<GenomicVariant, Long> {

    public List<GenomicVariant> findByGeneName(String name) throws HearsayDAOException;

}
