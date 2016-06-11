package aufgabe4;

public class Player implements Comparable<Player> {
	
	private int bodySize;
	private String lastName;
	private String firstName;

	public Player(int bodySize, String lastName, String firstName) {
		this.bodySize = bodySize;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public int getBodySize() {
		return bodySize;
	}

	public void setBodySize(int bodySize) {
		this.bodySize = bodySize;
	}
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Compares two Players with each other. Read the documentation of the 'Comparable' interface for more detail.
	 * 
	 */
	@Override
	public int compareTo(Player comparePlayer) {
		// TODO implement
		if(comparePlayer == null){
			throw new NullPointerException("compare Player is null");
		}
		return Integer.compare(bodySize, comparePlayer.getBodySize());
	}

	@Override
	public String toString() {
		return "(" + bodySize + ") " + lastName;
	}
}