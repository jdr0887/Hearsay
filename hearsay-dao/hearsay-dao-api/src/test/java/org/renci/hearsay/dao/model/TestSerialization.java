package org.renci.hearsay.dao.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.junit.Test;

public class TestSerialization {

	@Test
	public void testWorkflowPlanSerialization() {

		// try {
		// JAXBContext context = JAXBContext.newInstance(WorkflowPlan.class);
		// Marshaller m = context.createMarshaller();
		// m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// FileWriter fw = new FileWriter(new File("/tmp/workflowPlan.xml"));
		// m.marshal(workflowPlan, fw);
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// } catch (PropertyException e1) {
		// e1.printStackTrace();
		// } catch (JAXBException e1) {
		// e1.printStackTrace();
		// }

	}

}
