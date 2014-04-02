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
import edu.unc.mapseq.dao.FileDataDAO;
import edu.unc.mapseq.dao.MaPSeqDAOException;
import edu.unc.mapseq.dao.model.FileData;

/**
 * 
 * @author jdr0887
 */
public class FileDataDAOImpl extends BaseDAOImpl<FileData, Long> implements FileDataDAO {

    private final Logger logger = LoggerFactory.getLogger(FileDataDAOImpl.class);

    private MaPSeqConfigurationService configurationService;

    public FileDataDAOImpl() {
        super(FileData.class);
    }

    public void init() {
        setRestServiceURL(String.format("http://%1$s:%2$d/cxf/%3$s/%3$sService", getConfigurationService()
                .getWebServiceHost("localhost"), 8181, FileData.class.getSimpleName()));
    }

    @Override
    public Long save(FileData entity) throws MaPSeqDAOException {
        logger.debug("ENTERING save(FileData)");
        WebClient client = WebClient.create(getRestServiceURL()).type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        Response response = client.path("/").post(entity);
        Long id = response.readEntity(Long.class);
        return id;
    }

    @Override
    public FileData findById(Long id) throws MaPSeqDAOException {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        FileData fileData = client.path("findById/{id}", id).accept(MediaType.APPLICATION_JSON).get(FileData.class);
        return fileData;
    }

    @Override
    public List<FileData> findByExample(FileData fileData) {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Collection<? extends FileData> ret = client.path("findByExample/{fileData}", fileData)
                .accept(MediaType.APPLICATION_JSON).getCollection(FileData.class);
        return new ArrayList<FileData>(ret);
    }

    public MaPSeqConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(MaPSeqConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

}
