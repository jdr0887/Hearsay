package org.renci.hearsay.dao.jpa;

import javax.transaction.Transactional;

import org.renci.hearsay.dao.IntronOffsetDAO;
import org.renci.hearsay.dao.model.IntronOffset;

@Transactional
public class IntronOffsetDAOImpl extends BaseEntityDAOImpl<IntronOffset, Long> implements IntronOffsetDAO {

    public IntronOffsetDAOImpl() {
        super();
    }

    @Override
    public Class<IntronOffset> getPersistentClass() {
        return IntronOffset.class;
    }

}
