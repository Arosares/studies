package de.uniba.wiai.dsg.ajp.assignment2;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.IssueTracker;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.IssueTrackingException;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.ProjectService;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.logic.IssueTrackerImpl;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.logic.ProjectServiceImpl;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.ui.ProjectUi;

public class Main {

	public static void main(String[] args) throws IOException, JAXBException,
			SAXException, IssueTrackingException {
		// TODO start your application from here

		IssueTracker tracki = new IssueTrackerImpl();
		tracki.create();
		ProjectUi Ui = new ProjectUi(tracki);

	}
}
