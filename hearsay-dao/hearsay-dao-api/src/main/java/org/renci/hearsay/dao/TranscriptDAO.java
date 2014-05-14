package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.Transcript;

public interface TranscriptDAO extends BaseEntityDAO<Transcript, Long> {

    public List<Transcript> findAll() throws HearsayDAOException;

}
