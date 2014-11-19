package org.renci.hearsay.ws.impl;

import org.renci.hearsay.dao.GenomicRefSeqDAO;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GenomicRefSeq;
import org.renci.hearsay.ws.GenomicRefSeqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenomicRefSeqServiceImpl implements GenomicRefSeqService {

    private final Logger logger = LoggerFactory.getLogger(GenomicRefSeqServiceImpl.class);

    private GenomicRefSeqDAO genomicRefSeqDAO;

    public GenomicRefSeqServiceImpl() {
        super();
    }

    @Override
    public GenomicRefSeq findById(Long id) {
        logger.debug("ENTERING findById(Long)");
        GenomicRefSeq ret = null;
        try {
            ret = genomicRefSeqDAO.findById(id);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public GenomicRefSeqDAO getGenomicRefSeqDAO() {
        return genomicRefSeqDAO;
    }

    public void setGenomicRefSeqDAO(GenomicRefSeqDAO genomicRefSeqDAO) {
        this.genomicRefSeqDAO = genomicRefSeqDAO;
    }

}
