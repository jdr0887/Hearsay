package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.IndividualVariantDAO;
import org.renci.hearsay.dao.model.IndividualVariant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndividualVariantDAOImpl extends BaseEntityDAOImpl<IndividualVariant, Long> implements
        IndividualVariantDAO {

    private final Logger logger = LoggerFactory.getLogger(IndividualVariantDAOImpl.class);

    public IndividualVariantDAOImpl() {
        super();
    }

    @Override
    public Class<IndividualVariant> getPersistentClass() {
        return IndividualVariant.class;
    }

}
