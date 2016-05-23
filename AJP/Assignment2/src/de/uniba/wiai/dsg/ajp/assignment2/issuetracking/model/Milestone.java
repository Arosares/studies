package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

public class Milestone {
	
	private String id;

	private String name;

	private List<Issue> issues = new LinkedList<>();

	public Milestone(){

	}

	public Milestone(String id, String name){
		this.id = id;
		this.name = name;
	}



	@XmlID
	@XmlAttribute
	public String getId() {
		return id;
	}

	@XmlIDREF
	@XmlAttribute
	public List<Issue> getIssues() {
		return issues;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String issueNames = "";
		for (Issue issue : issues) {
			issueNames += issue.getName() + " ";
		}
		return "Milestone [id=" + id + ", name=" + name + ", issues=( "
		+ issueNames + ")]";
	}
}
