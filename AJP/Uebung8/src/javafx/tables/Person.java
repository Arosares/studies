package javafx.tables;

public class Person {

	private String prename;
	private String surname;
	private String nationality;

	public Person(String prename, String surname, String nationality) {
		super();
		this.prename = prename;
		this.surname = surname;
		this.nationality = nationality;
	}

	public String getPrename() {
		return prename;
	}

	public void setPrename(String prename) {
		this.prename = prename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Override
	public String toString() {
		return prename + " " + surname + ": " + nationality;
	}

}
