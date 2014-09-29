package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.GeneConditionDAO;
import org.renci.hearsay.dao.model.GeneCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneConditionDAOImpl extends BaseEntityDAOImpl<GeneCondition, Long> implements GeneConditionDAO {

    private final Logger logger = LoggerFactory.getLogger(GeneConditionDAOImpl.class);

    public GeneConditionDAOImpl() {
        super();
    }

    @Override
    public Class<GeneCondition> getPersistentClass() {
        return GeneCondition.class;
    }

}
