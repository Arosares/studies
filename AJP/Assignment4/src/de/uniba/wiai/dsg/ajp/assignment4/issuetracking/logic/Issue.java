package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

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

	private List<Issue> dependencies = new LinkedList<>();

	public Issue() {
		super();
	}

	public Issue(String id, String name, String description, Severity severity,
			Type type, State state, List<Issue> dependencies) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.severity = severity;
		this.type = type;
		this.state = state;
		this.dependencies = dependencies;
	}

	@XmlID
	@XmlElement(required = true)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(required = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlAttribute(required = true)
	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	@XmlAttribute(required = true)
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@XmlAttribute(required = true)
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@XmlIDREF
	@XmlElement(name = "dependencies")
	public List<Issue> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<Issue> dependencies) {
		this.dependencies = dependencies;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ");
		for (Issue dependentIssue : dependencies) {
			joiner.add(dependentIssue.getName());
		}

		return "Issue [id=" + id + ", name=" + name + ", description="
				+ description + ", severity=" + severity + ", type=" + type
				+ ", state=" + state + ", dependencies=( " + joiner.toString() + ")]";
	}
}
