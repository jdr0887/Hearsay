package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.TranscriptRefSeq;

public interface TranscriptRefSeqDAO extends BaseEntityDAO<TranscriptRefSeq, Long> {

    public List<TranscriptRefSeq> findAll() throws HearsayDAOException;

    public abstract List<TranscriptRefSeq> findByExample(TranscriptRefSeq transcriptRefSeq) throws HearsayDAOException;

    public abstract List<TranscriptRefSeq> findByGeneId(Long geneId) throws HearsayDAOException;

    public abstract List<TranscriptRefSeq> findByGeneName(String name) throws HearsayDAOException;

    public abstract List<TranscriptRefSeq> findByRefSeqAccession(String accession) throws HearsayDAOException;

}
