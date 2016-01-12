package org.renci.hearsay.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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

    @Override
    public List<Identifier> findBySystem(String system) {
        logger.debug("ENTERING findBySystem(String)");
        List<Identifier> identifierList = new ArrayList<Identifier>();
        try {
            identifierList.addAll(identifierDAO.findByExample(new Identifier(system, null)));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return identifierList;
    }

    @Override
    public Identifier findBySystemAndValue(String system, String value) {
        logger.debug("ENTERING findBySystemAndValue(String, String)");
        List<Identifier> identifierList = new ArrayList<Identifier>();
        try {
            identifierList.addAll(identifierDAO.findByExample(new Identifier(system, value)));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        if (CollectionUtils.isNotEmpty(identifierList)) {
            return identifierList.get(0);
        }
        return null;
    }

    public IdentifierDAO getIdentifierDAO() {
        return identifierDAO;
    }

    public void setIdentifierDAO(IdentifierDAO identifierDAO) {
        this.identifierDAO = identifierDAO;
    }

}
