package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.MappedTranscript;

public interface MappedTranscriptDAO extends BaseEntityDAO<MappedTranscript, Long> {

    public List<MappedTranscript> findByTranscriptId(Long transcriptId) throws HearsayDAOException;

    public List<MappedTranscript> findByTranscriptAccession(String accession) throws HearsayDAOException;

    public List<MappedTranscript> findByGeneName(String name) throws HearsayDAOException;

}
