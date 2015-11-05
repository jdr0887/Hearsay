package org.renci.hearsay.dao.jpa;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.MolecularConsequenceDAO;
import org.renci.hearsay.dao.model.MolecularConsequence;

@OsgiServiceProvider(classes = { MolecularConsequenceDAO.class })
@Singleton
@Transactional
public class MolecularConsequenceDAOImpl extends BaseEntityDAOImpl<MolecularConsequence, Long> implements MolecularConsequenceDAO {

    public MolecularConsequenceDAOImpl() {
        super();
    }

    @Override
    public Class<MolecularConsequence> getPersistentClass() {
        return MolecularConsequence.class;
    }

}
