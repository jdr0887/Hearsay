package org.renci.hearsay.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.hearsay.dao.HearsayDAOBean;
import org.renci.hearsay.dao.HearsayDAOBeanService;
import org.renci.hearsay.dao.HearsayDAOException;

public class IdentifierTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    private final static HearsayDAOBeanService hearsayDAOBean = new HearsayDAOBean();

    public IdentifierTest() {
        super();
    }

    @BeforeClass
    public static void setup() {
        emf = Persistence.createEntityManagerFactory("test-hearsay", null);
        em = emf.createEntityManager();

        ReferenceSequenceDAOImpl referenceSequenceDAO = new ReferenceSequenceDAOImpl();
        referenceSequenceDAO.setEntityManager(em);
        hearsayDAOBean.setReferenceSequenceDAO(referenceSequenceDAO);

        IdentifierDAOImpl identifierDAO = new IdentifierDAOImpl();
        identifierDAO.setEntityManager(em);
        hearsayDAOBean.setIdentifierDAO(identifierDAO);

    }

    @Test
    public void testFindByExample() throws HearsayDAOException {
        // Identifier identifier = new Identifier("www.ncbi.nlm.nih.gov/snp", "61748497");
        // hearsayDAOBean.getIdentifierDAO().findByExample(identifier);

        hearsayDAOBean.getReferenceSequenceDAO().findByIdentifierValue("NM_000552.3");

    }

    @AfterClass
    public static void tearDown() {
        em.close();
        emf.close();
    }

}
