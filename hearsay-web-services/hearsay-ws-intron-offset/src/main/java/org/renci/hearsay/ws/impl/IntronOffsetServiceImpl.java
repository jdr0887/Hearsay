package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.IntronOffsetDAO;
import org.renci.hearsay.dao.model.IntronOffset;
import org.renci.hearsay.ws.IntronOffsetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntronOffsetServiceImpl implements IntronOffsetService {

    private final Logger logger = LoggerFactory.getLogger(IntronOffsetServiceImpl.class);

    private IntronOffsetDAO intronOffsetDAO;

    public IntronOffsetServiceImpl() {
        super();
    }

    @Override
    public IntronOffset findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        IntronOffset ret = null;
        try {
            ret = intronOffsetDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public IntronOffsetDAO getIntronOffsetDAO() {
        return intronOffsetDAO;
    }

    public void setIntronOffsetDAO(IntronOffsetDAO intronOffsetDAO) {
        this.intronOffsetDAO = intronOffsetDAO;
    }

}
