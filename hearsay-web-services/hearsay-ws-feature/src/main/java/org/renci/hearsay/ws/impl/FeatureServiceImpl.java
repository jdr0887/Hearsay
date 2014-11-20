package org.renci.hearsay.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.renci.hearsay.dao.FeatureDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Feature;
import org.renci.hearsay.ws.FeatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeatureServiceImpl implements FeatureService {

    private final Logger logger = LoggerFactory.getLogger(FeatureServiceImpl.class);

    private FeatureDAO featureDAO;

    public FeatureServiceImpl() {
        super();
    }

    @Override
    public Feature findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        Feature ret = null;
        try {
            ret = featureDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<Feature> findByTranscriptRefSeqId(Long transcriptRefSeqId) {
        logger.debug("ENTERING findByTranscriptRefSeqId()");
        List<Feature> ret = new ArrayList<Feature>();
        try {
            ret.addAll(featureDAO.findByTranscriptRefSeqId(transcriptRefSeqId));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public FeatureDAO getFeatureDAO() {
        return featureDAO;
    }

    public void setFeatureDAO(FeatureDAO featureDAO) {
        this.featureDAO = featureDAO;
    }

}
