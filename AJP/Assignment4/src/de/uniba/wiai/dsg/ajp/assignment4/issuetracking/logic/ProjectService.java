package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic;

import java.util.List;
import java.util.Set;

public interface ProjectService {

	/**
	 * Creates a new {@link Issue}. New {@link Issue}s are in <code>OPEN</code>
	 * {@link State}.
	 * 
	 * @param id
	 *            the ID of the new {@link Issue}. Must not be <code>null</code>
	 *            or empty. Must be a valid ID and unique within the current
	 *            project.
	 * @param name
	 *            the name of the new {@link Issue} is a headline to the
	 *            description. Must not be <code>null</code> or empty.
	 * @param description
	 *            a description of the new {@link Issue} shows the
	 *            problem/feature in such Detail, that it can be implemented by
	 *            those who read it. Must not be <code>null</code>, but may be
	 *            empty.
	 * @param severity
	 *            the severity of the new {@link Issue}.
	 * @param type
	 *            the {@link Issue} tells whether to fix a problem or to
	 *            implement something new.
	 * @param dependencies
	 *            optional relationship that indicates the {@link Issue}s that
	 *            need to be closed, so the new {@link Issue} can be closed as
	 *            well. The dependent {@link Issue}s are represented by their
	 *            IDs. Must not be <code>null</code> and the IDs of all
	 *            {@link Issue}s need to exist and must not be <code>null</code>
	 *            or empty. The list may be empty.
	 * @throws IssueTrackingException
	 *             if the parameters are invalid.
	 */
	void createIssue(String id, String name, String description,
			Severity severity, Type type, Set<String> dependencies)
			throws IssueTrackingException;

	/**
	 * Fetches an array of the {@link Issue}s attached to the Project.
	 * 
	 * @return the {@link Issue}s of the current Project.
	 */
	List<Issue> getIssues();

	/**
	 * Deletes an {@link Issue} by ID. Also deletes all references to this
	 * {@link Issue} in other {@link Issue}s.
	 * 
	 * @param id
	 *            the ID of the {@link Issue} to be deleted. Must not be null or
	 *            empty. Must be a valid ID.
	 * @throws IssueTrackingException
	 *             {@link Issue} with given ID does not exist in project or one
	 *             of the dependent {@link Issue}s caused a problem during the
	 *             deletion of the references.
	 */
	void removeIssue(String id) throws IssueTrackingException;

	/**
	 * Closes an {@link Issue} by id. An {@link Issue} that depends on other
	 * open {@link Issue}s cannot be closed.
	 * 
	 * @param id
	 *            the ID of the {@link Issue} to be closed. Must not be null or
	 *            empty. Must be a valid ID.
	 * @throws IssueTrackingException
	 *             {@link Issue} with given ID does not exist in project or one
	 *             of the dependent {@link Issue}s is not closed.
	 */
	void closeIssue(String id) throws IssueTrackingException;

	/**
	 * Prints the current project to the console by marshaling it to XML.
	 * 
	 * @throws IssueTrackingException
	 *             if there are errors while marshaling the current project.
	 */
	void printXMLToConsole() throws IssueTrackingException;

	/**
	 * Saves the current project to the given file by marshaling it to XML.
	 * Existing files are overridden.
	 * 
	 * @param path
	 *            the path of the file. Must not be <code>null</code> or empty.
	 *            Must be a valid path in the file system and be writable.
	 * @throws IssueTrackingException
	 *             if path is <code>null</code> or empty or there are errors
	 *             during marshaling the current project.
	 */
	void saveXMLToFile(String path) throws IssueTrackingException;
}
