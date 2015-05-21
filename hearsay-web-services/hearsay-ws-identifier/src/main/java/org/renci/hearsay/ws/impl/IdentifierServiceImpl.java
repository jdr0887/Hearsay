package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.IdentifierDAO;
import org.renci.hearsay.dao.model.Identifier;
import org.renci.hearsay.ws.IdentifierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdentifierServiceImpl implements IdentifierService {

    private final Logger logger = LoggerFactory.getLogger(IdentifierServiceImpl.class);

    private IdentifierDAO identifierDAO;

    public IdentifierServiceImpl() {
        super();
    }

    @Override
    public Identifier findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        Identifier ret = null;
        try {
            ret = identifierDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public IdentifierDAO getIdentifierDAO() {
        return identifierDAO;
    }

    public void setIdentifierDAO(IdentifierDAO identifierDAO) {
        this.identifierDAO = identifierDAO;
    }

}
