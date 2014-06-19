package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.Transcript;

public interface TranscriptDAO extends BaseEntityDAO<Transcript, Long> {

    public List<Transcript> findAll() throws HearsayDAOException;

    public abstract List<Transcript> findByExample(Transcript transcript) throws HearsayDAOException;

    public abstract List<Transcript> findByGeneId(Long geneId) throws HearsayDAOException;

    public abstract List<Transcript> findByGeneName(String name) throws HearsayDAOException;

}
