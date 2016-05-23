package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.controller;

import java.io.File;
import java.util.List;
import java.util.Observer;

import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Issue;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.IssueTrackingException;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Severity;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.State;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Type;

public interface IssueTrackerController extends Observer {

	public void createNewProject();

	public void loadNewProject(String path) throws IssueTrackingException;

	public void saveProject(String path) throws IssueTrackingException;

	public void deleteMarkedIssue(Issue issue);

	public void closeMarkedIssue(Issue issue);

	public void openAddNewIssueWindow();

	public void openDescriptionWindow(Issue issue);

	public void newProject();

	public void createIssue(String id, String name, String description,
			Severity severity, Type type, State state, List<String> dependencies);

	public void loadProject(String loadDestination);

}
