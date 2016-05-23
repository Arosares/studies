package javafx.echo;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		EchoController controller = new EchoController();
		EchoStage frame = new EchoStage(controller);
		controller.setView(frame);
		frame.show();
	}
}
