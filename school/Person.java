package school;

public abstract class Person {

	
	private String name;
	private String surName;
	
	
	public Person(String name, String surName) {
		this.name = name;
		this.surName = surName;
	}
	
	public String getName() {
		return this.name;
	}
	public String getSurName() {
		return this.surName;
	}
	
	public String viewPerson() {
		return (this.name + " " + this.surName);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			Person otherPerson = (Person) obj;
			if (this.getName().equalsIgnoreCase(otherPerson.getName()) && this.getSurName().equalsIgnoreCase(otherPerson.getSurName())) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	
}
