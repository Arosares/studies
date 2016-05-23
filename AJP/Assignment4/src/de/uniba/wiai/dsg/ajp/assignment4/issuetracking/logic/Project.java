package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Project {

	private List<Issue> issues = new LinkedList<>();

	public Project() {
		super();
	}

	@XmlElement(name = "issue")
	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

}
