package org.renci.hearsay.dao.jpa;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.IntronOffsetDAO;
import org.renci.hearsay.dao.model.IntronOffset;

@OsgiServiceProvider(classes = { IntronOffsetDAO.class })
@Singleton
@Transactional(Transactional.TxType.SUPPORTS)
public class IntronOffsetDAOImpl extends BaseEntityDAOImpl<IntronOffset, Long> implements IntronOffsetDAO {

    public IntronOffsetDAOImpl() {
        super();
    }

    @Override
    public Class<IntronOffset> getPersistentClass() {
        return IntronOffset.class;
    }

}
