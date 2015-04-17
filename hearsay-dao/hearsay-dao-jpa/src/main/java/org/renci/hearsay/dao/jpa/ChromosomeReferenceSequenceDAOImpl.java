package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.ChromosomeReferenceSequenceDAO;
import org.renci.hearsay.dao.model.ChromosomeReferenceSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChromosomeReferenceSequenceDAOImpl extends BaseEntityDAOImpl<ChromosomeReferenceSequence, Long> implements
        ChromosomeReferenceSequenceDAO {

    private final Logger logger = LoggerFactory.getLogger(ChromosomeReferenceSequenceDAOImpl.class);

    public ChromosomeReferenceSequenceDAOImpl() {
        super();
    }

    @Override
    public Class<ChromosomeReferenceSequence> getPersistentClass() {
        return ChromosomeReferenceSequence.class;
    }

}
