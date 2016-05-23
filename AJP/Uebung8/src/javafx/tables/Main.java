package javafx.tables;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Person gamma = new Person("Erich", "Gamma", "swiss");
		Person fowler = new Person("Martin", "Fowler", "english");
		Person lamport = new Person("Leslie", "Lamport", "american");
		
		Person[] people = { gamma, fowler, lamport };
		
		PeopleView view = new PeopleView(people);
		
		view.show();
	}

}
