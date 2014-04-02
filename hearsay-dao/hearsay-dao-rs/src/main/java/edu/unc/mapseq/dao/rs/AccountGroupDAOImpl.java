package edu.unc.mapseq.dao.rs;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.unc.mapseq.config.MaPSeqConfigurationService;
import edu.unc.mapseq.dao.AccountGroupDAO;
import edu.unc.mapseq.dao.MaPSeqDAOException;
import edu.unc.mapseq.dao.model.AccountGroup;

/**
 * 
 * @author jdr0887
 */
public class AccountGroupDAOImpl extends BaseEntityDAOImpl<AccountGroup, Long> implements AccountGroupDAO {

    private final Logger logger = LoggerFactory.getLogger(AccountGroupDAOImpl.class);

    private MaPSeqConfigurationService configurationService;

    public AccountGroupDAOImpl() {
        super(AccountGroup.class);
    }

    public void init() {
        setRestServiceURL(String.format("http://%1$s:%2$d/cxf/%3$s/%3$sService", getConfigurationService()
                .getWebServiceHost("localhost"), 8181, AccountGroup.class.getSimpleName()));
    }

    @Override
    public Long save(AccountGroup entity) throws MaPSeqDAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AccountGroup findById(Long id) throws MaPSeqDAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AccountGroup findByName(String name) throws MaPSeqDAOException {
        logger.debug("ENTERING findById(Long)");
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        AccountGroup ret = client.path("findByName/{name}", name).accept(MediaType.APPLICATION_JSON)
                .get(AccountGroup.class);
        return ret;
    }

    public MaPSeqConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(MaPSeqConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

}
