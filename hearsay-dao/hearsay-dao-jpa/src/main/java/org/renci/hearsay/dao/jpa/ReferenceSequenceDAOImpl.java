package org.renci.hearsay.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.ReferenceSequenceDAO;
import org.renci.hearsay.dao.model.ReferenceSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReferenceSequenceDAOImpl extends BaseEntityDAOImpl<ReferenceSequence, Long> implements
        ReferenceSequenceDAO {

    private final Logger logger = LoggerFactory.getLogger(ReferenceSequenceDAOImpl.class);

    public ReferenceSequenceDAOImpl() {
        super();
    }

    @Override
    public Class<ReferenceSequence> getPersistentClass() {
        return ReferenceSequence.class;
    }

    @Override
    public ReferenceSequence findByAccession(String accession) throws HearsayDAOException {
        logger.debug("ENTERING findByAccession(String)");
        TypedQuery<ReferenceSequence> query = getEntityManager().createNamedQuery("ReferenceSequence.findByAccession",
                ReferenceSequence.class);
        query.setParameter("accession", accession);
        List<ReferenceSequence> ret = new ArrayList<ReferenceSequence>();
        ret.addAll(query.getResultList());
        return ret.get(0);
    }

}
