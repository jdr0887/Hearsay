package org.renci.hearsay.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GeneSymbol;

public class ListGeneSymbolTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    public ListGeneSymbolTest() {
        super();
    }

    @BeforeClass
    public static void setup() {
        emf = Persistence.createEntityManagerFactory("test-hearsay", null);
        em = emf.createEntityManager();
    }

    @AfterClass
    public static void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    public void findBySymbol() {
        GeneSymbolDAOImpl geneSymbolDAO = new GeneSymbolDAOImpl();
        geneSymbolDAO.setEntityManager(em);
        try {
            List<GeneSymbol> geneSymbolList = geneSymbolDAO.findBySymbol("ZFP112");
            System.out.printf("%s%n", geneSymbolList.size());
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }
    }

}
