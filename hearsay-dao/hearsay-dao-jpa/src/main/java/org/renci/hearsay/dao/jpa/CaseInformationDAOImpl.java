package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.CaseInformationDAO;
import org.renci.hearsay.dao.model.CaseInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaseInformationDAOImpl extends BaseDAOImpl<CaseInformation, Long> implements CaseInformationDAO {

    private final Logger logger = LoggerFactory.getLogger(CaseInformationDAOImpl.class);

    public CaseInformationDAOImpl() {
        super();
    }

    @Override
    public Class<CaseInformation> getPersistentClass() {
        return CaseInformation.class;
    }

}
