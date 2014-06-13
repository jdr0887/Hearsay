package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.ReferenceSequenceDAO;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.ReferenceSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReferenceSequenceDAOImpl extends BaseEntityDAOImpl<ReferenceSequence, Long> implements
        ReferenceSequenceDAO {

    private final Logger logger = LoggerFactory.getLogger(ReferenceSequenceDAOImpl.class);

    public ReferenceSequenceDAOImpl() {
        super();
    }

    @Override
    public Class<ReferenceSequence> getPersistentClass() {
        return ReferenceSequence.class;
    }

}
