package edu.unc.mapseq.dao.rs;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.unc.mapseq.config.MaPSeqConfigurationService;
import edu.unc.mapseq.dao.AccountDAO;
import edu.unc.mapseq.dao.MaPSeqDAOException;
import edu.unc.mapseq.dao.model.Account;

/**
 * 
 * @author jdr0887
 */
public class AccountDAOImpl extends BaseEntityDAOImpl<Account, Long> implements AccountDAO {

    private final Logger logger = LoggerFactory.getLogger(AccountDAOImpl.class);

    private MaPSeqConfigurationService configurationService;

    public AccountDAOImpl() {
        super(Account.class);
    }

    public void init() {
        setRestServiceURL(String.format("http://%1$s:%2$d/cxf/%3$s/%3$sService", getConfigurationService()
                .getWebServiceHost("localhost"), 8181, Account.class.getSimpleName()));
    }

    @Override
    public Long save(Account entity) throws MaPSeqDAOException {
        logger.debug("ENTERING save(Account)");
        WebClient client = WebClient.create(getRestServiceURL()).type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        Response response = client.path("/").post(entity);
        Long id = response.readEntity(Long.class);
        return id;
    }

    @Override
    public Account findById(Long id) throws MaPSeqDAOException {
        logger.debug("ENTERING findById(Long)");
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Account account = client.path("findById/{id}", id).accept(MediaType.APPLICATION_JSON).get(Account.class);
        return account;
    }

    @Override
    public Account findByName(String name) throws MaPSeqDAOException {
        logger.debug("ENTERING findByName(String)");
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        Account ret = client.path("findByName/{name}", name).accept(MediaType.APPLICATION_JSON).get(Account.class);
        return ret;
    }

    public MaPSeqConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(MaPSeqConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

}
