package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.VariantAssertionDAO;
import org.renci.hearsay.dao.model.VariantAssertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VariantAssertionDAOImpl extends BaseEntityDAOImpl<VariantAssertion, Long> implements
        VariantAssertionDAO {

    private final Logger logger = LoggerFactory.getLogger(VariantAssertionDAOImpl.class);

    public VariantAssertionDAOImpl() {
        super();
    }

    @Override
    public Class<VariantAssertion> getPersistentClass() {
        return VariantAssertion.class;
    }

}
