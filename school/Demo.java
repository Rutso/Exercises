package school;

public class Demo {

	public static void main(String[] args) {


		//1. Create a school:
		School ittallents = new School("IT Tallents Training Camp");
		
		//2. Create several groups:
		Group javaAndroid = new Group("Java Android", 3);
		Group php = new Group("PHP", 3);
		Group php2 = new Group("PHP", 7);
		Group javaEE = new Group("Java EE", 3);
		Group javaScript = new Group("Java Script", 3);
		
		
		//3. Add groups to the school:
		ittallents.addGroup(javaAndroid);
		ittallents.addGroup(php);
		ittallents.addGroup(php2);									//The program won't add this group because the school already has a group with the same subject.
		ittallents.addGroup(javaEE);
		ittallents.addGroup(javaScript);							//The program will say that the school has a full list of groups.
		

		//4. Create several teachers:
		Person vader = new Teacher("Darth", "Vader", 2);
		Person luke = new Teacher("Luke", "Skywalker", 3);
		Person lukeWannaBe = new Teacher("Luke", "Skywalker", 2);	//The program won't add him because we already have Luke in our team.
		Person jaba = new Teacher("Jaba", "The Hut", 1);
		Person wannaBeTeacher = new Student("Jar Jar", "Binks");	//Let's check if the program works well...
		
		//5. Add teachers to the school:
		ittallents.addTeacher(vader);
		ittallents.addTeacher(luke);
		ittallents.addTeacher(lukeWannaBe);
		ittallents.addTeacher(jaba);
		ittallents.addTeacher(wannaBeTeacher);						//The program will reveal that the person we want to add is not a teacher.
		
		
		//6. Add groups to teachers:
		((Teacher) vader).addGroup(javaAndroid);
		((Teacher) vader).addGroup(javaAndroid);					//The program won't accept this because Vader already has this group.
		((Teacher) vader).addGroup(php);
		((Teacher) vader).addGroup(javaEE);							//Vader can handle only 2 groups...
		
		
		//7.Create students:
		Person leia = new Student("Princess", "Leia");
		Person r2d2 = new Student("R2", "D2");
		Person c3p0 = new Student("C3", "P0");
		Person boba = new Student("Boba", "Fet");
		
		//8.Add students to groups in school:
		javaAndroid.addStudent(leia);
		javaAndroid.addStudent(r2d2);
		javaAndroid.addStudent(c3p0);
		javaAndroid.addStudent(boba);								//The program will say that the group is already full.
		javaAndroid.addStudent(c3p0);								//The program will say that we already have this student in this group.
		php.addStudent(boba);
		
		
		//9. View teacher:
		ittallents.viewTeacherInfo((Teacher) vader);
		
		
		
		
		
		
		
	}

}
