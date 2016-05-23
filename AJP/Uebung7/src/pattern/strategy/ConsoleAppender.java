package pattern.strategy;

public class ConsoleAppender implements Appender {

	@Override
	public void append(String message) {
		System.out.println(message);
	}

}
