package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.Feature;

public interface FeatureDAO extends BaseEntityDAO<Feature, Long> {

    public List<Feature> findByReferenceSequenceId(Long referenceSequenceId) throws HearsayDAOException;

}
