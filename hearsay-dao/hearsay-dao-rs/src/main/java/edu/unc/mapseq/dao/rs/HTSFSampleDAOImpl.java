package edu.unc.mapseq.dao.rs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.unc.mapseq.config.MaPSeqConfigurationService;
import edu.unc.mapseq.dao.HTSFSampleDAO;
import edu.unc.mapseq.dao.MaPSeqDAOException;
import edu.unc.mapseq.dao.model.HTSFSample;

/**
 * 
 * @author jdr0887
 */
public class HTSFSampleDAOImpl extends BaseEntityDAOImpl<HTSFSample, Long> implements HTSFSampleDAO {

    private final Logger logger = LoggerFactory.getLogger(HTSFSampleDAOImpl.class);

    private MaPSeqConfigurationService configurationService;

    public HTSFSampleDAOImpl() {
        super(HTSFSample.class);
    }

    public void init() {
        setRestServiceURL(String.format("http://%1$s:%2$d/cxf/%3$s/%3$sService", getConfigurationService()
                .getWebServiceHost("localhost"), 8181, HTSFSample.class.getSimpleName()));
    }

    @Override
    public HTSFSample findById(Long id) throws MaPSeqDAOException {
        logger.debug("ENTERING findById(Long)");
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        HTSFSample sample = client.path("findById/{id}", id).accept(MediaType.APPLICATION_JSON).get(HTSFSample.class);
        return sample;
    }

    @Override
    public Long save(HTSFSample entity) throws MaPSeqDAOException {
        logger.debug("ENTERING save(HTSFSample)");
        WebClient client = WebClient.create(getRestServiceURL()).type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        Response response = client.path("/").post(entity);
        Long id = response.readEntity(Long.class);
        return id;
    }

    @Override
    public List<HTSFSample> findBySequencerRunId(Long sequencerRunId) throws MaPSeqDAOException {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Collection<? extends HTSFSample> results = client.path("findBySequencerRunId/{id}", sequencerRunId)
                .accept(MediaType.APPLICATION_JSON).getCollection(HTSFSample.class);
        return new ArrayList<HTSFSample>(results);
    }

    @Override
    public List<HTSFSample> findBySequencerRunIdAndSampleName(Long sequencerRunId, String sampleName)
            throws MaPSeqDAOException {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Collection<? extends HTSFSample> results = client
                .path("findBySequencerRunIdAndSampleName/{sequencerRunId}/{sampleName}", sequencerRunId, sampleName)
                .accept(MediaType.APPLICATION_JSON).getCollection(HTSFSample.class);
        return new ArrayList<HTSFSample>(results);
    }

    @Override
    public List<HTSFSample> findByName(String name) throws MaPSeqDAOException {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Collection<? extends HTSFSample> results = client.path("findByName/{name}", name)
                .accept(MediaType.APPLICATION_JSON).getCollection(HTSFSample.class);
        return new ArrayList<HTSFSample>(results);
    }

    public MaPSeqConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(MaPSeqConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

}
