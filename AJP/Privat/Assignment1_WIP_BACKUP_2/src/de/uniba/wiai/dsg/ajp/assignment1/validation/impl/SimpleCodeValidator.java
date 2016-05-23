package de.uniba.wiai.dsg.ajp.assignment1.validation.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
		//Path rootFolder = startFolder;
		//boolean isDirectory;
		List<String> directoryList = new LinkedList<String>();
		List<String> fileList = new LinkedList<String>();	//List for saving .java files, not sure if necessary
		BufferedReader reader;
		ValidationResult warningList = new ValidationResult();
		List<String> stringWarningList = new LinkedList<String>(); //List for saving warnings as strings, makes writing file easier
		int endIndex=0;
		String[] stringArray = new String[10];

		try {
			Files.walk(startFolder).forEach(Path -> {
				String stringPath = Path.toString();
				System.out.println(stringPath);
				directoryList.add(stringPath);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<String> strings = new ArrayList<>();
		strings.stream().forEach((string) -> {
			System.out.println("Content: " + string);
		});

		//		try (Stream<Path> pathStream = Files.walk(startFolder)) {
		//
		//			String list;
		//			pathStream.forEach(System.out::println);
		//			//			String stringPath = pathStream.toString();
		//			//			System.out.println(stringPath);
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//		}

		try (DirectoryStream<Path> fileStream = Files.newDirectoryStream(startFolder)) {


			for (Path pathElem : fileStream) {
				String stringPath = pathElem.toString();//type Path changed into String (not sure if necessary if we use List<Path> directoryList)

				if (directoryList.contains(stringPath)) {
					System.out.println("Skipping entity :"+stringPath);
					continue;								//skips folder if already in directoryList
				}
				if (Files.isDirectory(pathElem)) {
					System.out.println("Ignoring Directory: "+pathElem);
					continue;
				}
				if (!Files.isDirectory(pathElem)) {			//if pathElem is file
					System.out.println("found file " + stringPath);


					if(stringPath.contains(".java")){
						System.out.println("File confirmed as .java");
						if (!fileList.contains(stringPath)){
							System.out.println("File not in List");

							//Check File readability (WIP)

							File file = new File(stringPath);
							try {
								FileReader fileReader = new FileReader(file.getAbsolutePath());
								System.out.println("Absolute File Path: "+file);
								fileReader.read();
								fileReader.close();
							} catch (Exception e) {
								System.out.print("Exception when checked file can read with message:"+e.getMessage());
							}


							fileList.add(stringPath);
							int lineCounter=1;
							reader = Files.newBufferedReader(pathElem, StandardCharsets.UTF_8);
							System.out.println("Aktuelle Zeile "+lineCounter); //Ausgabe der aktuellen Zeile f�r Debug-Zwecke
							String line = reader.readLine();
							while(line!=null){

								int lineLength = line.length();
								if (lineLength > 120){
									Warning warningLength = new Warning (stringPath, lineCounter, WarningType.TOO_MANY_CHARACTERS);
									//System.out.println(WarningType.TOO_MANY_CHARACTERS);
									warningList.addWarning(warningLength);
									stringWarningList.add("Error: "+WarningType.TOO_MANY_CHARACTERS+" in File: "+stringPath+" at line: "+lineCounter);
									System.out.println("Error: "+WarningType.TOO_MANY_CHARACTERS+" in File: "+stringPath+" at line: "+lineCounter);
								}
								if (line.endsWith(" ")){
									Warning warningSpaceEnd = new Warning (stringPath, lineCounter, WarningType.TRAILING_WHITESPACES);
									//System.out.println(WarningType.TRAILING_WHITESPACES);
									warningList.addWarning(warningSpaceEnd);
									stringWarningList.add("Error: "+WarningType.TRAILING_WHITESPACES+" in File: "+stringPath+" at line: "+lineCounter);
									System.out.println("Error: "+WarningType.TRAILING_WHITESPACES+" in File: "+stringPath+" at line: "+lineCounter);
								}
								if (line.startsWith(" ") && !line.startsWith(" *")){
									Warning warningSpaceStart = new Warning (stringPath, lineCounter, WarningType.FAULTY_INDENTATION);
									//System.out.println(WarningType.FAULTY_INDENTATION);
									warningList.addWarning(warningSpaceStart);
									stringWarningList.add("Error: "+WarningType.FAULTY_INDENTATION+" in File: "+stringPath+" at line: "+lineCounter);
									System.out.println("Error: "+WarningType.FAULTY_INDENTATION+" in File: "+stringPath+" at line: "+lineCounter);
								}
								line = reader.readLine();
								lineCounter++;
								System.out.println(lineCounter);
							}
							System.out.println("while-Schleife Ende");

						}
					}

				}
			}

		} catch (IOException e) {

			e.printStackTrace();

		}
		//Directories suchen


		try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(startFolder)) {
			for (Path pathElem : dirStream) {
				String stringPath = pathElem.toString();//type Path changed into String (not sure if necessary if we use List<Path> directoryList)

				if (directoryList.contains(stringPath)) {
					System.out.println("Skipping entity :"+stringPath);
					continue;								//skips folder if already in directoryList
				}
				if (Files.isDirectory(pathElem)) {
					System.out.println("Starting Search in subdirectory: "+pathElem);
					//HIER MUSS REKURSIVER AUFRUF VON SIMPLECODEVALIDATOR HIN!

					endIndex++;
					startFolder = startFolder.subpath(0, endIndex);


				}
			}
		} catch (IOException e) {

			e.printStackTrace();

		}


		//TODO: WarningList in ResultFile speichern
		try {
			//			resultFile = Files.newBufferedWriter(pathElem2, StandardCharsets.UTF_8);
			//			for (Warning warning : warningList.getWarnings()){
			//				System.out.println(warning);
			Files.write(Paths.get(task.getResultFile()), stringWarningList, StandardCharsets.UTF_8);
			//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//			do {
		//
		//				for (Path pathElem : dirStream) {
		//
		//					//					while (pathElem2 != null){		//is never true in first walkthrough
		//					//						pathElem = pathElem2;
		//					//					}
		//					String stringPath = pathElem.toString();//type Path changed into String (not sure if necessary if we use List<Path> directoryList)
		//
		//					if (directoryList.contains(stringPath)) {
		//						continue;								//skips folder if already in directoryList
		//					}
		//					if (Files.isDirectory(pathElem)) {
		//						System.out.println("Directory: "+pathElem);
		//						directoryList.add(stringPath);			//adds folder to directoryList
		//						System.out.println("added folder to directoryList");
		//						startFolder = pathElem;					//opens folder
		//
		//						System.out.println("open folder");
		//					}
		//					if (!Files.isDirectory(pathElem)) {			//if pathElem is file
		//						System.out.println(stringPath+ "found file");
		//
		//
		//						if (!fileList.contains(stringPath)){
		//							System.out.println("File not in List");
		//							fileList.add(stringPath);
		//							if(stringPath.contains(".java")){
		//								System.out.println("File confirmed as .java");
		//								int lineCounter=1;
		//								reader = Files.newBufferedReader(pathElem, StandardCharsets.UTF_8);
		//								System.out.println("Aktuelle Zeile "+lineCounter);
		//								String line = reader.readLine();
		//								while(line!=null){
		//
		//									int lineLength = line.length();
		//									if (lineLength > 120){
		//										Warning warningLength = new Warning (stringPath, lineCounter, WarningType.TOO_MANY_CHARACTERS);
		//										System.out.println(WarningType.TOO_MANY_CHARACTERS);
		//										warningList.addWarning(warningLength);
		//										System.out.println(lineCounter+ "fehler 1");
		//									}
		//									if (line.endsWith(" ")){
		//										Warning warningSpaceEnd = new Warning (stringPath, lineCounter, WarningType.TRAILING_WHITESPACES);
		//										System.out.println(WarningType.TRAILING_WHITESPACES);
		//										warningList.addWarning(warningSpaceEnd);
		//										System.out.println(lineCounter+ "fehler 2");
		//									}
		//									if (line.startsWith(" ") && !line.startsWith(" *")){
		//										Warning warningSpaceStart = new Warning (stringPath, lineCounter, WarningType.FAULTY_INDENTATION);
		//										System.out.println(WarningType.FAULTY_INDENTATION);
		//										warningList.addWarning(warningSpaceStart);
		//										System.out.println(lineCounter+ "fehler 3");
		//									}
		//									line = reader.readLine();
		//									lineCounter++;
		//									System.out.println(lineCounter);
		//								}
		//								System.out.println("while-Schleife Ende");
		//
		//							}
		//						}
		//
		//					}
		//					//pathElem2 = pathElem;
		//				}
		//				//				if(pathElem2 != rootFolder){
		//				//					pathElem2 = pathElem2.getParent();
		//				//				}
		//			} while (pathElem2!=rootFolder);
		//			//TODO: WarningList in ResultFile speichern
		//			resultFile = Files.newBufferedWriter(pathElem2, StandardCharsets.UTF_8);




		return warningList;
	}



}

// wenn for - Schleife jetzt durchgelaufen ist, befindet man sich im untersten sub-Ordner im dort letzten File.
// was noch fehlt ist ein weg, wieder in den "Parent-Folder" zu kommen, um dann die for-Schleife wieder weiter laufen zu lassen.