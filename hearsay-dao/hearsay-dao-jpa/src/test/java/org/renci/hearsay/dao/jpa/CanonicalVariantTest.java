package org.renci.hearsay.dao.jpa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.apache.commons.collections.CollectionUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.CanonicalAllele;
import org.renci.hearsay.dao.model.Gene;

public class CanonicalVariantTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    @BeforeClass
    public static void setup() {
        emf = Persistence.createEntityManagerFactory("test-hearsay", null);
        em = emf.createEntityManager();
    }

    @Test
    public void findByGene() throws HearsayDAOException {

        GeneDAOImpl geneDAO = new GeneDAOImpl();
        geneDAO.setEntityManager(em);

        CanonicalAlleleDAOImpl canonicalVariantDAO = new CanonicalAlleleDAOImpl();
        canonicalVariantDAO.setEntityManager(em);

        List<Gene> genes = geneDAO.findAll();
        if (CollectionUtils.isNotEmpty(genes)) {
            for (Gene gene : genes) {
                // List<CanonicalAllele> canonicalVariants = canonicalVariantDAO.findByGeneName(gene.getName());
                // if (canonicalVariants != null && !canonicalVariants.isEmpty()) {
                // System.out.println("asdfasdf");
                // }
            }
        }

    }

    @Test
    public void findByGeneName() throws HearsayDAOException {

        GeneDAOImpl geneDAO = new GeneDAOImpl();
        geneDAO.setEntityManager(em);

        // CanonicalVariantDAOImpl canonicalVariantDAO = new CanonicalVariantDAOImpl();
        // canonicalVariantDAO.setEntityManager(em);
        //
        // List<CanonicalVariant> canonicalVariants = canonicalVariantDAO.findByGeneName("BRCA1");
        // if (canonicalVariants != null && !canonicalVariants.isEmpty()) {
        // for (CanonicalVariant canonicalVariant : canonicalVariants) {
        // Set<VariantRepresentation> variants = canonicalVariant.getVariants();
        // if (variants != null && !variants.isEmpty()) {
        // for (VariantRepresentation variant : variants) {
        // System.out.println(variant.toString());
        // }
        // }
        // }
        // }

    }

    @Test
    public void testCanonicalAlleleSerialization() {

        try {
            CanonicalAlleleDAOImpl canonicalVariantDAO = new CanonicalAlleleDAOImpl();
            canonicalVariantDAO.setEntityManager(em);

            JAXBContext context = JAXBContext.newInstance(CanonicalAllele.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            FileWriter fw = new FileWriter(new File("/tmp/canonicalAllele.xml"));
            m.marshal(canonicalVariantDAO.findById(94483L), fw);
        } catch (JAXBException | IOException | HearsayDAOException e) {
            e.printStackTrace();
        }

    }

    @AfterClass
    public static void tearDown() {
        em.close();
        emf.close();
    }

}
