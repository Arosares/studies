package javafx.tables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PeopleView extends Stage {

	public PeopleView(Person[] people) {
		Pane rootPane = createContentPane(people);
		Scene scene = new Scene(rootPane, 300, 300);
		this.setScene(scene);
		this.setTitle("PeopleStage");
	}

	private Pane createContentPane(Person[] people) {
		GridPane pane = new GridPane();
		pane.add(createTable(people), 0, 1);
		pane.add(createList(people), 1, 1);
		return pane;
	}

	private Node createTable(Person[] people) {
		// TODO: implement me
		ObservableList<Person> tableData = FXCollections.observableArrayList(people);
		TableView<Person> table = new TableView<>();
		
		TableColumn<Person, String> prename = new TableColumn<>("Prename");
		TableColumn<Person, String> surname = new TableColumn<>("Surname");
		TableColumn<Person, String> nationality = new TableColumn<>("Nationality");
		
		table.getColumns().add(prename);
		table.getColumns().add(surname);
		table.getColumns().add(nationality);
		
		PropertyValueFactory<Person, String> firstColFactory = new PropertyValueFactory<>("prename");
		PropertyValueFactory<Person, String> secondColFactory = new PropertyValueFactory<>("surname");
		PropertyValueFactory<Person, String> thirdColFactory = new PropertyValueFactory<>("nationality");

		prename.setCellValueFactory(firstColFactory);
		surname.setCellValueFactory(secondColFactory);
		nationality.setCellValueFactory(thirdColFactory);
		
		
		table.setItems(tableData);
		
		ScrollPane listScrollPane = new ScrollPane(table);
		
		return table;
	}

	private Node createList(Person[] people) {
		// TODO: implement me
		ObservableList<Person> items = FXCollections.observableArrayList(people);
		ListView<Person> list = new ListView<>(items);
		ScrollPane listScrollPane = new ScrollPane(list);
		return list;
	}

}
