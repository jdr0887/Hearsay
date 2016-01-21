package org.renci.hearsay.dao.rs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.renci.hearsay.dao.GeneSymbolDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GeneSymbol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @author jdr0887
 */
@Component
public class GeneSymbolDAOImpl extends BaseEntityDAOImpl<GeneSymbol, Long> implements GeneSymbolDAO {

    private final Logger logger = LoggerFactory.getLogger(GeneSymbolDAOImpl.class);

    public GeneSymbolDAOImpl() {
        super(GeneSymbol.class);
    }

    @Override
    public Long save(GeneSymbol entity) throws HearsayDAOException {
        return null;
    }

    @Override
    public GeneSymbol findById(Long id) throws HearsayDAOException {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        GeneSymbol geneSymbol = client.path("findById/{id}", id).accept(MediaType.APPLICATION_JSON).get(GeneSymbol.class);
        return geneSymbol;
    }

    @Override
    public List<GeneSymbol> findBySymbol(String symbol) throws HearsayDAOException {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders(), true);
        Collection<? extends GeneSymbol> results = client.path("findBySymbol/{symbol}", symbol).accept(MediaType.APPLICATION_JSON)
                .getCollection(GeneSymbol.class);
        return new ArrayList<GeneSymbol>(results);
    }

}
