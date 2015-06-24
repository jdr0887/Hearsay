package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.MolecularConsequenceDAO;
import org.renci.hearsay.dao.model.MolecularConsequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MolecularConsequenceDAOImpl extends BaseEntityDAOImpl<MolecularConsequence, Long> implements MolecularConsequenceDAO {

    private final Logger logger = LoggerFactory.getLogger(MolecularConsequenceDAOImpl.class);

    public MolecularConsequenceDAOImpl() {
        super();
    }

    @Override
    public Class<MolecularConsequence> getPersistentClass() {
        return MolecularConsequence.class;
    }

}
