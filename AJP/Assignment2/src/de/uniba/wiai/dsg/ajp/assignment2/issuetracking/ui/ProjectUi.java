package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.ui;

import java.io.IOException;

import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.IssueTracker;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.IssueTrackingException;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.ProjectService;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.logic.ProjectServiceImpl;
import de.uniba.wiai.dsg.ajp.assignment2.issuetracking.view.ConsoleHelper;

public class ProjectUi implements IssueTracker {
	ProjectService projectService = new ProjectServiceImpl();

	ConsoleHelper console = ConsoleHelper.build();
	int number;

	public ProjectUi(IssueTracker tracki) {

		MainMenuSysOut();
		MainMenuIntCheck();

	}

	// Main Menu Text
	public void MainMenuSysOut() {

		System.out.println(" # # # Main Menu # # # ");
		System.out.println("");
		System.out.println("Options:");
		System.out.println("( 1 ) Validate and Load Project");
		System.out.println("( 2 ) Create New Project");
		System.out.println("( 0 ) Exit");
	}

	public void MainMenuIntCheck() {

		try {
			number = console.askInteger("Please Enter a Number...");
			switch (number) {
			case 0:
				System.out.println("Programm terminating.");
				System.out.println("");
				break;
			case 1:
				System.out.println(number + ": Validate and Load Project");
				// It should print name here, had we finished that function
				SubMenuText();
				break;
			case 2:
				System.out.println(number + ": Create New Project");
				System.out.println("Please enter Project name.... ");
				// It should print name here, had we finished that function
				SubMenuText();
				break;
			default:
				System.out.println(number + ": Please Enter a Valid Number!");
				MainMenuSysOut();
				MainMenuIntCheck();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IssueTrackingException e) {
			e.printStackTrace();
		}
	}

	public void SubMenuText() throws IOException, IssueTrackingException {
		System.out.println("( 1 ) Add Milestone");
		System.out.println("( 2 ) Remove Milestone and Cleanup");
		System.out.println("( 3 ) List Milestones");
		System.out.println("( 4 ) Add Issue");
		System.out.println("( 5 ) Close Issue");
		System.out.println("( 6 ) Remove Issue and Cleanup");
		System.out.println("( 7 ) List Issues");
		System.out.println("( 8 ) Print XML on Console");
		System.out.println("( 9 ) Save XML to File");
		System.out.println("( 0 ) Back to main menu / close without saving");
		SubMenuIntCheck();
	}

	public void SubMenuIntCheck() {
		try {
			number = console.askInteger("Please Enter a Number...");

			switch(number) {
				case 0:
					System.out.println("Back to main menu");
					MainMenuSysOut();
					MainMenuIntCheck();
					break;
				case 1:
					System.out.println(number+": Add Milestone");
					SubMenuText();
					break;
				case 2:
					System.out.println(number+": Remove Milestone and Cleanup");
					System.out.println("Not done yet!");
					System.out.println("Enter ID:");
					number = console.askInteger("Enter Milestone ID Number...");
					String mId = "m"+number;
					projectService.removeMilestone(mId);
					// I'm aware asking for a string and offering a fail option would be better. 
					// We also missed cleaning up the dependencies
					break;
				case 3:
					System.out.println(number+": List Milestones");
					projectService.getMilestones();
					// The actual function is faulty, however
					break;
				case 4:
					System.out.println(number+": Add Issue");
					System.out.println("Not done yet!");
//					projectService.createIssue(id, name, description, severity, type, milestone, dependencies);
					// That was way too much for the limited time we had left
					break;
				case 5:
					System.out.println(number+" Close Issue");
					System.out.println("Not done yet!");
				case 6:
					System.out.println(number+": Remove Issue and Cleanup");
					System.out.println("Not done yet!");
//					projectService.removeIssue(id);
					break;
				case 7:
					System.out.println(number+": List Issues");
					System.out.println("Not done yet!");
					projectService.getIssues();
					break;
				case 8:
					System.out.println(number+": Print XML on Console");
					projectService.printXMLToConsole();
					break;
				case 9:
					System.out.println(number+": Save XML to File");
					System.out.println("Not done yet!");
//					projectService.saveXMLToFile(path);
					break;
				default:
					System.out.println(number+": Please Enter a Valid Number!");
					SubMenuText();

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IssueTrackingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void validate(String path) throws IssueTrackingException {

	}

	@Override
	public ProjectService load(String path) throws IssueTrackingException {
		return null;
	}

	@Override
	public ProjectService create() {
		return null;
	}

}
