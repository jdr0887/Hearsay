package org.renci.hearsay.dao.model.opm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;

public class TestSerialization {

    @Test
    public void testFileRoundTrip() {

        File documentFile = new File("/tmp/document.xml");

        try {
            ObjectFactory factory = new ObjectFactory();
            Document document = factory.createDocument();
            Activity activity = factory.createActivity();
            QualifiedName qualifiedName = new QualifiedName();
            qualifiedName.setLocalPart("a1");
            qualifiedName.setNamespaceURI("http://example.org/");
            qualifiedName.setPrefix("ex");
            activity.setId(qualifiedName);
            document.getEntityOrActivityOrWasGeneratedBy().add(activity);
            document.getEntityOrActivityOrWasGeneratedBy().add(activity);
            JAXBContext context = JAXBContext.newInstance(Document.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            FileWriter fw = new FileWriter(documentFile);
            m.marshal(document, fw);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (PropertyException e1) {
            e1.printStackTrace();
        } catch (JAXBException e1) {
            e1.printStackTrace();
        }

        try {
            JAXBContext context = JAXBContext.newInstance(Document.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileInputStream fis = new FileInputStream(new File("/tmp/document.xml"));
            Source source = new StreamSource(fis);
            JAXBElement<Document> root = unmarshaller.unmarshal(source, Document.class);
            Document document = root.getValue();
            Set<Element> elements = document.getEntityOrActivityOrWasGeneratedBy();
            for (Element element : elements) {
                if (element instanceof Activity) {
                    Activity activity = (Activity) element;
                    System.out.println(activity.toString());
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (PropertyException e1) {
            e1.printStackTrace();
        } catch (JAXBException e1) {
            e1.printStackTrace();
        }

    }

}
