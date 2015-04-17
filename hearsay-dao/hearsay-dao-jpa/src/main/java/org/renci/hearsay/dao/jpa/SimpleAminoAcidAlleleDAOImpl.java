package org.renci.hearsay.dao.jpa;

import org.renci.hearsay.dao.SimpleAminoAcidAlleleDAO;
import org.renci.hearsay.dao.model.SimpleAminoAcidAllele;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleAminoAcidAlleleDAOImpl extends BaseEntityDAOImpl<SimpleAminoAcidAllele, Long> implements
        SimpleAminoAcidAlleleDAO {

    private final Logger logger = LoggerFactory.getLogger(SimpleAminoAcidAlleleDAOImpl.class);

    public SimpleAminoAcidAlleleDAOImpl() {
        super();
    }

    @Override
    public Class<SimpleAminoAcidAllele> getPersistentClass() {
        return SimpleAminoAcidAllele.class;
    }

}
