package org.renci.hearsay.dao.jpa;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.ExternalOffsetPositionDAO;
import org.renci.hearsay.dao.model.ExternalOffsetPosition;

@OsgiServiceProvider(classes = { ExternalOffsetPositionDAO.class })
@Singleton
@Transactional(Transactional.TxType.SUPPORTS)
public class ExternalOffsetPositionDAOImpl extends BaseEntityDAOImpl<ExternalOffsetPosition, Long> implements ExternalOffsetPositionDAO {

    public ExternalOffsetPositionDAOImpl() {
        super();
    }

    @Override
    public Class<ExternalOffsetPosition> getPersistentClass() {
        return ExternalOffsetPosition.class;
    }

}
