package org.renci.hearsay.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import org.renci.hearsay.dao.GeneDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Gene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneDAOImpl extends BaseEntityDAOImpl<Gene, Long> implements GeneDAO {

    private final Logger logger = LoggerFactory.getLogger(GeneDAOImpl.class);

    public GeneDAOImpl() {
        super();
    }

    @Override
    public Class<Gene> getPersistentClass() {
        return Gene.class;
    }

    @Override
    public List<Gene> findAll() throws HearsayDAOException {
        logger.debug("ENTERING findAll()");
        TypedQuery<Gene> query = getEntityManager().createNamedQuery("Gene.findAll", Gene.class);
        List<Gene> ret = query.getResultList();
        return ret;
    }

}
