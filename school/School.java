package school;

public class School {

	private String name;
	private Group[] listOfGroupsInSchool;
	private Teacher[] listOfTeachersInSchool;
	private Student[] listOfStudentsInSchool;
	
	
	public School(String name) {
		this.name = name;
		this.listOfGroupsInSchool = new Group[3];
		this.listOfTeachersInSchool = new Teacher[3];
		this.listOfStudentsInSchool = new Student[30];
	}
	
	public void addTeacher(Person newTeacher) {
		if (!(newTeacher instanceof Teacher)) {
			System.out.println(newTeacher.viewPerson() + " is not a teacher.");
			return;
		} else {
			for (int i = 0; i < this.listOfTeachersInSchool.length; i++) {
				if (this.listOfTeachersInSchool[i] != null && this.listOfTeachersInSchool[i].equals(newTeacher)) {
					System.out.println(newTeacher.viewPerson() + " is already listed as a teacher in " + this.name + ".");
					return;
				}
			}
			int counter = 0;
			for (int i = 0; i < this.listOfTeachersInSchool.length; i++) {
				if (this.listOfTeachersInSchool[i] == null) {
					this.listOfTeachersInSchool[i] = (Teacher) newTeacher;
					return;
				}
			}
			if (counter == 0) {
				System.out.println("Can't enroll " + newTeacher.viewPerson() + " as a teacher in " + this.name + " because the list of teachers is already full.");
			}
			
		}
	}
	
	public void addGroup(Group newGroup) {
		for (int i = 0; i < this.listOfGroupsInSchool.length; i++) {
			if (newGroup.equals(this.listOfGroupsInSchool[i])) {
				System.out.println("There is already a group with subject " + newGroup.getSubject() + " in " + this.name + ".");
				return;
			}
		}
		int counter = 0;
		for (int i = 0; i < this.listOfGroupsInSchool.length; i++) {
			if (this.listOfGroupsInSchool[i] == null) {
				this.listOfGroupsInSchool[i] = newGroup;
				return;
			}
		}
		if (counter == 0) {
			System.out.println("Can't add " + newGroup.getSubject() + " group to " + this.name + ". The school has full list of groups.");
		}
	}
	
	public void addStudent(Person newStudent) {
		if (!(newStudent instanceof Student)) {
			System.out.println(newStudent.viewPerson() + " is not a student.");
			return;
		} else {
			for (int i = 0; i < this.listOfStudentsInSchool.length; i++) {
				if (this.listOfTeachersInSchool[i] != null && this.listOfStudentsInSchool[i].equals(newStudent)) {
					System.out.println(newStudent.viewPerson() + " is already listed as a teacher in " + this.name);
					return;
				}
			}
			int counter = 0;
			for (int i = 0; i < this.listOfStudentsInSchool.length; i++) {
				if (this.listOfStudentsInSchool[i] == null) {
					this.listOfStudentsInSchool[i] = (Student) newStudent;
					return;
				}
			}
			if (counter == 0) {
				System.out.println("Can't enroll " + newStudent.viewPerson() + " as a student in " + this.name + " because the list of students is already full.");
			}
			
		}
	}
	
	public void viewTeacherInfo(Teacher teacher) {
		for (int i = 0; i < this.listOfTeachersInSchool.length; i++) {
			if (this.listOfTeachersInSchool[i] != null && this.listOfTeachersInSchool[i].equals(teacher)) {
				this.listOfTeachersInSchool[i].viewTeacher();
				return;
			}
		}
	}
	
	
}
