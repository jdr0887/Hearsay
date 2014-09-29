package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.IndividualDAO;
import org.renci.hearsay.dao.model.Individual;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndividualDAOImpl extends BaseEntityDAOImpl<Individual, Long> implements IndividualDAO {

    private final Logger logger = LoggerFactory.getLogger(IndividualDAOImpl.class);

    public IndividualDAOImpl() {
        super();
    }

    @Override
    public Class<Individual> getPersistentClass() {
        return Individual.class;
    }

}
