package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.ResolvedConditionDAO;
import org.renci.hearsay.dao.model.ResolvedCondition;
import org.renci.hearsay.ws.ResolvedConditionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResolvedConditionServiceImpl implements ResolvedConditionService {

    private final Logger logger = LoggerFactory.getLogger(ResolvedConditionServiceImpl.class);

    private ResolvedConditionDAO resolvedConditionDAO;

    @Override
    public ResolvedCondition findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        ResolvedCondition ret = null;
        try {
            ret = resolvedConditionDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ResolvedConditionDAO getResolvedConditionDAO() {
        return resolvedConditionDAO;
    }

    public void setResolvedConditionDAO(ResolvedConditionDAO resolvedConditionDAO) {
        this.resolvedConditionDAO = resolvedConditionDAO;
    }

}
