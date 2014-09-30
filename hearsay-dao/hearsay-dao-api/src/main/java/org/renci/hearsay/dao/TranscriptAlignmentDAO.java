package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.TranscriptAlignment;

public interface TranscriptAlignmentDAO extends BaseEntityDAO<TranscriptAlignment, Long> {

    public List<TranscriptAlignment> findByTranscriptRefSeqId(Long transcriptId) throws HearsayDAOException;

    public List<TranscriptAlignment> findByTranscriptRefSeqAccession(String accession) throws HearsayDAOException;

    public List<TranscriptAlignment> findByGeneName(String name) throws HearsayDAOException;

}
