package org.renci.hearsay.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.TranscriptVariantDAO;
import org.renci.hearsay.dao.model.TranscriptVariant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranscriptVariantDAOImpl extends BaseEntityDAOImpl<TranscriptVariant, Long> implements
        TranscriptVariantDAO {

    private final Logger logger = LoggerFactory.getLogger(TranscriptVariantDAOImpl.class);

    public TranscriptVariantDAOImpl() {
        super();
    }

    @Override
    public Class<TranscriptVariant> getPersistentClass() {
        return TranscriptVariant.class;
    }

    @Override
    public List<TranscriptVariant> findByGeneName(String name) throws HearsayDAOException {
        logger.debug("ENTERING findByGeneName(String)");
        TypedQuery<TranscriptVariant> query = getEntityManager().createNamedQuery("TranscriptVariant.findByGeneName",
                TranscriptVariant.class);
        query.setParameter("name", name);
        List<TranscriptVariant> ret = query.getResultList();
        return ret;
    }

}
