package pattern.strategy;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

	private final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat();

	private Appender appender;
	
	private String component;

	public Logger(String component, Appender appender) {
		super();
		this.appender = appender;
		this.component = component;
	}

	public void setAppender(Appender appender) {
		this.appender = appender;
	}

	public void info(String message) {
		log("["+component+"]: INFO", message);
	}

	public void debug(String message) {
		log("["+component+"]: DEBUG", message);
	}

	public void warn(String message) {
		log("["+component+"]: WARN", message);
	}

	public void error(String message) {
		log("["+component+"]: ERROR", message);
	}

	private void log(String level, String message) {
		try {
			appender.append(level + " - " + getCurrentTimestamp() + " - " + message);
		} catch (IOException e) {
			throw new IllegalStateException("Can't write on file");
		}
	}

	private String getCurrentTimestamp() {
		return SIMPLE_DATE_FORMAT.format(new Date());
	}

}
