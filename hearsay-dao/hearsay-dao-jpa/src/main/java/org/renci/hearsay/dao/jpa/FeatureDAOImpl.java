package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.FeatureDAO;
import org.renci.hearsay.dao.model.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeatureDAOImpl extends BaseEntityDAOImpl<Feature, Long> implements FeatureDAO {

    private final Logger logger = LoggerFactory.getLogger(FeatureDAOImpl.class);

    public FeatureDAOImpl() {
        super();
    }

    @Override
    public Class<Feature> getPersistentClass() {
        return Feature.class;
    }

}
