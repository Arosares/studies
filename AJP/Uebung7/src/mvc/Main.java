package mvc;

import mvc.controller.CountDownController;
import mvc.controller.CountDownControllerImpl;
import mvc.model.CountDownModel;
import mvc.view.CountDownView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		/*
		 * TODO: First, create the model! Second, create the controller and let
		 * the controller do all the work. Both steps may require the creation
		 * of additional objects.
		 */
		CountDownModel model = new CountDownModel();
		CountDownController controller = new CountDownControllerImpl(model);
//		CountDownView view = new CountDownView(model, controller);
//		
//		controller.startCountDown(0);
//		view.showInputNodes();
	}
}
