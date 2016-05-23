package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic;

import javafx.beans.Observable;


public interface IssueTracker {

	/**
	 * Validates the XML file identified by <code>path</code> with an XML Schema
	 * for {@link Project}
	 * 
	 * @param path
	 *            the path to the XML file to be validated. Must not be null,
	 *            empty, or a non-existent/unreadable file.
	 * @throws IssueTrackingException
	 *             if the file identified by <code>path</code> is not valid
	 */
	void validate(String path) throws IssueTrackingException;

	/**
	 * Loads an XML file containing a {@link Project} by unmarshalling it into
	 * memory and validating the content
	 * 
	 * @param path
	 *            the path of the XML file to be unmarshalled. Must not be null,
	 *            empty, or a non-existent/unreadable file.
	 * @return a service handle for manipulating the {@link Project}
	 * @throws IssueTrackingException
	 */
	ProjectService load(String path) throws IssueTrackingException;

	/**
	 * Creates a new and empty Project
	 * 
	 * @return a service handle for manipulating the {@link Project}.
	 */
	ProjectService create();

}
