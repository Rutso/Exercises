package school;

public class Group {

	private String subject;
	private Student[] studentsInGroup;
	private Teacher teacher;
	
	
	public Group(String subject, int numberOfStudents) {
		this.subject = subject;
		if (numberOfStudents > 0) {
			this.studentsInGroup = new Student[numberOfStudents];
		} else {
			this.studentsInGroup = new Student[5];
			System.out.println("Invalid number of students entered for the group: " + "'" + subject + "'. Number of students set to default value of 5.");
		}
	}
	
	public String getSubject() {
		return this.subject;
	}
	
	public void setTeacher(Teacher teacher) {
		if (teacher != null) {
			this.teacher = teacher;
		} else {
			System.out.println("Null teacher proposed for group " + "'" + this.subject + "'. Please, assign a real one.");
		}
	}
	
	public void addStudent(Person newStudent) {
		if (newStudent instanceof Student) {
				for (int i = 0; i < this.studentsInGroup.length; i++) {
					if (this.studentsInGroup[i] != null && newStudent.equals(this.studentsInGroup[i])) {
						System.out.println(newStudent.viewPerson() + " is already listed in the group '" + this.subject + "'.");
						return;
					}
				}
		
				int counter = 0;
				for (int i = 0; i < this.studentsInGroup.length; i++) {
					if (this.studentsInGroup[i] == null) {
						this.studentsInGroup[i] = (Student) newStudent;
						return;
					}
				}
				if (counter == 0) {
					System.out.println(newStudent.viewPerson() + " can't be added to group '" + this.subject + "' because the group is already full.");
				}
				
		} else {
			System.out.println(newStudent.viewPerson() + " is not a student.");
		}
		
		
	}
	
	
	public void viewStudentsInGroup() {
		System.out.println(" Students: ");
		for (int i = 0; i < this.studentsInGroup.length; i++) {
			if (this.studentsInGroup[i] != null) {
				System.out.println("  " + this.studentsInGroup[i].viewPerson());
			}
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Group)) {
			return false;
		} else {
			Group otherGroup = (Group) obj;
			if (this.getSubject().equalsIgnoreCase(otherGroup.getSubject())) {
				return true;
			} 
		}
		return false;
	}
	
	
}
