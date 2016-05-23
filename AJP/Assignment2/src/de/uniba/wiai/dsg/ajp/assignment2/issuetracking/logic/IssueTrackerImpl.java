package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.logic;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.IssueTracker;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.IssueTrackingException;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.ProjectService;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Project;

public class IssueTrackerImpl implements IssueTracker {

	public IssueTrackerImpl() {
		/*
		 * DO NOT CHANGE - required for grading!
		 */
	}

	@Override
	public ProjectService create() {
		ProjectService project = new ProjectServiceImpl();
		return project;
	}

	@Override
	public ProjectService load(String path) throws IssueTrackingException {

		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Project.class);
			Unmarshaller um = context.createUnmarshaller();
			create();

			Project one = (Project) um.unmarshal(new File(path));
			return create();
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void validate(String path) throws IssueTrackingException { // validates
																		// schema
																		// with
																		// *.xml
																		// located
																		// in
																		// Path

		try {
			JAXBContext context;
			context = JAXBContext.newInstance(Project.class);
			SchemaFactory sf = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(new File("bin/schema1.xsd"));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(path)));

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
