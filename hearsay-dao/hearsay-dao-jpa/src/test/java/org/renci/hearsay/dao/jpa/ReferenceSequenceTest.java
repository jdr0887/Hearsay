package org.renci.hearsay.dao.jpa;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.hearsay.dao.HearsayDAOBean;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Gene;
import org.renci.hearsay.dao.model.ReferenceSequence;

public class ReferenceSequenceTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    private final static HearsayDAOBean hearsayDAOBean = new HearsayDAOBean();

    public ReferenceSequenceTest() {
        super();
    }

    @BeforeClass
    public static void setup() {
        emf = Persistence.createEntityManagerFactory("test-hearsay", null);
        em = emf.createEntityManager();

        ReferenceSequenceDAOImpl referenceSequenceDAO = new ReferenceSequenceDAOImpl();
        referenceSequenceDAO.setEntityManager(em);
        hearsayDAOBean.setReferenceSequenceDAO(referenceSequenceDAO);

    }

    @Test
    public void findAll() {
        String format = "%1$-10s %2$-14s %3$s%n";
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        formatter.format(format, "ID", "CDS Start", "CDS End");

        List<ReferenceSequence> referenceSequenceList = new ArrayList<ReferenceSequence>();
        try {
            referenceSequenceList.addAll(hearsayDAOBean.getReferenceSequenceDAO().findAll());
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }

        for (ReferenceSequence referenceSequence : referenceSequenceList) {
            formatter.format(format, referenceSequence.getId(), referenceSequence.getCdsStart(), referenceSequence.getCdsEnd());
            formatter.flush();
        }
        System.out.println(formatter.toString());
        formatter.close();
    }

    @Test
    public void findByIdentifierValue() {
        List<Gene> geneList = new ArrayList<Gene>();
        try {
            geneList.addAll(hearsayDAOBean.getGeneDAO().findByIdentifierValue("2720"));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }

        System.out.println(geneList.size());
    }

    @AfterClass
    public static void tearDown() {
        em.close();
        emf.close();
    }

}
