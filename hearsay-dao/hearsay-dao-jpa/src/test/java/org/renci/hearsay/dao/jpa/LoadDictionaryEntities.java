package org.renci.hearsay.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Chromosome;

public class LoadDictionaryEntities {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    @BeforeClass
    public static void setup() {
        emf = Persistence.createEntityManagerFactory("test-hearsay", null);
        em = emf.createEntityManager();
    }

    @Test
    public void loadChromosomes() {

        ChromosomeDAOImpl chromosomeDAO = new ChromosomeDAOImpl();
        chromosomeDAO.setEntityManager(em);

        try {
            for (int i = 1; i < 22; i++) {
                chromosomeDAO.save(new Chromosome(i + ""));
            }
            chromosomeDAO.save(new Chromosome("X"));
            chromosomeDAO.save(new Chromosome("Y"));
            chromosomeDAO.save(new Chromosome("MT"));
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
