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

import org.apache.commons.collections4.CollectionUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.hearsay.dao.HearsayDAOBeanService;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Identifier;
import org.renci.hearsay.dao.model.ReferenceSequence;

public class ReferenceSequenceTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    private final static HearsayDAOBeanService hearsayDAOBean = new HearsayDAOBeanServiceImpl();

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
            // List<Identifier> rnaNucleotideAccessionIdentifierList = hearsayDAOBean.getIdentifierDAO()
            // .findByExample(new Identifier("www.ncbi.nlm.nih.gov/nuccore", "NM_130787.2"));
            //
            // List<Identifier> proteinAccessionIdentifierList = hearsayDAOBean.getIdentifierDAO()
            // .findByExample(new Identifier("www.ncbi.nlm.nih.gov/protein", "NP_570603.2"));

            List<Identifier> rnaNucleotideAccessionIdentifierList = hearsayDAOBean.getIdentifierDAO()
                    .findByExample(new Identifier("canvas/refseq/transcript/versionId", "NM_002570.3"));

            List<Identifier> genomeAccessionIdentifierList = hearsayDAOBean.getIdentifierDAO()
                    .findByExample(new Identifier("canvas/ref/genomeRefSeq/verAccession", "NC_000015.9"));

            List<Identifier> proteinAccessionIdentifierList = hearsayDAOBean.getIdentifierDAO()
                    .findByExample(new Identifier("canvas/refseq/cds/proteinId", "NP_002561.1"));

            // List<Long> identifierIdList = new ArrayList<Long>();
            // identifierIdList.add(rnaNucleotideAccessionIdentifierList.get(0).getId());
            // identifierIdList.add(genomeAccessionIdentifierList.get(0).getId());
            // identifierIdList.add(proteinAccessionIdentifierList.get(0).getId());

            List<Identifier> identifierIdList = new ArrayList<Identifier>();
            identifierIdList.add(rnaNucleotideAccessionIdentifierList.get(0));
            identifierIdList.add(genomeAccessionIdentifierList.get(0));
            identifierIdList.add(proteinAccessionIdentifierList.get(0));

            List<ReferenceSequence> potentialRefSeqs = hearsayDAOBean.getReferenceSequenceDAO().findByIdentifiers(identifierIdList);
            assertTrue(CollectionUtils.isNotEmpty(potentialRefSeqs));
            assertTrue(potentialRefSeqs.size() == 1);
            serialize(potentialRefSeqs.get(0));

        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }

    }

    private void serialize(ReferenceSequence referenceSequence) {

        try {
            JAXBContext context = JAXBContext.newInstance(ReferenceSequence.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            FileWriter fw = new FileWriter(new File("/tmp", String.format("referenceSequence-%d.xml", referenceSequence.getId())));
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
