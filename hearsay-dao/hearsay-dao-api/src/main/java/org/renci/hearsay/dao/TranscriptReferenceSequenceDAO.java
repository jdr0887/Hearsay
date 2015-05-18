package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.TranscriptReferenceSequence;

public interface TranscriptReferenceSequenceDAO extends BaseEntityDAO<TranscriptReferenceSequence, Long> {

    public List<TranscriptReferenceSequence> findByExample(TranscriptReferenceSequence transcriptReferenceSequence)
            throws HearsayDAOException;

    public List<TranscriptReferenceSequence> findByGeneId(Long geneId) throws HearsayDAOException;

}
