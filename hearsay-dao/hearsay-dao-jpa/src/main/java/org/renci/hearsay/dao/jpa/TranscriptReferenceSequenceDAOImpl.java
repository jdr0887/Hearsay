package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.TranscriptReferenceSequenceDAO;
import org.renci.hearsay.dao.model.TranscriptReferenceSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptReferenceSequenceDAOImpl extends BaseEntityDAOImpl<TranscriptReferenceSequence, Long> implements
        TranscriptReferenceSequenceDAO {

    private final Logger logger = LoggerFactory.getLogger(TranscriptReferenceSequenceDAOImpl.class);

    public TranscriptReferenceSequenceDAOImpl() {
        super();
    }

    @Override
    public Class<TranscriptReferenceSequence> getPersistentClass() {
        return TranscriptReferenceSequence.class;
    }

}
