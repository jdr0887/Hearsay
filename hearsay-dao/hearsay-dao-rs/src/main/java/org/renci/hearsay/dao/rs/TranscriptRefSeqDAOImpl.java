package org.renci.hearsay.dao.rs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranscriptRefSeqDAO;
import org.renci.hearsay.dao.model.TranscriptRefSeq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author jdr0887
 */
public class TranscriptRefSeqDAOImpl extends BaseEntityDAOImpl<TranscriptRefSeq, Long> implements TranscriptRefSeqDAO {

    private final Logger logger = LoggerFactory.getLogger(TranscriptRefSeqDAOImpl.class);

    public TranscriptRefSeqDAOImpl() {
        super(TranscriptRefSeq.class);
    }

    @Override
    public Long save(TranscriptRefSeq entity) throws HearsayDAOException {
        logger.debug("ENTERING save(TranscriptRefSeq)");
        WebClient client = WebClient.create(getRestServiceURL()).type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        Response response = client.path("/").post(entity);
        Long id = response.readEntity(Long.class);
        return id;
    }

    @Override
    public TranscriptRefSeq findById(Long id) throws HearsayDAOException {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        TranscriptRefSeq transcriptRefSeq = client.path("findById/{id}", id).accept(MediaType.APPLICATION_JSON)
                .get(TranscriptRefSeq.class);
        return transcriptRefSeq;
    }

    @Override
    public List<TranscriptRefSeq> findAll() throws HearsayDAOException {
        return null;
    }

    @Override
    public List<TranscriptRefSeq> findByExample(TranscriptRefSeq transcriptRefSeq) throws HearsayDAOException {
        return null;
    }

    @Override
    public List<TranscriptRefSeq> findByGeneId(Long geneId) throws HearsayDAOException {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders(), true);
        Collection<? extends TranscriptRefSeq> results = client.path("findByGeneId/{geneId}", geneId)
                .accept(MediaType.APPLICATION_JSON).getCollection(TranscriptRefSeq.class);
        return new ArrayList<TranscriptRefSeq>(results);
    }

    @Override
    public List<TranscriptRefSeq> findByGeneName(String name) throws HearsayDAOException {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders(), true);
        Collection<? extends TranscriptRefSeq> results = client.path("findByGeneName/{geneName}", name)
                .accept(MediaType.APPLICATION_JSON).getCollection(TranscriptRefSeq.class);
        return new ArrayList<TranscriptRefSeq>(results);
    }

    @Override
    public List<TranscriptRefSeq> findByAccession(String accession) throws HearsayDAOException {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders(), true);
        Collection<? extends TranscriptRefSeq> results = client.path("findByAccession/{accession}", accession)
                .accept(MediaType.APPLICATION_JSON).getCollection(TranscriptRefSeq.class);
        return new ArrayList<TranscriptRefSeq>(results);
    }

}
