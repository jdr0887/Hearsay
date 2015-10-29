package org.renci.hearsay.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.collections4.CollectionUtils;
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
        emf = Persistence.createEntityManagerFactory("test-hearsay");
        em = emf.createEntityManager();
    }

    @Test
    public void loadChromosomes() {

        ChromosomeDAOImpl chromosomeDAO = new ChromosomeDAOImpl();
        chromosomeDAO.setEntityManager(em);

        List<Chromosome> allChromosomes = new ArrayList<Chromosome>();

        try {
            allChromosomes.addAll(chromosomeDAO.findAll());
            if (CollectionUtils.isNotEmpty(allChromosomes)) {
                em.getTransaction().begin();
                chromosomeDAO.delete(allChromosomes);
                em.getTransaction().commit();
            }
        } catch (HearsayDAOException e1) {
            e1.printStackTrace();
        }

        try {
            em.getTransaction().begin();
            for (int i = 1; i < 22; i++) {
                chromosomeDAO.save(new Chromosome(i + ""));
            }
            chromosomeDAO.save(new Chromosome("X"));
            chromosomeDAO.save(new Chromosome("Y"));
            chromosomeDAO.save(new Chromosome("MT"));
            em.getTransaction().commit();
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
