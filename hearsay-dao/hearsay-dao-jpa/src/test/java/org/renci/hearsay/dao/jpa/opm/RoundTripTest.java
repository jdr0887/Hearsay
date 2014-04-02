package org.renci.hearsay.dao.jpa.opm;

import java.io.StringWriter;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.opm.Activity;
import org.renci.hearsay.dao.model.opm.Document;
import org.renci.hearsay.dao.model.opm.InternationalizedString;
import org.renci.hearsay.dao.model.opm.ObjectFactory;
import org.renci.hearsay.dao.model.opm.QualifiedName;
import org.renci.hearsay.dao.model.opm.Type;
import org.renci.hearsay.dao.model.opm.Value;

public class RoundTripTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    @BeforeClass
    public static void setup() {
        emf = Persistence.createEntityManagerFactory("test-hearsay", null);
        em = emf.createEntityManager();
    }

    @Test
    public void testRoundTrip() {

        ValueDAOImpl valueDAO = new ValueDAOImpl();
        valueDAO.setEntityManager(em);

        TypeDAOImpl typeDAO = new TypeDAOImpl();
        typeDAO.setEntityManager(em);

        InternationalizedStringDAOImpl internationalizedStringDAO = new InternationalizedStringDAOImpl();
        internationalizedStringDAO.setEntityManager(em);

        QualifiedNameDAOImpl qualifiedNameDAO = new QualifiedNameDAOImpl();
        qualifiedNameDAO.setEntityManager(em);

        DocumentDAOImpl documentDAO = new DocumentDAOImpl();
        documentDAO.setEntityManager(em);

        ActivityDAOImpl activityDAO = new ActivityDAOImpl();
        activityDAO.setEntityManager(em);

        QualifiedName exampleQN = new QualifiedName("http://example.org/", "a1", "ex");
        QualifiedName stringQN = new QualifiedName("http://www.w3.org/2001/XMLSchema", "string", "xsd");
        QualifiedName intQN = new QualifiedName("http://www.w3.org/2001/XMLSchema", "int", "xsd");

        try {
            em.getTransaction().begin();
            Long pk = qualifiedNameDAO.save(exampleQN);
            em.getTransaction().commit();
            exampleQN.setPrimaryKey(pk);

            em.getTransaction().begin();
            pk = qualifiedNameDAO.save(stringQN);
            em.getTransaction().commit();
            stringQN.setPrimaryKey(pk);

            em.getTransaction().begin();
            pk = qualifiedNameDAO.save(intQN);
            em.getTransaction().commit();
            intQN.setPrimaryKey(pk);

        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }

        ObjectFactory factory = new ObjectFactory();
        Document document = factory.createDocument();
        Activity activity = factory.createActivity();

        activity.setId(exampleQN);
        activity.setStartTime(new Date());
        activity.setEndTime(new Date());

        try {
            InternationalizedString is = new InternationalizedString("asdfads");
            em.getTransaction().begin();
            Long internationalizedStringPK = internationalizedStringDAO.save(is);
            em.getTransaction().commit();
            is.setPrimaryKey(internationalizedStringPK);
            activity.getLabel().add(is);
        } catch (HearsayDAOException e2) {
            e2.printStackTrace();
        }

        try {
            InternationalizedString is = new InternationalizedString("qwerqwr");
            em.getTransaction().begin();
            Long internationalizedStringPK = internationalizedStringDAO.save(is);
            em.getTransaction().commit();
            is.setPrimaryKey(internationalizedStringPK);
            activity.getLabel().add(is);
        } catch (HearsayDAOException e2) {
            e2.printStackTrace();
        }

        try {
            InternationalizedString is = new InternationalizedString("bye");
            is.setLang("en");
            em.getTransaction().begin();
            Long internationalizedStringPK = internationalizedStringDAO.save(is);
            em.getTransaction().commit();
            is.setPrimaryKey(internationalizedStringPK);
            activity.getLabel().add(is);
        } catch (HearsayDAOException e2) {
            e2.printStackTrace();
        }

        try {
            InternationalizedString is = new InternationalizedString("bonjour");
            is.setLang("fr");
            em.getTransaction().begin();
            Long internationalizedStringPK = internationalizedStringDAO.save(is);
            em.getTransaction().commit();
            is.setPrimaryKey(internationalizedStringPK);
            activity.getLabel().add(is);
        } catch (HearsayDAOException e2) {
            e2.printStackTrace();
        }

        try {
            Type t = new Type();
            t.setValue("asdf");
            t.setType(stringQN);
            em.getTransaction().begin();
            Long pk = typeDAO.save(t);
            em.getTransaction().commit();
            t.setPrimaryKey(pk);
            activity.getType().add(t);
        } catch (HearsayDAOException e2) {
            e2.printStackTrace();
        }

        // try {
        // Type t = new Type();
        // t.setValue("1");
        // t.setType(intQN);
        // em.getTransaction().begin();
        // Long pk = typeDAO.save(t);
        // em.getTransaction().commit();
        // t.setPrimaryKey(pk);
        // activity.getType().add(t);
        // } catch (HearsayDAOException e2) {
        // e2.printStackTrace();
        // }

        try {
            em.getTransaction().begin();
            Long pk = activityDAO.save(activity);
            em.getTransaction().commit();
            activity.setPrimaryKey(pk);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }

        document.getEntityOrActivityOrWasGeneratedBy().add(activity);

        Long documentPrimaryKey = null;

        try {
            em.getTransaction().begin();
            documentPrimaryKey = documentDAO.save(document);
            em.getTransaction().commit();
            document.setPrimaryKey(documentPrimaryKey);
        } catch (HearsayDAOException e) {
            e.printStackTrace();
        }

        try {
            Document foundDocument = documentDAO.findById(documentPrimaryKey);
            JAXBContext context = JAXBContext.newInstance(Document.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            m.marshal(foundDocument, sw);
            System.out.println(sw.toString());
        } catch (PropertyException e1) {
            e1.printStackTrace();
        } catch (JAXBException e1) {
            e1.printStackTrace();
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
