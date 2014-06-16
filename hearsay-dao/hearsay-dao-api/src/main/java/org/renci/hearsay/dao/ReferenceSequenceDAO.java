package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.ReferenceSequence;

public interface ReferenceSequenceDAO extends BaseEntityDAO<ReferenceSequence, Long> {

    public List<ReferenceSequence> findByAccession(String accession) throws HearsayDAOException;

}
