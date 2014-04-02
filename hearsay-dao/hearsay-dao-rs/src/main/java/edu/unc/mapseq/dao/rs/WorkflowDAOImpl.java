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
import edu.unc.mapseq.dao.WorkflowDAO;
import edu.unc.mapseq.dao.model.Workflow;

/**
 * 
 * @author jdr0887
 */
public class WorkflowDAOImpl extends BaseEntityDAOImpl<Workflow, Long> implements WorkflowDAO {

    private final Logger logger = LoggerFactory.getLogger(WorkflowDAOImpl.class);

    private MaPSeqConfigurationService configurationService;

    public WorkflowDAOImpl() {
        super(Workflow.class);
    }

    public void init() {
        setRestServiceURL(String.format("http://%1$s:%2$d/cxf/%3$s/%3$sService", getConfigurationService()
                .getWebServiceHost("localhost"), 8181, Workflow.class.getSimpleName()));
    }

    @Override
    public Long save(Workflow entity) throws MaPSeqDAOException {
        logger.debug("ENTERING save(Workflow)");
        WebClient client = WebClient.create(getRestServiceURL()).type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        Response response = client.path("/").post(entity);
        Long id = response.readEntity(Long.class);
        return id;
    }

    @Override
    public Workflow findById(Long id) throws MaPSeqDAOException {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Workflow workflow = client.path("findById/{id}", id).accept(MediaType.APPLICATION_JSON).get(Workflow.class);
        return workflow;
    }

    @Override
    public List<Workflow> findAll() throws MaPSeqDAOException {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Collection<? extends Workflow> ret = client.path("findAll").accept(MediaType.APPLICATION_JSON)
                .getCollection(Workflow.class);
        return new ArrayList<Workflow>(ret);
    }

    @Override
    public Workflow findByName(String name) throws MaPSeqDAOException {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Workflow ret = client.path("findByName/{name}", name).accept(MediaType.APPLICATION_JSON).get(Workflow.class);
        return ret;
    }

    public MaPSeqConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(MaPSeqConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

}
