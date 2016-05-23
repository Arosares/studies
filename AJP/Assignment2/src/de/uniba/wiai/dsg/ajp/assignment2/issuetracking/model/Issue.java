package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

public class Issue {

	private String id;

	private String name;

	private String description;

	private Severity severity;

	private Type type;

	private State state;

	private Milestone milestone;

	private List<Issue> dependencies = new LinkedList<>();
	
	@XmlIDREF
	@XmlElement
	public List<Issue> getDependencies() {
		return dependencies;
	}
	

	@XmlElement
	public String getDescription() {
		return description;
	}

	@XmlID
	@XmlAttribute(required=true)
	public String getId() {
		return id;
	}

	@XmlElement
	public Milestone getMilestone() {
		return milestone;
	}

	@XmlID
	@XmlElement(required=true)
	public String getName() {
		return name;
	}

	@XmlAttribute(required=true)
	public Severity getSeverity() {
		return severity;
	}

	@XmlAttribute(required=true)
	public State getState() {
		return state;
	}

	@XmlAttribute(required=true)
	public Type getType() {
		return type;
	}

	public void setDependencies(List<Issue> dependencies) {
		this.dependencies = dependencies;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMilestone(Milestone milestone) {
		this.milestone = milestone;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		String issueNames = "";
		for (Issue dependentIssue : dependencies) {
			issueNames += dependentIssue.getName() + " ";
		}
		String milestone = "";
		if (this.milestone != null) {
			milestone = this.milestone.getName();
		}

		return "Issue [id=" + id + ", name=" + name + ", description="
		+ description + ", severity=" + severity + ", type=" + type
		+ ", state=" + state + ", milestone=" + milestone
		+ ", dependsUponIssues=( " + issueNames + ")]";
	}

}