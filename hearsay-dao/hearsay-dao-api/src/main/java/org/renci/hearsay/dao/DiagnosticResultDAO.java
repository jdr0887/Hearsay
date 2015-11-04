package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.DiagnosticResult;
import org.renci.hearsay.dao.model.Gene;

public interface DiagnosticResultDAO extends BaseEntityDAO<DiagnosticResult, Long> {

    public List<DiagnosticResult> findByParticipantName(String name) throws HearsayDAOException;

}
