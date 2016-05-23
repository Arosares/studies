package pattern.strategy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class FileAppender implements Appender {

	private List<String> messageList = new LinkedList<String>();
	private Path path;
	
	@Override
	public void append(String message) throws IOException {
		
		
		messageList.add(message);
	
		String resultFile = "log.txt";
		path = Paths.get(resultFile);
		
		Files.write(path, messageList, StandardCharsets.UTF_8);
	}

}
