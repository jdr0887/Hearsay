package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.TranscriptVariant;

public interface TranscriptVariantDAO extends BaseEntityDAO<TranscriptVariant, Long> {

    public List<TranscriptVariant> findByGeneName(String name) throws HearsayDAOException;

}
