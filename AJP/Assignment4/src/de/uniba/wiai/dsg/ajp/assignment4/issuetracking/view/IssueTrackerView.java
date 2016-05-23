package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.view;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.controller.IssueTrackerController;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Issue;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.model.IssueTrackerModel;

public class IssueTrackerView extends Stage implements Observer {

	private IssueTrackerModel trackerModel;
	private IssueTrackerController controller;
	private IssueTrackerTable mainTable;
	private Stage stage;
	private BorderPane pane;
	private IntegerProperty amountIssueProperty;
	private StringProperty projectNameProperty;
	private String projectName;
	
	
	public IssueTrackerView(IssueTrackerModel trackerModel,
			IssueTrackerController controller) {
		this.trackerModel = trackerModel;
		this.controller = controller;
		this.stage = this;
		
		trackerModel.create();

		Pane rootPane = createContentPane();
		stage.initModality(Modality.APPLICATION_MODAL);
		Scene scene = new Scene(rootPane, 800, 400);

		amountIssueProperty = new SimpleIntegerProperty();
		projectNameProperty = new SimpleStringProperty("New Project");
	
		titleProperty().bind(
				Bindings.concat(projectNameProperty)
						.concat(" - ")
						.concat(amountIssueProperty)
						.concat(" Issues"));
		
		stage.setScene(scene);
	}
    
//	private String createTitle(){
//		Path path = Paths.get(projectName);
//		
//		return projectName;
//	}
	
	private Pane createContentPane() {
		// TODO Auto-generated method stub
		BorderPane pane = new BorderPane();
		this.pane = pane;

		Pane downPane = createBottomButtons();

		IssueTrackerMenu topMenuBar = new IssueTrackerMenu(controller);
		MenuBar menuBar = topMenuBar.getTopMenuBar();
		
		pane.setTop(menuBar);

		mainTable = new IssueTrackerTable(trackerModel, controller);
		Node table = mainTable.getMainTable();
		pane.setCenter(table);
		
		pane.setBottom(downPane);

		return pane;
	}

	private Pane createBottomButtons() {
		HBox hboxLeft = new HBox();
		BorderPane downBorder = new BorderPane();

		hboxLeft.setSpacing(10);

		Button buttonDeleteIssue = new Button("Delete selected Issue");
		buttonDeleteIssue.setPrefSize(150, 20);

		Button buttonCloseIssue = new Button("Close selected Issue");
		buttonCloseIssue.setPrefSize(150, 20);

		Button buttonAddIssue = new Button("Add new Issue");
		buttonAddIssue.setPrefSize(150, 20);
		
		buttonDeleteIssue.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				TableView<Issue> table = mainTable.getTable();
				Issue issue = table.getSelectionModel().getSelectedItem();
				controller.deleteMarkedIssue(issue);
			}
		});
		
		buttonCloseIssue.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				TableView<Issue> table = mainTable.getTable();
				Issue issue = table.getSelectionModel().getSelectedItem();
				controller.closeMarkedIssue(issue);
			}
		});

		buttonAddIssue.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				controller.openAddNewIssueWindow();
			}
		});

		hboxLeft.getChildren().addAll(buttonDeleteIssue, buttonCloseIssue);

		downBorder.setLeft(hboxLeft);
		downBorder.setRight(buttonAddIssue);
		downBorder.setStyle("-fx-background-color: #336699;");
		downBorder.setPadding(new Insets(30, 12, 15, 12));

		return downBorder;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (o instanceof IssueTrackerModel) {
			IssueTrackerModel model = (IssueTrackerModel) o;
			IssueTrackerTable table = new IssueTrackerTable(trackerModel, controller);
			mainTable = table;
			Node node = table.getMainTable();
			table.setTableData(model.getIssues());
			
			pane.setCenter(node);
			
			projectNameProperty.set(trackerModel.getPath());
			amountIssueProperty.set(trackerModel.getIssues().size());
			

		}


	}

	

}
