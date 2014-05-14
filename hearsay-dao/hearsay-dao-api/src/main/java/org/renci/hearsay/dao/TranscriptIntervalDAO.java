package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.TranscriptInterval;

public interface TranscriptIntervalDAO extends BaseEntityDAO<TranscriptInterval, Long> {

    public List<TranscriptInterval> findByTranscriptId(Long transcriptId) throws HearsayDAOException;

}
