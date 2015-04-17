package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.AminoAcidReferenceSequenceDAO;
import org.renci.hearsay.dao.model.AminoAcidReferenceSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AminoAcidReferenceSequenceDAOImpl extends BaseEntityDAOImpl<AminoAcidReferenceSequence, Long> implements
        AminoAcidReferenceSequenceDAO {

    private final Logger logger = LoggerFactory.getLogger(AminoAcidReferenceSequenceDAOImpl.class);

    public AminoAcidReferenceSequenceDAOImpl() {
        super();
    }

    @Override
    public Class<AminoAcidReferenceSequence> getPersistentClass() {
        return AminoAcidReferenceSequence.class;
    }

}
