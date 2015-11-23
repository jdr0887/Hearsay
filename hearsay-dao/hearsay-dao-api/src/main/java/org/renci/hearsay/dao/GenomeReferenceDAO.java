package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.GenomeReference;

public interface GenomeReferenceDAO extends BaseEntityDAO<GenomeReference, Long> {

    public List<GenomeReference> findAll() throws HearsayDAOException;

    public List<GenomeReference> findByName(String name) throws HearsayDAOException;

    public List<GenomeReference> findByExample(GenomeReference genomeReference) throws HearsayDAOException;

    public List<GenomeReference> findByIdentifierValue(String value) throws HearsayDAOException;

}
