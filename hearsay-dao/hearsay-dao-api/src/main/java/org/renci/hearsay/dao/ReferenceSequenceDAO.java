package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.Identifier;
import org.renci.hearsay.dao.model.ReferenceSequence;

public interface ReferenceSequenceDAO extends BaseEntityDAO<ReferenceSequence, Long> {

    public List<ReferenceSequence> findByGeneId(Long geneId) throws HearsayDAOException;

    public List<ReferenceSequence> findAll() throws HearsayDAOException;

    public List<ReferenceSequence> findByIdentifierValue(String value) throws HearsayDAOException;

    public List<ReferenceSequence> findByIdentifiers(Identifier... identifiers) throws HearsayDAOException;

}
