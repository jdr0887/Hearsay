package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.Participant;

public interface ParticipantDAO extends BaseEntityDAO<Participant, Long> {

    public List<Participant> findByName(String name) throws HearsayDAOException;

    public List<Participant> findAll() throws HearsayDAOException;

}
