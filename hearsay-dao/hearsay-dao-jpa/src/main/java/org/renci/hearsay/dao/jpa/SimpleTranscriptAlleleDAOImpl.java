package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.SimpleTranscriptAlleleDAO;
import org.renci.hearsay.dao.model.SimpleTranscriptAllele;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleTranscriptAlleleDAOImpl extends BaseEntityDAOImpl<SimpleTranscriptAllele, Long> implements
        SimpleTranscriptAlleleDAO {

    private final Logger logger = LoggerFactory.getLogger(SimpleTranscriptAlleleDAOImpl.class);

    public SimpleTranscriptAlleleDAOImpl() {
        super();
    }

    @Override
    public Class<SimpleTranscriptAllele> getPersistentClass() {
        return SimpleTranscriptAllele.class;
    }

}
