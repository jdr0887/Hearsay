package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.SimpleAlleleDAO;
import org.renci.hearsay.dao.model.SimpleAllele;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleAlleleDAOImpl extends BaseEntityDAOImpl<SimpleAllele, Long> implements SimpleAlleleDAO {

    private final Logger logger = LoggerFactory.getLogger(SimpleAlleleDAOImpl.class);

    public SimpleAlleleDAOImpl() {
        super();
    }

    @Override
    public Class<SimpleAllele> getPersistentClass() {
        return SimpleAllele.class;
    }

}
