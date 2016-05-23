package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.impl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Pattern;

import de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.IssueTrackingException;


public class ValidationHelper {
	private static final String ZERO_OR_MORE = "*";

	/**
	 * Validates an ID.
	 * 
	 * An ID has to start with a letter followed by zero or more letters or
	 * numbers.
	 * 
	 * @param id
	 *            the id to be checked. must not be null.
	 * @return true if the id is valid, false otherwise
	 * 
	 * @throws NullPointerException
	 *             if id is null
	 */
	public static boolean isId(String id) {
		id = Objects.requireNonNull(id, "passed id is null");

		String startWithLetter = "[a-zA-Z]";
		String anyLetterOrNumber = "[a-zA-Z0-9]" + ZERO_OR_MORE;
		String regexForId = startWithLetter + anyLetterOrNumber;
		return Pattern.matches(regexForId, id);
	}

	static void assertID(String id) throws IssueTrackingException {
		assertNotEmpty(id);

		if (!ValidationHelper.isId(id)) {
			throw new IssueTrackingException("<" + id + "> is not a valid ID.");
		}
	}

	static void assertNotEmpty(String string) throws IssueTrackingException {
		assertNotNull(string);

		if (string.isEmpty()) {
			throw new IssueTrackingException("Must not be empty.");
		}
	}

	static void assertExistentFile(String path) throws IssueTrackingException {
		assertNotEmpty(path);

		if (!Files.exists(Paths.get(path))) {
			throw new IssueTrackingException("File does not exist at path <"
					+ path + ">");
		}
	}

	static void assertNotNull(Object object) throws IssueTrackingException {
		if (object == null) {
			throw new IssueTrackingException("Must not be null.");
		}
	}

}
