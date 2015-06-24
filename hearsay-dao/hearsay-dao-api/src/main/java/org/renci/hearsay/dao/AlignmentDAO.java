package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.Alignment;

public interface AlignmentDAO extends BaseEntityDAO<Alignment, Long> {

    public List<Alignment> findByReferenceSequenceId(Long referenceSequenceId) throws HearsayDAOException;

}
