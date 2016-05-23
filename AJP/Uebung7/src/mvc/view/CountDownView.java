package mvc.view;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import mvc.controller.CountDownController;
import mvc.model.CountDownModel;

/**
 * Implements a simple view for a {@code CountDownModel} which it observes.
 * Provides a text field and a button. Only integer values are valid input to
 * the text field.
 * 
 * @author Simon Harrer, Joerg Lenhard, Michael Traeger, Gabriel Nikol
 */
public class CountDownView extends Stage implements Observer {

	private static final Font NORMAL_FONT = Font.font("Monospaced",
			FontWeight.NORMAL, 12);
	private static final Font BIG_FONT = Font.font("BigRedFont",
			FontWeight.BOLD, 24);

	/**
	 * UI nodes
	 */
	private Label countDownValueLabel;
	private TextField countDownTextField;
	private Button startButton;
	private Pane layout;

	/**
	 * other members of MVC
	 */
	private final CountDownController controller;
	private final CountDownModel model;

	/**
	 * Constructs a new {@code CountDownView}, registers it at the
	 * {@code CountDownModel} which is passed in and sets the
	 * {@code CountDownController}. The view will start up and become visible
	 * immediately.
	 * 
	 * @param model
	 *            The {@code CountDownModel} this {@code CountDownView} should
	 *            observe
	 * @param controller
	 *            The {@code CountDownController} that implements the current
	 *            strategy for this {@code CountDownView}
	 */
	public CountDownView(CountDownModel model, CountDownController controller) {
		this.controller = controller;
		this.model = model;

		initiateNodes();
		registerOnModel();
	}

	/**
	 * creates and layouts the view nodes
	 */
	private void initiateNodes() {
		createNodes();
		addEventHandlers();
		layoutNodes();
		configureWindow();
	}

	/**
	 * configures the overall scene for this window
	 */
	private void configureWindow() {
		Scene scene = new Scene(layout, 240, 80);
		this.setScene(scene);
	}

	/**
	 * registers this view at the given model
	 */
	private void registerOnModel() {
		model.addObserver(this);
	}

	/**
	 * creates the UI nodes
	 */
	private void createNodes() {
		countDownValueLabel = new Label();
		countDownTextField = new TextField();
		String button = "COUNTDOWN\n START";
		startButton = new Button(button);
	}

	/**
	 * adds necessary EventHandlers (one Handler for the button is required)
	 */
	private void addEventHandlers() {
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				controller.startCountDown(getTextFieldValue());
			}
		});
	}

	/**
	 * puts the view nodes into a layout
	 */
	private void layoutNodes() {
		// add components to a JPanel
		GridPane panel = new GridPane();

		panel.add(countDownTextField, 0, 0);
		panel.add(countDownValueLabel, 1, 0);
		panel.add(startButton, 0, 1);

		layout = panel;
	}

	/**
	 * reads the current value of the views text field.
	 * 
	 * @return the current value of the text field if it is a number, otherwise
	 *         0
	 */
	private int getTextFieldValue() {
		try {
			return Integer.parseInt(countDownTextField.getText());
		} catch (NumberFormatException e) {
			countDownTextField.setText("0");
			return getTextFieldValue();
		}
	}

	/**
	 * Inherited from {@code java.util.Observer}. Is called by
	 * {@code Observables} at which this {@code CountDownView} has registered.
	 * Updates the display of this {@code CountDownView} to the state of its
	 * {@code CountDownModel}.
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		// run notification for the GUI on the JavaFX thread
		// calling into JavaFX from other threads will cause exceptions
		Platform.runLater(() -> {
			// Observer pattern is implemented in the pull fashion
			String newCounterValue = String.valueOf(this.model.getSeconds());
			if (!model.isExecuting()) {
				countDownValueLabel.setText("");
				countDownValueLabel.setFont(NORMAL_FONT);

				countDownTextField.setText(newCounterValue);
			} else {
				countDownValueLabel.setText(newCounterValue);
				countDownValueLabel.setFont(BIG_FONT);
			}
		});
	}

	/**
	 * Resets the display of this {@code CountDownView} to the current state of
	 * its {@code CountDownModel}. If the {@code CountDownModel} is executing,
	 * its current second value is displayed.
	 */
	public void reset() {
		this.update(model, null);
	}

	/**
	 * Disables all input nodes (text field and button) and makes them
	 * invisible.
	 */
	public void hideInputNodes() {
		startButton.setDisable(true);
		startButton.setVisible(false);
		countDownTextField.setEditable(false);
		countDownTextField.setVisible(false);
	}

	/**
	 * Enables all input nodes (text field and button) and makes them visible.
	 */
	public void showInputNodes() {
		startButton.setDisable(false);
		startButton.setVisible(true);
		countDownTextField.setEditable(true);
		countDownTextField.setVisible(true);
	}

	/**
	 * Displays an info message to the user
	 * 
	 * @param message
	 *            the message to display
	 */
	public void showMessageToUser(String message) {
		new Alert(AlertType.INFORMATION, message).showAndWait();
	}

}
