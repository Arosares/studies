package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.model;

import java.util.List;
import java.util.Observable;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Issue;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.IssueTracker;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.IssueTrackingException;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Project;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.ProjectService;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Severity;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.State;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Type;

public class IssueTrackerModel extends Observable {

	private Project project;
	private List<Issue> issues;
	private Issue issue;
	private ProjectService projectService;
	private IssueTracker issueTracker;
	private String path;

	public IssueTrackerModel(IssueTracker issueTracker) {
		// TODO Auto-generated constructor stub
		this.issueTracker = issueTracker;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	/**
	 * adds Issue to the IssueList
	 * 
	 * @param issue
	 */
	public void addIssue(Issue issue) {
		issues = getIssues();
		issues.add(issue);

		setChanged();
		notifyObservers();
	}

	public Issue createIssue(String id, String name, String description,
			Severity severity, Type type, State state, List<Issue> dependencies) {
		return issue = new Issue(id, name, description, severity, type, state,
				dependencies);
	}

	public void loadProject(String path) {
		try {
			projectService = issueTracker.load(path);

			this.path = path;

			System.out.println(this.path);

			issues = projectService.getIssues();

			setChanged();
			notifyObservers();
		} catch (IssueTrackingException e) {
			// TODO Auto-generated catch block
			Alert validateAlert = new Alert(AlertType.ERROR,
					"The chosen File can not be processed.");
			validateAlert.setHeaderText("Invalid XML");
			validateAlert.setTitle("Validation Error");
			validateAlert.showAndWait();
			// Stacktrace:
			e.printStackTrace();
		}
	}

	public Project loadProject() {

		return project;
	}

	public void saveProject(String path) throws IssueTrackingException {

		projectService.saveXMLToFile(path);
	}

	public ProjectService create() {

		project = new Project();
		projectService = issueTracker.create();
		issues = projectService.getIssues();

		return projectService;
	}

	public Project getProject() {

		return project;
	}

	public String getDesciption() {
		String description = "";
		for (Issue issue : issues) {
			description += issue.getDescription();
		}

		return description;
	}

	public void newProject() {
		// TODO Auto-generated method stub
		issues = getIssues();
		issues.clear();
		setChanged();
		notifyObservers();

	}

	public void closeMarkedIssue(Issue issue) {
		// TODO Auto-generated method stub
		issue.setState(State.CLOSED);

		setChanged();
		notifyObservers();
	}

	public void deleteMarkedIssue(Issue issueToDelete) {
		// TODO Auto-generated method stub
		issues = getIssues();
		issues.remove(issueToDelete);

		setChanged();
		notifyObservers();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
