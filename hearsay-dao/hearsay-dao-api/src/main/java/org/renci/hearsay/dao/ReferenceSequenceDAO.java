package org.renci.hearsay.dao;

import org.renci.hearsay.dao.model.ReferenceSequence;

public interface ReferenceSequenceDAO extends BaseEntityDAO<ReferenceSequence, Long> {

    public ReferenceSequence findByAccession(String accession) throws HearsayDAOException;

}
