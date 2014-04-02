package org.renci.hearsay.dao.jpa.opm;

import org.renci.hearsay.dao.jpa.BaseDAOImpl;
import org.renci.hearsay.dao.model.opm.Document;
import org.renci.hearsay.dao.opm.DocumentDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocumentDAOImpl extends BaseDAOImpl<Document, Long> implements DocumentDAO {

    private final Logger logger = LoggerFactory.getLogger(DocumentDAOImpl.class);

    public DocumentDAOImpl() {
        super();
    }

    @Override
    public Class<Document> getPersistentClass() {
        return Document.class;
    }

}
