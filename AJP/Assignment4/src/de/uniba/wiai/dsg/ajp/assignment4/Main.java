package de.uniba.wiai.dsg.ajp.assignment4;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;

import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.controller.IssueTrackerController;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.controller.IssueTrackerControllerImpl;
//import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.gui.model.MainModel;
//import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.gui.view.MainFrame;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.IssueTracker;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.impl.IssueTrackerImpl;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.model.IssueTrackerModel;

public class Main extends Application {

	public static void main(String[] args) throws IOException, JAXBException {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		IssueTracker issueTracker = new IssueTrackerImpl();

		IssueTrackerModel trackerModel = new IssueTrackerModel(issueTracker);
		//
		IssueTrackerController controller = new IssueTrackerControllerImpl(
				trackerModel);

		// TODO bootstrap your app
	}

}
