package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.MappedTranscript;

public interface MappedTranscriptDAO extends BaseEntityDAO<MappedTranscript, Long> {

    public List<MappedTranscript> findByTranscriptId(Long transcriptId) throws HearsayDAOException;

    public abstract List<MappedTranscript> findByExample(MappedTranscript mappedTranscript) throws HearsayDAOException;

}
