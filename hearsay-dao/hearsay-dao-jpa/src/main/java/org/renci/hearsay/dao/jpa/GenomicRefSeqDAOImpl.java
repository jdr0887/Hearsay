package org.renci.hearsay.dao.jpa;

import java.util.List;

import org.renci.hearsay.dao.GenomicRefSeqDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GenomicRefSeq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenomicRefSeqDAOImpl extends BaseEntityDAOImpl<GenomicRefSeq, Long> implements GenomicRefSeqDAO {

    private final Logger logger = LoggerFactory.getLogger(GenomicRefSeqDAOImpl.class);

    public GenomicRefSeqDAOImpl() {
        super();
    }

    @Override
    public Class<GenomicRefSeq> getPersistentClass() {
        return GenomicRefSeq.class;
    }

    @Override
    public List<GenomicRefSeq> findAll() throws HearsayDAOException {
        return null;
    }

    @Override
    public List<GenomicRefSeq> findByExample(GenomicRefSeq referenceGenome) throws HearsayDAOException {
        return null;
    }

    @Override
    public List<GenomicRefSeq> findBySource(String version) throws HearsayDAOException {
        return null;
    }

    @Override
    public GenomicRefSeq findBySourceAndVersion(String source, String version) throws HearsayDAOException {
        return null;
    }

}
