package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.controller;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Issue;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.IssueTrackingException;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Severity;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.State;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Type;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.model.IssueTrackerModel;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.view.IssueTrackerNewIssueView;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.view.IssueTrackerView;

public class IssueTrackerControllerImpl implements IssueTrackerController {

	// private IssueTracker issueTracker;
	private IssueTrackerModel trackerModel;
	private IssueTrackerView view;

	public IssueTrackerControllerImpl(IssueTrackerModel trackerModel)
			throws IssueTrackingException {
		this.trackerModel = trackerModel;
		view = new IssueTrackerView(trackerModel, this);
		view.show();

		trackerModel.addObserver(this);
		trackerModel.addObserver(view);
	}

	public void openAddNewIssueWindow() {
		IssueTrackerNewIssueView newIssueView = new IssueTrackerNewIssueView(
				trackerModel, this);
		newIssueView.showAndWait();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	public void createNewProject() {
		// TODO Auto-generated method stub
	}

	@Override
	public void loadNewProject(String path) throws IssueTrackingException {
		// TODO Auto-generated method stub
		trackerModel.loadProject(path);
	}

	@Override
	public void saveProject(String path) throws IssueTrackingException {
		// TODO Auto-generated method stub
		trackerModel.saveProject(path);
	}

	@Override
	public void deleteMarkedIssue(Issue issue) {
		// TODO Auto-generated method stub

		if (issue != null) {
			trackerModel.deleteMarkedIssue(issue);
		} else {
			Alert nullAlert = new Alert(AlertType.WARNING, "No Issue Selected");
			nullAlert.showAndWait();
		}
	}

	@Override
	public void closeMarkedIssue(Issue issue) {
		// TODO Auto-generated method stub

		if (issue != null) {
			trackerModel.closeMarkedIssue(issue);
		} else {
			Alert nullAlert = new Alert(AlertType.WARNING, "No Issue Selected");
			nullAlert.showAndWait();
		}
	}

	@Override
	public void openDescriptionWindow(Issue issue) {
		// TODO Auto-generated method stub
		if (issue != null) {
			Alert alert = new Alert(AlertType.NONE, issue.getDescription(),
					new ButtonType("Back"));
			alert.setTitle("Description of Issue " + issue.getId() + " / "
					+ issue.getName());
			alert.showAndWait();
		}
	}

	@Override
	public void createIssue(String id, String name, String description,
			Severity severity, Type type, State state, List<String> dependencies) {

		List<Issue> issueDependencies = new LinkedList<Issue>();
		List<Issue> issues = trackerModel.getIssues();

		if (id.trim() != "" || name.trim() != "" || description.trim() != "") {

			for (String string : dependencies) {
				for (Issue issue : issues) {
					if (issue.toString().contains(string)) {
						issueDependencies.add(issue);
					}
				}
			}

			Issue issue = trackerModel.createIssue(id, name, description,
					severity, type, state, issueDependencies);
			trackerModel.addIssue(issue);

		} else {
			System.out.println("else");
			// newIssueView.createAlert();
		}
	}

	@Override
	public void newProject() {
		trackerModel.newProject();
	}

	@Override
	public void loadProject(String loadDestination) {
		// TODO Auto-generated method stub
		trackerModel.loadProject(loadDestination);
	}
}
