package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.PopulationFrequency;

public interface PopulationFrequencyDAO extends BaseEntityDAO<PopulationFrequency, Long> {

    public abstract List<PopulationFrequency> findBySourceAndVersion(String source, String version)
            throws HearsayDAOException;

}
