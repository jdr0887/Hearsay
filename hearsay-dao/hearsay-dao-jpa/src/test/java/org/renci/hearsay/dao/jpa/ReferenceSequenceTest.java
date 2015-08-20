package org.renci.hearsay.dao.jpa;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
import org.renci.hearsay.dao.HearsayDAOBean;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Identifier;
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

        IdentifierDAOImpl identifierDAO = new IdentifierDAOImpl();
        identifierDAO.setEntityManager(em);
        hearsayDAOBean.setIdentifierDAO(identifierDAO);

    }

    @Test
    public void findByIdentifiers() {

        try {
            List<Identifier> rnaNucleotideAccessionIdentifierList = hearsayDAOBean.getIdentifierDAO().findByExample(
                    new Identifier("www.ncbi.nlm.nih.gov/nuccore", "NM_130787.2"));

            List<Identifier> proteinAccessionIdentifierList = hearsayDAOBean.getIdentifierDAO().findByExample(
                    new Identifier("www.ncbi.nlm.nih.gov/protein", "NP_570603.2"));

            List<Long> identifierIdList = new ArrayList<Long>();
            for (Identifier identifier : rnaNucleotideAccessionIdentifierList) {
                identifierIdList.add(identifier.getId());
            }
            for (Identifier identifier : proteinAccessionIdentifierList) {
                identifierIdList.add(identifier.getId());
            }

            List<ReferenceSequence> potentialRefSeqs = hearsayDAOBean.getReferenceSequenceDAO().findByIdentifiers(
                    identifierIdList);

            assertTrue(CollectionUtils.isNotEmpty(potentialRefSeqs));
            assertTrue(potentialRefSeqs.size() == 1);
            serialize(potentialRefSeqs.get(0));

        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void findAll() {

        try {
            long startTime = System.currentTimeMillis();
            List<ReferenceSequence> potentialRefSeqs = hearsayDAOBean.getReferenceSequenceDAO().findAll();
            long endTime = System.currentTimeMillis();
            System.out.printf("duration: %d minutes", (endTime - startTime) / 1000 / 60);
            assertTrue(CollectionUtils.isNotEmpty(potentialRefSeqs));
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }

    }

    private void serialize(ReferenceSequence referenceSequence) {

        try {
            JAXBContext context = JAXBContext.newInstance(ReferenceSequence.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            FileWriter fw = new FileWriter(new File("/tmp", String.format("referenceSequence-%d.xml",
                    referenceSequence.getId())));
            m.marshal(referenceSequence, fw);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (PropertyException e1) {
            e1.printStackTrace();
        } catch (JAXBException e1) {
            e1.printStackTrace();
        }

    }

    @AfterClass
    public static void tearDown() {
        em.close();
        emf.close();
    }

}
