package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model;


import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
//@XmlType(propOrder={"issues","milestones","issue","milestone"})
public class Project {

	private List<Issue> issues = new LinkedList<>();

	private List<Milestone> milestones = new LinkedList<>();
	
	private String projectname;

//	private Issue issue;

//	private Milestone milestone;

	//    @XmlElement
//	public Issue getIssue() {
//		return issue;
//	}
	
	@XmlElement(name="issue")
	@XmlIDREF
	public List<Issue> getIssues() {
		return issues;
	}
	
//	@XmlElement
//	public Milestone getMilestone() {
//		return milestone;
//	}
	@XmlElement(name = "milestone")
	@XmlIDREF
	public List<Milestone> getMilestones() {
		return milestones;
	}
	
	@XmlAttribute
	public String getProjectname(){
		return projectname;
	}
//	public void setIssue(Issue issue) {
//		this.issue = issue;
//	}
	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
//	public void setMilestone(Milestone milestone) {
//		this.milestone = milestone;
//	}
	public void setMilestones(List<Milestone> milestones) {
		this.milestones = milestones;
	}
	public void setProjectname(String projectname){
		this.projectname = projectname;
	}

}