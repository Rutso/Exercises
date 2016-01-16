package school;

public class Teacher extends Person {
	
	private Group[] listOfGroups;
	
	public Teacher(String name, String surName, int numberOfGroups) {
		super(name, surName);
		if (numberOfGroups > 0) {
			this.listOfGroups = new Group[numberOfGroups];
		} else {
			this.listOfGroups = new Group[2];
			System.out.println("Invalid number of groups assigned to the teacher " + name + " " + surName + ". Number set to default value of 2.");
		}
	}

	public void viewTeacher() {
		System.out.println("Name of the teacher: " + this.viewPerson());
		System.out.println("Groups: ");
		for (int i = 0; i < this.listOfGroups.length; i++) {
			if (this.listOfGroups[i] != null) {
				System.out.println("Subject: " + this.listOfGroups[i].getSubject());
				this.listOfGroups[i].viewStudentsInGroup();
			}
		}
	}
	
	
	public void addGroup(Group newGroup) {
		
		for (int i = 0; i < this.listOfGroups.length; i++) {
			if (newGroup.equals(this.listOfGroups[i])) {
				System.out.println(this.viewPerson() + " already has this group in his list of groups.");
				return;
			}
		}
		int counter = 0;
		for (int i = 0; i < this.listOfGroups.length; i++) {
			if (this.listOfGroups[i] == null) {
				this.listOfGroups[i] = newGroup;
				this.listOfGroups[i].setTeacher(this);
				return;
			}
		}
		if (counter == 0) {
			System.out.println(this.viewPerson() + " already has full list of groups.");
		}
		
		
	}
}
