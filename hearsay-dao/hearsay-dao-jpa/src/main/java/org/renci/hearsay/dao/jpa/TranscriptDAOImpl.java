package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.TranscriptDAO;
import org.renci.hearsay.dao.model.Transcript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptDAOImpl extends BaseEntityDAOImpl<Transcript, Long> implements TranscriptDAO {

    private final Logger logger = LoggerFactory.getLogger(TranscriptDAOImpl.class);

    public TranscriptDAOImpl() {
        super();
    }

    @Override
    public Class<Transcript> getPersistentClass() {
        return Transcript.class;
    }

}
