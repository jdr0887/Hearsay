package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.MolecularConsequenceDAO;
import org.renci.hearsay.dao.model.MolecularConsequence;

public class MolecularConsequenceDAOImpl extends BaseEntityDAOImpl<MolecularConsequence, Long> implements MolecularConsequenceDAO {

    public MolecularConsequenceDAOImpl() {
        super();
    }

    @Override
    public Class<MolecularConsequence> getPersistentClass() {
        return MolecularConsequence.class;
    }

}
