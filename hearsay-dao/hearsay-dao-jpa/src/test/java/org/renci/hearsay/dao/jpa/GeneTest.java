package org.renci.hearsay.dao.jpa;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.hearsay.dao.HearsayDAOBean;
import org.renci.hearsay.dao.HearsayDAOBeanService;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Gene;

public class GeneTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    private final static HearsayDAOBeanService hearsayDAOBean = new HearsayDAOBean();

    public GeneTest() {
        super();
    }

    @BeforeClass
    public static void setup() {
        emf = Persistence.createEntityManagerFactory("test-hearsay", null);
        em = emf.createEntityManager();

        GeneDAOImpl geneDAO = new GeneDAOImpl();
        geneDAO.setEntityManager(em);
        hearsayDAOBean.setGeneDAO(geneDAO);

    }

    @Test
    public void findAll() {
        try {
            List<Gene> potentialGenes = hearsayDAOBean.getGeneDAO().findAll();
            assertTrue(CollectionUtils.isNotEmpty(potentialGenes));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void create() {
        try {
            Gene gene = new Gene();
            gene.setDescription("asdf");
            gene.setSymbol("asdf");
            em.getTransaction().begin();
            gene.setId(hearsayDAOBean.getGeneDAO().save(gene));
            em.getTransaction().commit();
            System.out.println(gene.toString());
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDown() {
        em.close();
        emf.close();
    }

}
