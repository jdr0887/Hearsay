package org.renci.hearsay.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.GenomicVariant;

public class GenomicVariantTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    @BeforeClass
    public static void setup() {
        emf = Persistence.createEntityManagerFactory("test-hearsay", null);
        em = emf.createEntityManager();
    }

    @Test
    public void findByGeneName() throws HearsayDAOException {

        GenomicVariantDAOImpl genomicVariantDAO = new GenomicVariantDAOImpl();
        genomicVariantDAO.setEntityManager(em);

        List<GenomicVariant> variants = genomicVariantDAO.findByGeneName("BRCA1");
        if (variants != null && !variants.isEmpty()) {
            for (GenomicVariant variant : variants) {
                System.out.println(variant.toString());
            }
        }

    }

    @AfterClass
    public static void tearDown() {
        em.close();
        emf.close();
    }

}
