package org.renci.hearsay.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.PopulationFrequencyDAO;
import org.renci.hearsay.dao.model.PopulationFrequency;
import org.renci.hearsay.ws.PopulationFrequencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PopulationFrequencyServiceImpl implements PopulationFrequencyService {

    private final Logger logger = LoggerFactory.getLogger(PopulationFrequencyServiceImpl.class);

    private PopulationFrequencyDAO populationFrequencyDAO;

    public PopulationFrequencyServiceImpl() {
        super();
    }

    @Override
    public PopulationFrequency findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        PopulationFrequency ret = null;
        try {
            ret = populationFrequencyDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public List<PopulationFrequency> findBySourceAndVersion(String source, String version) {
        logger.debug("ENTERING findById(Long)");
        List<PopulationFrequency> ret = new ArrayList<PopulationFrequency>();
        try {
            ret.addAll(populationFrequencyDAO.findBySourceAndVersion(source, version));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public PopulationFrequencyDAO getPopulationFrequencyDAO() {
        return populationFrequencyDAO;
    }

    public void setPopulationFrequencyDAO(PopulationFrequencyDAO populationFrequencyDAO) {
        this.populationFrequencyDAO = populationFrequencyDAO;
    }

}
