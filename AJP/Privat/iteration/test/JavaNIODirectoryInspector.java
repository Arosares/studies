package nio.iteration;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JavaNIODirectoryInspector implements DirectoryInspector{

	@Override
	public void inspect(String path) throws IOException {
		// TODO implementiere hier
		Path checkPath = Paths.get(path);
		DirectoryStream<Path> dirStream = Files.newDirectoryStream(checkPath); 
		for (Path pathElem : dirStream) { 
			if(Files.isDirectory(pathElem)){
				System.out.println(pathElem.toString()+" is a directory");
			}else if(Files.isRegularFile(pathElem)){
				System.out.println(pathElem.toString()+" is a fileS");
			}
//			entry.getFileName(); 
			} 
		dirStream.close();
	}

}
