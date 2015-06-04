package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.ReferenceGenomeDAO;
import org.renci.hearsay.dao.model.ReferenceGenome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReferenceGenomeDAOImpl extends BaseEntityDAOImpl<ReferenceGenome, Long> implements ReferenceGenomeDAO {

    private final Logger logger = LoggerFactory.getLogger(ReferenceGenomeDAOImpl.class);

    public ReferenceGenomeDAOImpl() {
        super();
    }

    @Override
    public Class<ReferenceGenome> getPersistentClass() {
        return ReferenceGenome.class;
    }

}
