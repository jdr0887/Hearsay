package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.Identifier;
import org.renci.hearsay.dao.model.ReferenceSequence;

public interface ReferenceSequenceDAO extends BaseEntityDAO<ReferenceSequence, Long> {

    public List<ReferenceSequence> findByGeneId(Long geneId) throws HearsayDAOException;

    public List<ReferenceSequence> findByExample(ReferenceSequence referenceSequence) throws HearsayDAOException;

    public List<ReferenceSequence> findByIdentifierSystemAndValue(String system, String value) throws HearsayDAOException;

    public List<ReferenceSequence> findByIdentifierSystemAndValue(String fetchPlan, String system, String value) throws HearsayDAOException;

    public List<ReferenceSequence> findByIdentifierSystem(String system) throws HearsayDAOException;

    public List<ReferenceSequence> findByIdentifierSystem(String fetchPlan, String system) throws HearsayDAOException;

    public List<ReferenceSequence> findByIdentifiers(List<Identifier> idList) throws HearsayDAOException;

}
