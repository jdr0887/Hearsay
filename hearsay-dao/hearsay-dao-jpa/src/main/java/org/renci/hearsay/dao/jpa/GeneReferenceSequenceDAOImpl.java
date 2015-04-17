package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.GeneReferenceSequenceDAO;
import org.renci.hearsay.dao.model.GeneReferenceSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneReferenceSequenceDAOImpl extends BaseEntityDAOImpl<GeneReferenceSequence, Long> implements
        GeneReferenceSequenceDAO {

    private final Logger logger = LoggerFactory.getLogger(GeneReferenceSequenceDAOImpl.class);

    public GeneReferenceSequenceDAOImpl() {
        super();
    }

    @Override
    public Class<GeneReferenceSequence> getPersistentClass() {
        return GeneReferenceSequence.class;
    }

}
