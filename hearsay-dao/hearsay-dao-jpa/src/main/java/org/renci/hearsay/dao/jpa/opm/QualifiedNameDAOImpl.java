package org.renci.hearsay.dao.jpa.opm;

import org.renci.hearsay.dao.jpa.BaseDAOImpl;
import org.renci.hearsay.dao.model.opm.QualifiedName;
import org.renci.hearsay.dao.opm.QualifiedNameDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QualifiedNameDAOImpl extends BaseDAOImpl<QualifiedName, Long> implements QualifiedNameDAO {

    private final Logger logger = LoggerFactory.getLogger(QualifiedNameDAOImpl.class);

    public QualifiedNameDAOImpl() {
        super();
    }

    @Override
    public Class<QualifiedName> getPersistentClass() {
        return QualifiedName.class;
    }

}
