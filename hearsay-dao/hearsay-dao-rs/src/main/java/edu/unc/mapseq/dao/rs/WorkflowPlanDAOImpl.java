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
import edu.unc.mapseq.dao.WorkflowPlanDAO;
import edu.unc.mapseq.dao.model.WorkflowPlan;

/**
 * 
 * @author jdr0887
 */
public class WorkflowPlanDAOImpl extends BaseDAOImpl<WorkflowPlan, Long> implements WorkflowPlanDAO {

    private final Logger logger = LoggerFactory.getLogger(WorkflowPlanDAOImpl.class);

    private MaPSeqConfigurationService configurationService;

    public WorkflowPlanDAOImpl() {
        super(WorkflowPlan.class);
    }

    public void init() {
        setRestServiceURL(String.format("http://%1$s:%2$d/cxf/%3$s/%3$sService", getConfigurationService()
                .getWebServiceHost("localhost"), 8181, WorkflowPlan.class.getSimpleName()));
    }

    @Override
    public Long save(WorkflowPlan entity) throws MaPSeqDAOException {
        logger.info("ENTERING save(WorkflowPlan)");
        WebClient client = WebClient.create(getRestServiceURL()).type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        Response response = client.path("/").post(entity);
        Long id = response.readEntity(Long.class);
        return id;
    }

    @Override
    public List<WorkflowPlan> findBySequencerRunId(Long sequencerRunId) throws MaPSeqDAOException {
        logger.info("ENTERING findBySequencerRunId(Long)");
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Collection<? extends WorkflowPlan> ret = client.path("findBySequencerRunId/{sequencerRunId}", sequencerRunId)
                .accept(MediaType.APPLICATION_JSON).getCollection(WorkflowPlan.class);
        return new ArrayList<WorkflowPlan>(ret);
    }

    @Override
    public List<WorkflowPlan> findByHTSFSampleId(Long htsfSampleId) throws MaPSeqDAOException {
        logger.info("ENTERING findByHTSFSampleId(Long)");
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Collection<? extends WorkflowPlan> ret = client.path("findByHTSFSampleId/{htsfSampleId}", htsfSampleId)
                .accept(MediaType.APPLICATION_JSON).getCollection(WorkflowPlan.class);
        return new ArrayList<WorkflowPlan>(ret);
    }

    @Override
    public List<WorkflowPlan> findByWorkflowRunId(Long workflowRunId) throws MaPSeqDAOException {
        logger.info("ENTERING findByWorkflowRunId(Long)");
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Collection<? extends WorkflowPlan> ret = client.path("findByWorkflowRunId/{workflowRunId}", workflowRunId)
                .accept(MediaType.APPLICATION_JSON).getCollection(WorkflowPlan.class);
        return new ArrayList<WorkflowPlan>(ret);
    }

    @Override
    public WorkflowPlan findById(Long id) throws MaPSeqDAOException {
        logger.info("ENTERING findById(Long)");
        return null;
    }

    @Override
    public List<WorkflowPlan> findByStudyName(String studyName) throws MaPSeqDAOException {
        logger.info("ENTERING findByStudyName(String)");
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Collection<? extends WorkflowPlan> ret = client.path("findByStudyName/{studyName}", studyName)
                .accept(MediaType.APPLICATION_JSON).getCollection(WorkflowPlan.class);
        return new ArrayList<WorkflowPlan>(ret);
    }

    @Override
    public List<WorkflowPlan> findEnqueued(Long workflowId) throws MaPSeqDAOException {
        logger.info("ENTERING findEnqueued(Long)");
        return null;
    }

    @Override
    public List<WorkflowPlan> findEnqueued(Long workflowId, int maxResults) throws MaPSeqDAOException {
        logger.info("ENTERING findEnqueued(Long)");
        return null;
    }

    @Override
    public List<WorkflowPlan> findBySequencerRunAndWorkflowName(Long sequencerRunId, String workflowName)
            throws MaPSeqDAOException {
        logger.info("ENTERING findBySequencerRunAndWorkflowName(Long, String)");
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Collection<? extends WorkflowPlan> ret = client
                .path("findBySequencerRunAndWorkflowName/{sequencerRunId}/{workflowName}", sequencerRunId, workflowName)
                .accept(MediaType.APPLICATION_JSON).getCollection(WorkflowPlan.class);
        return new ArrayList<WorkflowPlan>(ret);
    }

    @Override
    public List<WorkflowPlan> findByStudyNameAndSampleNameAndWorkflowName(String studyName, String sampleName,
            String workflowName) throws MaPSeqDAOException {
        logger.info("ENTERING findByStudyNameAndSampleNameAndWorkflowName(String, String, String)");
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Collection<? extends WorkflowPlan> ret = client
                .path("findByStudyNameAndSampleNameAndWorkflowName/{studyName}/{sampleName}/{workflowName}", studyName,
                        sampleName, workflowName).accept(MediaType.APPLICATION_JSON).getCollection(WorkflowPlan.class);
        return new ArrayList<WorkflowPlan>(ret);
    }

    public MaPSeqConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(MaPSeqConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

}
