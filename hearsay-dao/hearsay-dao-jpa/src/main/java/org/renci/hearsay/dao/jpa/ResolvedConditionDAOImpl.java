package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.ResolvedConditionDAO;
import org.renci.hearsay.dao.model.ResolvedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResolvedConditionDAOImpl extends BaseEntityDAOImpl<ResolvedCondition, Long> implements
        ResolvedConditionDAO {

    private final Logger logger = LoggerFactory.getLogger(ResolvedConditionDAOImpl.class);

    public ResolvedConditionDAOImpl() {
        super();
    }

    @Override
    public Class<ResolvedCondition> getPersistentClass() {
        return ResolvedCondition.class;
    }

}
