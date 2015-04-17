package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.SimpleGenomicAlleleDAO;
import org.renci.hearsay.dao.model.SimpleGenomicAllele;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleGenomicAlleleDAOImpl extends BaseEntityDAOImpl<SimpleGenomicAllele, Long>
        implements SimpleGenomicAlleleDAO {

    private final Logger logger = LoggerFactory.getLogger(SimpleGenomicAlleleDAOImpl.class);

    public SimpleGenomicAlleleDAOImpl() {
        super();
    }

    @Override
    public Class<SimpleGenomicAllele> getPersistentClass() {
        return SimpleGenomicAllele.class;
    }

}
