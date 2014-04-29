package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.TranscriptIntervalDAO;
import org.renci.hearsay.dao.model.TranscriptInterval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptIntervalDAOImpl extends BaseEntityDAOImpl<TranscriptInterval, Long> implements
        TranscriptIntervalDAO {

    private final Logger logger = LoggerFactory.getLogger(TranscriptIntervalDAOImpl.class);

    public TranscriptIntervalDAOImpl() {
        super();
    }

    @Override
    public Class<TranscriptInterval> getPersistentClass() {
        return TranscriptInterval.class;
    }

}
