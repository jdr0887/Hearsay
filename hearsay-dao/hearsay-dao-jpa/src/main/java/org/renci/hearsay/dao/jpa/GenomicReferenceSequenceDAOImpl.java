package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.GenomicReferenceSequenceDAO;
import org.renci.hearsay.dao.model.GenomicReferenceSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenomicReferenceSequenceDAOImpl extends BaseEntityDAOImpl<GenomicReferenceSequence, Long> implements
        GenomicReferenceSequenceDAO {

    private final Logger logger = LoggerFactory.getLogger(GenomicReferenceSequenceDAOImpl.class);

    public GenomicReferenceSequenceDAOImpl() {
        super();
    }

    @Override
    public Class<GenomicReferenceSequence> getPersistentClass() {
        return GenomicReferenceSequence.class;
    }

}
