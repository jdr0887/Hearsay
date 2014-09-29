package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.GenomicRefSeq;

public interface GenomicRefSeqDAO extends BaseEntityDAO<GenomicRefSeq, Long> {

    public abstract List<GenomicRefSeq> findAll() throws HearsayDAOException;

    public abstract List<GenomicRefSeq> findByExample(GenomicRefSeq referenceGenome) throws HearsayDAOException;

    public abstract List<GenomicRefSeq> findBySource(String version) throws HearsayDAOException;

    public abstract GenomicRefSeq findBySourceAndVersion(String source, String version) throws HearsayDAOException;

}
