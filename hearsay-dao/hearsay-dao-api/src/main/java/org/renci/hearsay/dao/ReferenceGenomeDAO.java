package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.ReferenceGenome;

public interface ReferenceGenomeDAO extends BaseEntityDAO<ReferenceGenome, Long> {

    public abstract List<ReferenceGenome> findAll() throws HearsayDAOException;

    public abstract List<ReferenceGenome> findByExample(ReferenceGenome referenceGenome) throws HearsayDAOException;

    public abstract List<ReferenceGenome> findBySource(String version) throws HearsayDAOException;

    public abstract ReferenceGenome findBySourceAndVersion(String source, String version) throws HearsayDAOException;

}
