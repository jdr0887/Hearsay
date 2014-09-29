package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.GeneConditionAssertionDAO;
import org.renci.hearsay.dao.model.GeneConditionAssertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneConditionAssertionDAOImpl extends BaseEntityDAOImpl<GeneConditionAssertion, Long> implements
        GeneConditionAssertionDAO {

    private final Logger logger = LoggerFactory.getLogger(GeneConditionAssertionDAOImpl.class);

    public GeneConditionAssertionDAOImpl() {
        super();
    }

    @Override
    public Class<GeneConditionAssertion> getPersistentClass() {
        return GeneConditionAssertion.class;
    }

}
