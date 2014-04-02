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
import edu.unc.mapseq.dao.MaPSeqDAOException;
import edu.unc.mapseq.dao.SequencerRunDAO;
import edu.unc.mapseq.dao.model.SequencerRun;

/**
 * 
 * @author jdr0887
 */
public class SequencerRunDAOImpl extends BaseEntityDAOImpl<SequencerRun, Long> implements SequencerRunDAO {

    private final Logger logger = LoggerFactory.getLogger(SequencerRunDAOImpl.class);

    private MaPSeqConfigurationService configurationService;

    public SequencerRunDAOImpl() {
        super(SequencerRun.class);
    }

    public void init() {
        setRestServiceURL(String.format("http://%1$s:%2$d/cxf/%3$s/%3$sService", getConfigurationService()
                .getWebServiceHost("localhost"), 8181, SequencerRun.class.getSimpleName()));
    }

    @Override
    public Long save(SequencerRun entity) throws MaPSeqDAOException {
        logger.debug("ENTERING save(Long)");
        WebClient client = WebClient.create(getRestServiceURL()).type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        Response response = client.path("/").post(entity);
        Long id = response.readEntity(Long.class);
        return id;
    }

    @Override
    public List<SequencerRun> findByAccountId(Long id) throws MaPSeqDAOException {
        logger.debug("ENTERING findByAccountId(Long)");
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Collection<? extends SequencerRun> ret = client.path("findByName/{accountId}", id)
                .accept(MediaType.APPLICATION_JSON).getCollection(SequencerRun.class);
        return new ArrayList<SequencerRun>(ret);
    }

    @Override
    public List<SequencerRun> findByName(String name) throws MaPSeqDAOException {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Collection<? extends SequencerRun> ret = client.path("findByName/{name}", name)
                .accept(MediaType.APPLICATION_JSON).getCollection(SequencerRun.class);
        return new ArrayList<SequencerRun>(ret);
    }

    @Override
    public SequencerRun findById(Long id) throws MaPSeqDAOException {
        logger.debug("ENTERING findById(Long)");
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        SequencerRun sequencerRun = client.path("findById/{id}", id).accept(MediaType.APPLICATION_JSON)
                .get(SequencerRun.class);
        return sequencerRun;
    }

    @Override
    public List<SequencerRun> findByStudyName(String name) throws MaPSeqDAOException {
        logger.debug("ENTERING findByStudyName(String)");
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Collection<? extends SequencerRun> ret = client.path("findByStudyName/{name}", name)
                .accept(MediaType.APPLICATION_JSON).getCollection(SequencerRun.class);
        return new ArrayList<SequencerRun>(ret);
    }

    @Override
    public List<SequencerRun> findByExample(SequencerRun sequencerRun) throws MaPSeqDAOException {
        return null;
    }

    @Override
    public List<SequencerRun> findAll() throws MaPSeqDAOException {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Collection<? extends SequencerRun> ret = client.path("findAll").accept(MediaType.APPLICATION_JSON)
                .getCollection(SequencerRun.class);
        return new ArrayList<SequencerRun>(ret);
    }

    public MaPSeqConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(MaPSeqConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

}
