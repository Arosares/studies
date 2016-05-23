package de.uniba.wiai.dsg.ajp.assignment1.validation.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import de.uniba.wiai.dsg.ajp.assignment1.validation.CodeValidationException;
import de.uniba.wiai.dsg.ajp.assignment1.validation.CodeValidator;
import de.uniba.wiai.dsg.ajp.assignment1.validation.ValidationResult;
import de.uniba.wiai.dsg.ajp.assignment1.validation.ValidationTask;
import de.uniba.wiai.dsg.ajp.assignment1.validation.Warning;
import de.uniba.wiai.dsg.ajp.assignment1.validation.WarningType;

/**
 * TODO: implement this
 */
public class SimpleCodeValidator implements CodeValidator {

	public SimpleCodeValidator() {
		/*
		 * DO NOT REMOVE
		 *
		 * REQUIRED FOR GRADING
		 */
	}

	@Override
	public ValidationResult validate(final ValidationTask task)
			throws CodeValidationException {

		Path startFolder = Paths.get(task.getRootFolder());
		Path rootFolder = startFolder;
		boolean isDirectory;
		List<String> directoryList = new LinkedList<String>();
		List<String> fileList = new LinkedList<String>();	//List for saving .java files, not sure if necessary
		BufferedReader reader;
		BufferedWriter resultFile;
		ValidationResult warningList = new ValidationResult();
		Path pathElem2=null;

		try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(startFolder)) {
			do {

				for (Path pathElem : dirStream) {

					//					while (pathElem2 != null){		//is never true in first walkthrough
					//						pathElem = pathElem2;
					//					}
					String stringPath = pathElem.toString();//type Path changed into String (not sure if necessary if we use List<Path> directoryList)

					if (directoryList.contains(stringPath)) {
						continue;								//skips folder if already in directoryList
					}
					if (Files.isDirectory(pathElem)) {
						System.out.println("Directory: "+pathElem);
						directoryList.add(stringPath);			//adds folder to directoryList
						System.out.println("added folder to directoryList");
						startFolder = pathElem;					//opens folder

						System.out.println("open folder");
					}
					if (!Files.isDirectory(pathElem)) {			//if pathElem is file
						System.out.println(stringPath+ "found file");


						if (!fileList.contains(stringPath)){
							System.out.println("File not in List");
							fileList.add(stringPath);
							if(stringPath.contains(".java")){
								System.out.println("File confirmed as .java");
								int lineCounter=1;
								reader = Files.newBufferedReader(pathElem, StandardCharsets.UTF_8);
								System.out.println("Aktuelle Zeile "+lineCounter);
								String line = reader.readLine();
								while(line!=null){

									int lineLength = line.length();
									if (lineLength > 120){
										Warning warningLength = new Warning (stringPath, lineCounter, WarningType.TOO_MANY_CHARACTERS);
										System.out.println(WarningType.TOO_MANY_CHARACTERS);
										warningList.addWarning(warningLength);
										System.out.println(lineCounter+ "fehler 1");
									}
									if (line.endsWith(" ")){
										Warning warningSpaceEnd = new Warning (stringPath, lineCounter, WarningType.TRAILING_WHITESPACES);
										System.out.println(WarningType.TRAILING_WHITESPACES);
										warningList.addWarning(warningSpaceEnd);
										System.out.println(lineCounter+ "fehler 2");
									}
									if (line.startsWith(" ") && !line.startsWith(" *")){
										Warning warningSpaceStart = new Warning (stringPath, lineCounter, WarningType.FAULTY_INDENTATION);
										System.out.println(WarningType.FAULTY_INDENTATION);
										warningList.addWarning(warningSpaceStart);
										System.out.println(lineCounter+ "fehler 3");
									}
									line = reader.readLine();
									lineCounter++;
									System.out.println(lineCounter);
								}
								System.out.println("while-Schleife Ende");

							}
						}

					}
					//pathElem2 = pathElem;
				}
				//				if(pathElem2 != rootFolder){
				//					pathElem2 = pathElem2.getParent();
				//				}
			} while (pathElem2!=rootFolder);
			//TODO: WarningList in ResultFile speichern
			resultFile = Files.newBufferedWriter(pathElem2, StandardCharsets.UTF_8);
			for (Warning warning : warningList.getWarnings()){
				System.out.println(warning);
			}

		} catch (IOException e) {

			e.printStackTrace();

		}

		return null;
	}

}

// wenn for - Schleife jetzt durchgelaufen ist, befindet man sich im untersten sub-Ordner im dort letzten File.
// was noch fehlt ist ein weg, wieder in den "Parent-Folder" zu kommen, um dann die for-Schleife wieder weiter laufen zu lassen.