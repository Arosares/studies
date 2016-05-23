package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic;

public class IssueTrackingException extends Exception {

	private static final long serialVersionUID = -1327535201943041697L;

	public IssueTrackingException(String message, Throwable cause) {
		super(message, cause);
	}

	public IssueTrackingException(String message) {
		super(message);
	}

	public IssueTrackingException() {
		super();
	}

	public IssueTrackingException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public IssueTrackingException(Throwable arg0) {
		super(arg0);
	}
}
