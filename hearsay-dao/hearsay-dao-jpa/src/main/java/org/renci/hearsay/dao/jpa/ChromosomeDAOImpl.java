package org.renci.hearsay.dao.jpa;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.renci.hearsay.dao.ChromosomeDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Chromosome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OsgiServiceProvider(classes = { ChromosomeDAO.class })
@Singleton
@Transactional
public class ChromosomeDAOImpl extends BaseEntityDAOImpl<Chromosome, Long> implements ChromosomeDAO {

    private final Logger logger = LoggerFactory.getLogger(ChromosomeDAOImpl.class);

    public ChromosomeDAOImpl() {
        super();
    }

    @Override
    public Class<Chromosome> getPersistentClass() {
        return Chromosome.class;
    }

    @Override
    public List<Chromosome> findByName(String name) throws HearsayDAOException {
        logger.debug("ENTERING findByName(String)");
        TypedQuery<Chromosome> query = getEntityManager().createNamedQuery("Chromosome.findByName", Chromosome.class);
        query.setParameter("name", name);
        List<Chromosome> ret = query.getResultList();
        return ret;
    }

    @Override
    public List<Chromosome> findAll() throws HearsayDAOException {
        logger.debug("ENTERING findAll()");
        TypedQuery<Chromosome> query = getEntityManager().createNamedQuery("Chromosome.findAll", Chromosome.class);
        List<Chromosome> ret = query.getResultList();
        return ret;
    }

}
