package pattern.strategy;

public class Main {

	public static void main(String[] args) {
		// TODO: Logger erzeugen, Logeintraege schreiben und Appender
		// austauschen
		
		Appender consoleAppender = new ConsoleAppender();
		Appender fileAppender = new FileAppender();
		Logger logger = new Logger("patter.strategy", consoleAppender);
		
		String message = "WARNING";
		
		logger.info("Great Weather Today");
		logger.debug("Debug here");
		logger.error("Found Error!");
		logger.warn(message);
		
		logger.setAppender(fileAppender);
		
		logger.info("Great Weather Today");
		logger.debug("Debug here");
		logger.error("Found Error!");
		logger.warn(message);
	}

}
