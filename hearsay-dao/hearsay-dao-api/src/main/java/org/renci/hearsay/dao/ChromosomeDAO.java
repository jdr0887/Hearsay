package org.renci.hearsay.dao;

import java.util.List;

import org.renci.hearsay.dao.model.Chromosome;

public interface ChromosomeDAO extends BaseEntityDAO<Chromosome, Long> {

    public List<Chromosome> findByName(String name) throws HearsayDAOException;

}
