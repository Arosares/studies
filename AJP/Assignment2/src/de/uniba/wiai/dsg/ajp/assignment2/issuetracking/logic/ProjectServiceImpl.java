package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.IssueTrackingException;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.ProjectService;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Issue;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Milestone;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Project;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Severity;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model.Type;

public class ProjectServiceImpl implements ProjectService {

	@Override
	public void createMilestone(String id, String name, Set<String> issues)
			throws IssueTrackingException {
		Milestone milestone = new Milestone(id, name);
		List<Issue> issueList = new LinkedList<>();
		for (String issue : issues) {
			setMilestone(issue);
		}
		milestone.setIssues(issueList);
	}
	

	private void setMilestone(String issue) {
		
	}


	@Override
	public List<Milestone> getMilestones() { // not sure if intended/works that
												// way
		// TODO Auto-generated method stub

		return getMilestones();
	}

	@Override
	public void removeMilestone(String id) throws IssueTrackingException {
		// TODO Auto-generated method stub

	}

	@Override
	public void createIssue(String id, String name, String description,
			Severity severity, Type type, String milestone,
			Set<String> dependencies) throws IssueTrackingException {
		Issue issue = new Issue();
		issue.setId(id);
		issue.setName(name);
		issue.setDescription(description);
		issue.setSeverity(severity);
		issue.setType(type);
		// issue.setMilestone(milestone); setMilestone() not availible for
		// Strings

	}

	@Override
	public List<Issue> getIssues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeIssue(String id) throws IssueTrackingException {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeIssue(String id) throws IssueTrackingException {
		// TODO Auto-generated method stub

	}

	@Override
	public void printXMLToConsole() throws IssueTrackingException {
		try {
			BufferedReader reader = Files.newBufferedReader(
					Paths.get("one.xml"), StandardCharsets.UTF_8);
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void saveXMLToFile(String path) throws IssueTrackingException {
		// TODO Auto-generated method stub

	}

}
