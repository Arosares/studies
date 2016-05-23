package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.view;

import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.controller.IssueTrackerController;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.Issue;
import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.model.IssueTrackerModel;

public class IssueTrackerTable {

	private IssueTrackerModel trackerModel;
	private IssueTrackerController controller;
	private TableView<Issue> table;
	private ObservableList<Issue> tableData;

	public void setTableData(List<Issue> tableData) {
		this.tableData.clear();
		this.tableData.setAll(tableData);
	}

	public IssueTrackerTable(IssueTrackerModel trackerModel,
			IssueTrackerController controller) {
		this.trackerModel = trackerModel;
		this.controller = controller;
	}

	private Node createTable() {
		this.tableData = FXCollections.observableArrayList(trackerModel
				.getIssues());

		table = new TableView<>();

		TableColumn<Issue, String> id = new TableColumn<>("ID");
		TableColumn<Issue, String> name = new TableColumn<>("Name");
		TableColumn<Issue, String> type = new TableColumn<>("Type");
		TableColumn<Issue, String> dependencies = new TableColumn<>(
				"Dependencies");
		TableColumn<Issue, String> severity = new TableColumn<>("Severity");
		TableColumn<Issue, String> state = new TableColumn<>("Status");

		//set Column Width
		
		id.prefWidthProperty().bind(table.widthProperty().divide(8));
		name.prefWidthProperty().bind(table.widthProperty().divide(3));
		type.prefWidthProperty().bind(table.widthProperty().divide(8));
		dependencies.prefWidthProperty().bind(table.widthProperty().divide(6));
		severity.prefWidthProperty().bind(table.widthProperty().divide(8));
		state.prefWidthProperty().bind(table.widthProperty().divide(8));
		
		table.getColumns().add(id);
		table.getColumns().add(name);
		table.getColumns().add(type);
		table.getColumns().add(dependencies);
		table.getColumns().add(severity);
		table.getColumns().add(state);

		PropertyValueFactory<Issue, String> firstColFactory = new PropertyValueFactory<>(
				"id");
		PropertyValueFactory<Issue, String> secondColFactory = new PropertyValueFactory<>(
				"name");
		PropertyValueFactory<Issue, String> thirdColFactory = new PropertyValueFactory<>(
				"type");
		PropertyValueFactory<Issue, String> fithColFactory = new PropertyValueFactory<>(
				"severity");
		PropertyValueFactory<Issue, String> sixthColFactory = new PropertyValueFactory<>(
				"state");

		id.setCellValueFactory(firstColFactory);
		name.setCellValueFactory(secondColFactory);
		type.setCellValueFactory(thirdColFactory);
		dependencies
				.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Issue, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(
							CellDataFeatures<Issue, String> p) {
						// TODO Auto-generated method stub
						Issue issue = p.getValue();
						List<Issue> list = new LinkedList<Issue>();
						list = issue.getDependencies();
						List<String> stringList = new LinkedList<String>();
						for (Issue issue2 : list) {
							stringList.add(issue2.getId());
						}

						return new SimpleStringProperty(stringList.toString()
								.replaceAll("[\\[\\](){}]", ""));
					}
				});

		severity.setCellValueFactory(fithColFactory);
		state.setCellValueFactory(sixthColFactory);

		table.setItems(tableData);

		table.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					Issue issue = table.getSelectionModel().getSelectedItem();
					controller.openDescriptionWindow(issue);
				}
			}
		});

		// table.autosize();
		return table;
	}

	Node getMainTable() {
		return createTable();
	}

	public TableView<Issue> getTable() {
		return table;
	}
}
