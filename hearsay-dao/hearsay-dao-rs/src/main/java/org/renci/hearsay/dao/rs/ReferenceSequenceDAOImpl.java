package org.renci.hearsay.dao.rs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.ReferenceSequenceDAO;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.Identifier;
import org.renci.hearsay.dao.model.ReferenceSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @author jdr0887
 */
@Component
public class ReferenceSequenceDAOImpl extends BaseEntityDAOImpl<ReferenceSequence, Long> implements ReferenceSequenceDAO {

    private final Logger logger = LoggerFactory.getLogger(ReferenceSequenceDAOImpl.class);

    public ReferenceSequenceDAOImpl() {
        super(ReferenceSequence.class);
    }

    @Override
    public Long save(ReferenceSequence entity) throws HearsayDAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ReferenceSequence findById(Long id) throws HearsayDAOException {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        ReferenceSequence referenceSequence = client.path("findById/{id}", id).accept(MediaType.APPLICATION_JSON)
                .get(ReferenceSequence.class);
        return referenceSequence;
    }

    @Override
    public List<ReferenceSequence> findByGeneId(Long geneId) throws HearsayDAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ReferenceSequence> findByExample(ReferenceSequence referenceSequence) throws HearsayDAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ReferenceSequence> findByIdentifierSystemAndValue(String system, String value) throws HearsayDAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ReferenceSequence> findByIdentifierSystemAndValue(String fetchPlan, String system, String value)
            throws HearsayDAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ReferenceSequence> findByIdentifierSystem(String system) throws HearsayDAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ReferenceSequence> findByIdentifierSystem(String fetchPlan, String system) throws HearsayDAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ReferenceSequence> findByIdentifiers(List<Identifier> idList) throws HearsayDAOException {
        // TODO Auto-generated method stub
        return null;
    }

}
