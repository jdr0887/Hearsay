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
import edu.unc.mapseq.dao.StudyDAO;
import edu.unc.mapseq.dao.model.Study;

/**
 * 
 * @author jdr0887
 */
public class StudyDAOImpl extends BaseEntityDAOImpl<Study, Long> implements StudyDAO {

    private final Logger logger = LoggerFactory.getLogger(StudyDAOImpl.class);

    private MaPSeqConfigurationService configurationService;

    public StudyDAOImpl() {
        super(Study.class);
    }

    public void init() {
        setRestServiceURL(String.format("http://%1$s:%2$d/cxf/%3$s/%3$sService", getConfigurationService()
                .getWebServiceHost("localhost"), 8181, Study.class.getSimpleName()));
    }

    @Override
    public Long save(Study entity) throws MaPSeqDAOException {
        logger.debug("ENTERING save(Study)");
        WebClient client = WebClient.create(getRestServiceURL()).type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        Response response = client.path("/").post(entity);
        Long id = response.readEntity(Long.class);
        return id;
    }

    @Override
    public Study findByName(String name) throws MaPSeqDAOException {
        logger.debug("ENTERING findByName(String)");
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Study study = client.path("findByName/{name}", name).accept(MediaType.APPLICATION_JSON).get(Study.class);
        return study;
    }

    @Override
    public Study findById(Long id) throws MaPSeqDAOException {
        logger.debug("ENTERING findById(Long)");
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Study study = client.path("findById/{id}", id).accept(MediaType.APPLICATION_JSON).get(Study.class);
        return study;
    }

    @Override
    public List<Study> findAll() throws MaPSeqDAOException {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Collection<? extends Study> ret = client.path("findAll").accept(MediaType.APPLICATION_JSON)
                .getCollection(Study.class);
        return new ArrayList<Study>(ret);
    }

    @Override
    public Study findByHTSFSampleId(Long htsfSampleId) throws MaPSeqDAOException {
        logger.debug("ENTERING findByHTSFSampleId(Long)");
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Study study = client.path("findByHTSFSampleId/{id}", htsfSampleId).accept(MediaType.APPLICATION_JSON)
                .get(Study.class);
        return study;
    }

    public MaPSeqConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(MaPSeqConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

}
