package javafx.echo;

import javax.swing.RootPaneContainer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class EchoStage extends Stage {

	private EchoController controller;
	private TextField echoField;
	private Label label;
	
	public EchoStage(EchoController controller) {
		this.setTitle("EchoFrame");
		this.controller = controller;
		Pane rootPane = createContentPane();
		createBinding();
		
		Scene scene = new Scene(rootPane, 500,500);
		this.setScene(scene);

	}

	private Pane createContentPane() {
		
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(createInputField());
		borderPane.setCenter(createEchoButton());
		borderPane.setBottom(createEchoLabel());
		
		return borderPane;
	}

	private TextField createInputField() {
		echoField = new TextField();
		return echoField;
//		TextField inputField = new TextField();
//		inputField.getText();
		
//		return inputField;
	}
	public String getText(){
		return createInputField().getText();
	}

	private Button createEchoButton() {
		Button echoButton = new Button("Echo");
		echoButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				controller.echo(echoField.getText());
//				event.consume();
			}
		});
		
		
		return echoButton;
	}

	private Label createEchoLabel() {
		label = new Label();
		
		return label;
	}
	
	public void setLabelText(String message){
		label.setText(message);
	}
	
	public void clearTextField(){
		echoField.clear();
	}
	
	private void createBinding(){
			
		StringProperty inputProperty = echoField.textProperty();
		StringProperty labelProperty = label.textProperty();
		
		StringConverter<String> converter = new StringConverter<String>(){
			public String fromString(String arg0) {
				return "Text: "+arg0;
			} public String toString(String arg0) { 
				return arg0;
			}
		};
		
		inputProperty.bindBidirectional(labelProperty, converter);
	}
}
