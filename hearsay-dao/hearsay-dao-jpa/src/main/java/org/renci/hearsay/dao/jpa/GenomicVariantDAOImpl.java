package org.renci.hearsay.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import org.renci.hearsay.dao.GenomicVariantDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GenomicVariant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenomicVariantDAOImpl extends BaseEntityDAOImpl<GenomicVariant, Long> implements GenomicVariantDAO {

    private final Logger logger = LoggerFactory.getLogger(GenomicVariantDAOImpl.class);

    public GenomicVariantDAOImpl() {
        super();
    }

    @Override
    public Class<GenomicVariant> getPersistentClass() {
        return GenomicVariant.class;
    }

    @Override
    public List<GenomicVariant> findByGeneName(String name) throws HearsayDAOException {
        logger.debug("ENTERING findByGeneName(String)");
        TypedQuery<GenomicVariant> query = getEntityManager().createNamedQuery("GenomicVariant.findByGeneName",
                GenomicVariant.class);
        query.setParameter("name", name);
        List<GenomicVariant> ret = query.getResultList();
        return ret;
    }

}
