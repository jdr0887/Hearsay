package org.renci.hearsay.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.hearsay.dao.HearsayDAOBeanService;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GenomeReference;
import org.renci.hearsay.dao.model.Identifier;

public class GenomeReferenceTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    private final static HearsayDAOBeanService hearsayDAOBean = new HearsayDAOBeanServiceImpl();

    public GenomeReferenceTest() {
        super();
    }

    @BeforeClass
    public static void setup() {
        emf = Persistence.createEntityManagerFactory("test-hearsay", null);
        em = emf.createEntityManager();

        GenomeReferenceDAOImpl genomeReferenceDAO = new GenomeReferenceDAOImpl();
        genomeReferenceDAO.setEntityManager(em);
        hearsayDAOBean.setGenomeReferenceDAO(genomeReferenceDAO);

        IdentifierDAOImpl identifierDAO = new IdentifierDAOImpl();
        identifierDAO.setEntityManager(em);
        hearsayDAOBean.setIdentifierDAO(identifierDAO);

    }

    @Test
    public void testSave() throws HearsayDAOException {
        em.getTransaction().begin();
        Identifier identifier = new Identifier("www.ncbi.nlm.nih.gov/snp", "61748497");
        identifier.setId(hearsayDAOBean.getIdentifierDAO().save(identifier));

        GenomeReference genomeReference = new GenomeReference("asdfasdf");
        genomeReference.getIdentifiers().add(identifier);
        genomeReference.setId(hearsayDAOBean.getGenomeReferenceDAO().save(genomeReference));
        em.getTransaction().commit();
    }

    @AfterClass
    public static void tearDown() {
        em.close();
        emf.close();
    }

}
