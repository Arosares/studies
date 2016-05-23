package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.impl;

import java.io.IOException;

import javafx.beans.InvalidationListener;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.IssueTracker;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.IssueTrackingException;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Project;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.ProjectService;

public class IssueTrackerImpl implements IssueTracker {

    private final ProjectXmlBinding projectXMLBinding;
	private final XsdValidator xsdValidator;

	public IssueTrackerImpl() throws IssueTrackingException {
        try {
			this.projectXMLBinding = new ProjectXmlBinding();
			
			String xsdPath = "schema1.xsd";
			ValidationHelper.assertExistentFile(xsdPath);
			this.xsdValidator = new XsdValidator(xsdPath);
		} catch (JAXBException | SAXException e) {
			throw new IssueTrackingException("could not initialize issue tracker", e);
		}
    }

    @Override
    public void validate(String path) throws IssueTrackingException {
    	ValidationHelper.assertExistentFile(path);
    	
    	try {
			xsdValidator.validate(path);
		} catch (SAXException | IOException e) {
			throw new IssueTrackingException("XML file at " + path
					+ " could not be validated", e);
		}
    }

    @Override
    public ProjectService load(String path) throws IssueTrackingException {
        validate(path);

        Project project = projectXMLBinding.load(path);
        return new ProjectServiceImpl(projectXMLBinding, project);
    }

    @Override
    public ProjectService create() {
        return new ProjectServiceImpl(projectXMLBinding, new Project());
    }



}
