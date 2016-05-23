package javafx.echo;

public class EchoController {

	private EchoStage stage;
	
	public void setView(EchoStage stage){
		this.stage = stage;
	}
	
	
	public void echo(String echoMessage) {
//		EchoStage stage = new EchoStage();
		stage.setLabelText(echoMessage);;
		stage.clearTextField();
	}

}
