package org.renci.hearsay.dao.jpa.opm;

import org.renci.hearsay.dao.jpa.BaseDAOImpl;
import org.renci.hearsay.dao.model.opm.InternationalizedString;
import org.renci.hearsay.dao.opm.InternationalizedStringDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InternationalizedStringDAOImpl extends BaseDAOImpl<InternationalizedString, Long> implements
        InternationalizedStringDAO {

    private final Logger logger = LoggerFactory.getLogger(InternationalizedStringDAOImpl.class);

    public InternationalizedStringDAOImpl() {
        super();
    }

    @Override
    public Class<InternationalizedString> getPersistentClass() {
        return InternationalizedString.class;
    }

}
