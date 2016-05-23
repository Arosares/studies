package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.view;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.controller.IssueTrackerController;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Issue;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Severity;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.State;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Type;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.model.IssueTrackerModel;

public class IssueTrackerNewIssueView extends Stage {
	private Stage stage;
	private IssueTrackerModel trackerModel;
	private IssueTrackerController controller;
	public IssueTrackerNewIssueView(IssueTrackerModel trackerModel, IssueTrackerController controller){
		this.trackerModel = trackerModel;
		this.controller = controller;

//		stage.initModality(Modality.WINDOW_MODAL);
//		stage.initOwner(this.stage);
		Pane newIssuePane = createIssuePane();
		Scene scene = new Scene(newIssuePane, 450, 450);
		
		this.setScene(scene);
		this.setTitle("Add new Issue");
		this.stage = this;
		
	}
	
	private Pane createIssuePane() {
		// TODO Auto-generated method stub
		BorderPane pane = new BorderPane();
		
		
		
		GridPane gridPane = new GridPane();
		pane.setCenter(gridPane);
		
		//Set Column Width		
		ColumnConstraints column0 = new ColumnConstraints();
	    column0.setPercentWidth(2);
	    gridPane.getColumnConstraints().add(column0);		
		gridPane.getColumnConstraints().add(new ColumnConstraints(100));
	    ColumnConstraints column2 = new ColumnConstraints();
	    column2.setPercentWidth(75);
	    gridPane.getColumnConstraints().add(column2);
	    

//		IssueTrackerTable mainTable = new IssueTrackerTable();
//		Node table = mainTable.getMainTable(issues);
		//Added Texts
		Label idText = new Label("ID:");
		gridPane.add(idText, 1, 0);
		gridPane.getRowConstraints().add(new RowConstraints(35));
		
		Label nameText = new Label("Name:");
		gridPane.add(nameText, 1, 1);
		gridPane.getRowConstraints().add(new RowConstraints(35));
		
		//TODO: FIX IT!
		Label descText = new Label("Description:\n\n\n\n\n\n");
		gridPane.add(descText, 1, 2);
		descText.setAlignment(Pos.TOP_CENTER);
		gridPane.getRowConstraints().add(new RowConstraints(100));
		
		Label severityText = new Label("Severity:");
		gridPane.add(severityText, 1, 3);
		gridPane.getRowConstraints().add(new RowConstraints(35));
		
		Label typeText = new Label("Type:");
		gridPane.add(typeText, 1, 4);
		gridPane.getRowConstraints().add(new RowConstraints(35));
		
		Label dependencyText = new Label("Dependencies:");
		gridPane.add(dependencyText, 1, 5);
		gridPane.getRowConstraints().add(new RowConstraints(100));
		
		//Input Fields
				TextField idField = new TextField();
				gridPane.add(idField, 2, 0);
				TextField nameField = new TextField();
				gridPane.add(nameField, 2, 1);
				TextArea descField = new TextArea("Enter description here");
				descField.setPrefHeight(95);
				gridPane.add(descField, 2, 2);
				
				ComboBox<Severity> dropDownSeverity = new ComboBox<>(); //Import of Enums from logic (!)
				dropDownSeverity.getItems().setAll(Severity.values());
				dropDownSeverity.setMaxWidth(200);
				gridPane.add(dropDownSeverity, 2, 3);
				
				ComboBox<Type> dropDownType = new ComboBox<>(); //Import of Enums from logic (!)
				dropDownType.getItems().setAll(Type.values());
				dropDownType.setMaxWidth(200);
				gridPane.add(dropDownType, 2, 4);
				
				
				
		HBox hbox = new HBox();
		
		Button back = new Button("Back");
		back.setPrefSize(80, 20);

		Button buttonAddIssue = new Button("Add Issue");
		buttonAddIssue.setPrefSize(100, 20);
		
		
		
		ListView<String> depList = new ListView<String>();
		depList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		ListView<String> selected = new ListView<>();
		List<Issue> issues = trackerModel.getIssues();
		
		ObservableList<String> items = FXCollections.observableArrayList();
		
		for (Issue issue : issues) {
			items.add(issue.getId());
		}
		depList.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv)->{
            selected.setItems(depList.getSelectionModel().getSelectedItems());
        });

		depList.setItems(items);
		depList.setMaxWidth(150);
		gridPane.add(depList, 2, 5);
		
		buttonAddIssue.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String id = idField.getText();
				String name = nameField.getText();
				String description = descField.getText();
				Severity severity = dropDownSeverity.getSelectionModel().getSelectedItem();
				Type type = dropDownType.getSelectionModel().getSelectedItem();
				List<String> dependencies = depList.getSelectionModel().getSelectedItems();
				State state = State.OPEN;
				
				controller.createIssue(id, name, description, severity, type, state, dependencies);
				stage.close();
				
			}
		});
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				stage.close();
			}
		});
		
		hbox.getChildren().addAll(back, buttonAddIssue);
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		hbox.setSpacing(10);
		hbox.setPadding(new Insets(30, 12, 15, 12));
		
		
		
		pane.setBottom(hbox);
		
		//Input Fields
//		TextField idField = new TextField();
//		gridPane.add(idField, 2, 0);
//		TextField nameField = new TextField();
//		gridPane.add(nameField, 2, 1);
//		TextArea descField = new TextArea("Enter description here");
//		descField.setPrefHeight(95);
//		gridPane.add(descField, 2, 2);
		
		
		
	
		
		
		return pane;
	}

	public void createAlert() {
		// TODO Auto-generated method stub
		
	}
}
