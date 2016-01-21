package org.renci.hearsay.dao.rs;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.renci.hearsay.dao.GenomeReferenceDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GenomeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @author jdr0887
 */
@Component
public class GenomeReferenceDAOImpl extends BaseEntityDAOImpl<GenomeReference, Long> implements GenomeReferenceDAO {

    private final Logger logger = LoggerFactory.getLogger(GenomeReferenceDAOImpl.class);

    public GenomeReferenceDAOImpl() {
        super(GenomeReference.class);
    }

    @Override
    public Long save(GenomeReference entity) throws HearsayDAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GenomeReference findById(Long id) throws HearsayDAOException {
        WebClient client = WebClient.create(getRestServiceURL(), getProviders());
        GenomeReference genomeReference = client.path("findById/{id}", id).accept(MediaType.APPLICATION_JSON).get(GenomeReference.class);
        return genomeReference;
    }

    @Override
    public List<GenomeReference> findAll() throws HearsayDAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GenomeReference> findByName(String name) throws HearsayDAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GenomeReference> findByExample(GenomeReference genomeReference) throws HearsayDAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GenomeReference> findByIdentifierSystemAndValue(String system, String value) throws HearsayDAOException {
        // TODO Auto-generated method stub
        return null;
    }

}
