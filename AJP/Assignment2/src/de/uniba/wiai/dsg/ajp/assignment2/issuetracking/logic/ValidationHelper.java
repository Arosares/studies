package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.logic;

import java.util.Objects;
import java.util.regex.Pattern;

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
		Objects.requireNonNull(id, "input must not be null");

		String startWithLetter = "[a-zA-Z]";
		String anyLetterOrNumber = "[a-zA-Z0-9]" + ZERO_OR_MORE;
		String regexForId = startWithLetter + anyLetterOrNumber;
		return Pattern.matches(regexForId, id);
	}

}
