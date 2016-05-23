package de.uniba.wiai.dsg.ajp.assignment1.validation.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

		Path rootFolder = Paths.get(task.getRootFolder());
		List<String> directoryList = new LinkedList<String>();
		BufferedReader reader;
		ValidationResult warningList = new ValidationResult();
		List<String> stringWarningList = new LinkedList<String>(); //List for saving warnings as strings

		try {
			Files.walk(rootFolder).forEach(Path -> {
				String stringPath = Path.toString();
//				Goes through Path and all subfolders and finds all files
					if (stringPath.endsWith(".java")) {
						directoryList.add(stringPath);
//						adding found java files to directoryList
					}
				});

//			Iterate through found .java files
			for (String stringEntry : directoryList) {
				File file = new File(stringEntry);


				FileReader fileReader = new FileReader(file.getAbsolutePath());
				fileReader.read();
				fileReader.close();


				int lineCounter=1;
				reader = Files.newBufferedReader(Paths.get(stringEntry), StandardCharsets.UTF_8);
//				Read found files to check for errors:
				try {
					String line = reader.readLine();
//				Try	"File cannot be read" Problems
					while(line!=null){

						int lineLength = line.length();
						if (lineLength > 120){
//							Checks for lines with over 120 characters
							Warning warningLength = new Warning (stringEntry, lineCounter, WarningType.TOO_MANY_CHARACTERS);
							warningList.addWarning(warningLength);
							stringWarningList.add("Error: "+WarningType.TOO_MANY_CHARACTERS+" in File: "+stringEntry+" at line: "+lineCounter);
							System.out.println("Error: "+WarningType.TOO_MANY_CHARACTERS+" in File: "+stringEntry+" at line: "+lineCounter);
						}
						if (line.endsWith(" ")){
//							Checks for lines with trailing whitespaces
							Warning warningSpaceEnd = new Warning (stringEntry, lineCounter, WarningType.TRAILING_WHITESPACES);
							warningList.addWarning(warningSpaceEnd);
							stringWarningList.add("Error: "+WarningType.TRAILING_WHITESPACES+" in File: "+stringEntry+" at line: "+lineCounter);
							System.out.println("Error: "+WarningType.TRAILING_WHITESPACES+" in File: "+stringEntry+" at line: "+lineCounter);
						}
						if (line.startsWith(" ") && !line.startsWith(" *")){
//							Checks for lines starting with whitespaces, excluding multi-line comments
							Warning warningSpaceStart = new Warning (stringEntry, lineCounter, WarningType.FAULTY_INDENTATION);
							warningList.addWarning(warningSpaceStart);
							stringWarningList.add("Error: "+WarningType.FAULTY_INDENTATION+" in File: "+stringEntry+" at line: "+lineCounter);
							System.out.println("Error: "+WarningType.FAULTY_INDENTATION+" in File: "+stringEntry+" at line: "+lineCounter);
						}
						line = reader.readLine();
						lineCounter++;
					}
				} catch (IOException e) {
//					Catch "File cannot be read" Problems
//					We have been told to expect all files to be encoded in UTF-8. The following shouldn't happen:
					System.err.println("Exception in "+stringEntry+": A File could not be read: "+e.getMessage());
//					If a file can not be read, please check encoding and OS access rights. The run can still be successful.
				}
			}
			Files.write(Paths.get(task.getResultFile()), stringWarningList, StandardCharsets.UTF_8);
//			Catch Failures while checking file path or iterating through checked Files and trying to write the result file
		} catch (IOException e) {
			throw new CodeValidationException(e);
		}
				
		return warningList;
	}
}