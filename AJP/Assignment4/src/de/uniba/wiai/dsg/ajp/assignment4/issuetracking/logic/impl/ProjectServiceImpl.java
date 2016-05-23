package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Issue;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Project;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.State;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Type;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.IssueTrackingException;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.ProjectService;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Severity;

class ProjectServiceImpl implements ProjectService {
	
	private final Project project;
	private final ProjectXmlBinding projectXMLBinding;

	ProjectServiceImpl(ProjectXmlBinding projectXMLBinding, Project project) {
		this.projectXMLBinding = projectXMLBinding;
		this.project = project;
	}

	@Override
	public void createIssue(String id, String name, String description,
			Severity severity, Type type, Set<String> issueIds)
			throws IssueTrackingException {
		ValidationHelper.assertID(id);
		if (hasIssueWithId(id)) {
			throw new IssueTrackingException("ID is already taken.");
		}
		ValidationHelper.assertNotEmpty(name);
		ValidationHelper.assertNotNull(description);
		ValidationHelper.assertNotNull(severity);
		ValidationHelper.assertNotNull(type);

		ValidationHelper.assertNotNull(issueIds);
		for (String issueId : issueIds) {
			ValidationHelper.assertNotEmpty(issueId);
		}

		// convert issues to dependent issues
		List<Issue> dependencies = new LinkedList<>();
		for (String issueId : issueIds) {
			dependencies.add(getIssueById(issueId));
		}

		Issue issue = new Issue(id, name, description, severity, type,
				State.OPEN, dependencies);

		project.getIssues().add(issue);
	}

	@Override
	public void removeIssue(String id) throws IssueTrackingException {
		ValidationHelper.assertNotEmpty(id);
		Issue issue = getIssueById(id);

		// remove issue
		project.getIssues().remove(issue);

		// cleanup dependencies
		for (Issue dependentIssue : project.getIssues()) {
			dependentIssue.getDependencies().remove(issue);
		}
	}

	@Override
	public void closeIssue(String id) throws IssueTrackingException {
		ValidationHelper.assertNotEmpty(id);

		Issue issue = getIssueById(id);

		// check for open dependencies
		for (Issue dependendIssue : issue.getDependencies()) {
			if (State.OPEN.equals(dependendIssue.getState())) {
				throw new IssueTrackingException("Issue <" + id
						+ ">, which depends on open issue <"
						+ dependendIssue.getId() + ">, can not be removed.");
			}
		}

		// close issue
		issue.setState(State.CLOSED);
	}

	private Issue getIssueById(String id) throws IssueTrackingException {
		for (Issue issue : project.getIssues()) {
			if (issue.getId().equals(id)) {
				return issue;
			}
		}

		throw new IssueTrackingException("Issue with ID <" + id
				+ "> does not exist.");
	}

	private boolean hasIssueWithId(String id) {
		try {
			getIssueById(id);
			return true;
		} catch (IssueTrackingException success) {
			return false;
		}
	}

	@Override
	public void printXMLToConsole() throws IssueTrackingException {
		projectXMLBinding.print(project);
	}

	@Override
	public void saveXMLToFile(String path) throws IssueTrackingException {
		ValidationHelper.assertNotEmpty(path);

		projectXMLBinding.save(project, path);
	}

	@Override
	public List<Issue> getIssues() {
		return project.getIssues();
	}

}
