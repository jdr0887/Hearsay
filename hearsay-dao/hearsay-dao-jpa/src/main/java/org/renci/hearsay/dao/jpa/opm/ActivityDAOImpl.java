package org.renci.hearsay.dao.jpa.opm;

import org.renci.hearsay.dao.jpa.BaseDAOImpl;
import org.renci.hearsay.dao.model.opm.Activity;
import org.renci.hearsay.dao.opm.ActivityDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivityDAOImpl extends BaseDAOImpl<Activity, Long> implements ActivityDAO {

    private final Logger logger = LoggerFactory.getLogger(ActivityDAOImpl.class);

    public ActivityDAOImpl() {
        super();
    }

    @Override
    public Class<Activity> getPersistentClass() {
        return Activity.class;
    }

}
