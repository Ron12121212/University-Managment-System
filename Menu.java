package test1;

import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Menu
{
	private static Scanner scanner = new Scanner(System.in);

    // Main menu
    public static void displayMenu(LinkedList<User> users, LinkedList<Course> courses, ManagementSystem system)
    {
    	boolean finished = false;
    	while(!finished)
    	{
    		Object user = login(users, system);
            if (user instanceof ManagementSystem)
            	finished = displayManagementMenu(system);
            else if (user instanceof Student)
            	finished = displayStudentMenu((Student)user, system);
            else if (user instanceof Instructor)
            	finished = displayInstructorMenu((Instructor)user, system);
    	}
    }


    private static Object login(LinkedList<User> users, ManagementSystem system)
    {
    	Object user = null;
    	do {
    		System.out.println("*** LOGIN ***");
    		System.out.println("Please enter Username : ");
    		String username = scanner.nextLine();
            System.out.println("Please enter password : ");
            String password = scanner.nextLine();

            try {
                Tests.checkUsername(username);
                Tests.checkPassword(password);

                if (username.equals("admin") && password.equals("ADMIN")) 
                	return system;
                
                user = Tests.findUsernamePasswordInSystem(users, username, password);
                if (user == null)
                    System.out.println("\nWrong username or password. Please try again.\n");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
    	}
    	while(user == null);
    	return user;
    }

    // Management menu 
    private static boolean displayManagementMenu(ManagementSystem system)
    {
        while (true)
        {
        	try {
        		System.out.println("\nManagement Menu");
                System.out.println("1. Add User");
                System.out.println("2. Remove User");
                System.out.println("3. Display All Student");
                System.out.println("4. Display All Instructors");
                System.out.println("5. Add Course");
                System.out.println("6. Remove Course");
                System.out.println("7. Update Course");
                System.out.println("8. Display All Courses");
                System.out.println("9. Logout");
                System.out.println("10. Exit");
                System.out.print("Enter your choice: ");

                int choice = Tests.getChoice();
                switch (choice)
                {
                    case 1:	// adding a user
                    	addUserMenu(system);
                        break;
                    case 2:	// removing a user
                    	System.out.println("\nStudents :\n");
                    	system.displayStudents();
                    	System.out.println("\nInstructors : \n");
                    	system.displayInstructors();
                        System.out.println("\nEnter the id number of the Student or Instructor you want to remove");
                        String id = scanner.nextLine();
                        Tests.checkID(id);
                        system.removeUserFromSystemByID(id);
                        break;
                    case 3:	// display students
                    	System.out.println();
                    	system.displayStudents();
                    	break;
                    case 4:	// display instructors
                    	System.out.println();
                    	system.displayInstructors();
                    	break;
                    case 5:	// adding a course
                    	system.addCourseToSystem(scanner);
                        System.out.println("\nCourse was successfully add to system.");
                        break;
                    case 6:	//  removing a course
                    	system.displayCourses();
                    	System.out.println("\nEnter the id number of the Course you want to remove");
                        String remove_id = scanner.nextLine();
                        Tests.checkID(remove_id);
                        system.removeCourseFromSystemByID(remove_id);
                        break;
                    case 7:	// update course
                    	system.displayCourses();
                    	System.out.println("\nEnter the id number of the Course you want to update");
                        String update_id = scanner.nextLine();
                        Tests.checkID(update_id);
                    	system.updateCourseInstructor(update_id, scanner);
                    	system.updateCourse(update_id, scanner);
                    	break;
                    case 8:	// display all courses
                    	system.displayCourses();
                    	break;
                    case 9:	// logout
                        return false; 
                    case 10:	// exit
                    	System.out.println("\nExiting the program. Thank you for using our system.");
                    	scanner.close();
                    	return true;
                    default:
                        System.out.println("\nInvalid choice. Please enter a valid number.");
                }
        	} catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Student menu
    private static boolean displayStudentMenu(Student student, ManagementSystem system)
    {
        while (true)
        {
        	try {
        		System.out.println("\nStudent Menu");
                System.out.println("1. Enroll in Course");
                System.out.println("2. Drop a Course");
                System.out.println("3. Update Profile");
                System.out.println("4. Display Schedule");
                System.out.println("5. Display GPA");
                System.out.println("6. Send Request For Office Hours");
                System.out.println("7. Display My Profile");
                System.out.println("8. Logout");
                System.out.println("9. Exit");
                System.out.print("Enter your choice: ");
                
                int choice = Tests.getChoice();
                switch (choice)
                {
                    case 1:	// enroll in course
                        student.enrollInCourse(system, scanner);
                        break;
                    case 2:	// drop a course
                        student.dropCourse(system, scanner);
                        break;
                    case 3:	//  updating student profile
                        student.updateVariables(scanner);
                        break;
                    case 4:	// display schedule
                        student.getSchedule().displayWeeklySchedule();
                        break;
                    case 5:	// display gpa
                        System.out.println("\n" + student.getFirstName() + " your current GPA is : " + student.getGPA());
                        break;
                    case 6:	// request office hours
                    	System.out.println("\nCourses currently taking :");
                    	system.displayCoursesEnrolledIDName(student);
                    	System.out.println("\nEnter course ID of the course you want office hours in");
                        String request_id = scanner.nextLine();
                        Tests.checkID(request_id);
                        system.addOfficeHours(student, request_id);
                        break;
                    case 7:	// display profile
                        student.displayInfo();
                        break;
                    case 8:	// logout
                        return false; 
                    case 9:	// exit
                    	System.out.println("\nExiting the program. Thank you for using our system.");
                    	scanner.close();
                    	return true;
                    default:
                        System.out.println("\nInvalid choice. Please enter a valid number.");
                }
        	} catch (Exception e) {
	            System.out.println(e.getMessage());
        	}
        }	
    }

    // instructor  menu 
    private static boolean displayInstructorMenu(Instructor instructor, ManagementSystem system)
    {
        while (true)
        {
        	try {
        		System.out.println("\nProfessor Menu");
                System.out.println("1. Update Profile");
                System.out.println("2. Update Course");
                System.out.println("3. Give Grades");
                System.out.println("4. Display Courses");
                System.out.println("5. Display Office Hours Requests");
                System.out.println("6. Display Schedule");
                System.out.println("7. Display My Profile");
                System.out.println("8. Logout");
                System.out.println("9. Exit");
                System.out.print("Enter your choice: ");

                int choice = Tests.getChoice();
                switch (choice)
                {
                    case 1:	// update profile
                    	instructor.updateVariables(scanner);
                        break;
                    case 2:	// update course
                    	instructor.displayCoursesTeachingIDName(system);
                    	System.out.println("\nEnter the id number of the Course you want to update");
                        String update_id = scanner.nextLine();
                        Tests.checkID(update_id);
                    	instructor.updateCourseByID(update_id, scanner, system);
                        break;
                    case 3:	// give grades
                    	instructor.displayCoursesTeachingIDName(system);
                    	System.out.println("\nEnter the id number of the Course you want to grade");
                        String grade_id = scanner.nextLine();
                        Tests.checkID(grade_id);
                    	instructor.GradeCourse(grade_id, scanner, system);
                        break;
                    case 4:	// display courses
                    	instructor.displayCoursesTeaching(system);
                        break;
                    case 5:	// display office hours
                    	instructor.displayOfficeHours();
                    	if (!instructor.getOfficeHours().isEmpty())
                    	{
                    		System.out.println();
                    		System.out.println("\nDo you want to accept next student ?");
                        	int choice_oh = yesNoMenu();
                        	if (choice_oh == 1)
                        		instructor.getNextStudent().displayInfo();
                    	}
                        break;
                    case 6:	// display schedule
                        instructor.viewSchedule();
                        break;
                    case 7:	// display profile
                        instructor.displayInfo();
                        break;
                    case 8:	// logout
                        return false; 
                    case 9:	// exit
                    	System.out.println("\nExiting the program. Thank you for using our system.");
                    	scanner.close();
                    	return true;
                    default:
                        System.out.println("\nInvalid choice. Please enter a valid number.");
                }
        	} catch (Exception e) {
	            System.out.println(e.getMessage());
        	}
        }
    }
    
    public static void addUserMenu(ManagementSystem system)
    {
    	int choice = 0;
    	do {
    		try {
    			System.out.println("\nDo you wish to add :");
                System.out.println("1. Student");
                System.out.println("2. Instructor");
                System.out.println("3. Return to menu");
                choice = Tests.getChoice();
                String id;
                String first_name;
                String last_name;
                String date_str;
                Date date = null;
                switch (choice)
                {
                    case 1:
                    	System.out.println("\nYou have selected to add a Student");
                    	System.out.println("Enter id");
                    	id = scanner.nextLine();
                        System.out.println("Enter first name");
                        first_name = scanner.nextLine();
                        System.out.println("Enter last name");
                        last_name = scanner.nextLine();
                        System.out.println("Enter date of birth (dd/mm/yyyy)");
                        date_str = scanner.nextLine();
                        date = Tests.checkDateStr(date_str);
                        System.out.println("Enter degree name");
                        String degree_name = scanner.nextLine();
                    	system.addUserToSystem(new Student(id, first_name, last_name, date, id, id, degree_name));
                        break;
                    case 2:
                    	System.out.println("\nYou have selected to add an Instructor");
                    	System.out.println("Enter id");
                    	id = scanner.nextLine();
                        System.out.println("Enter first name");
                        first_name = scanner.nextLine();
                        System.out.println("Enter last name");
                        last_name = scanner.nextLine();
                        System.out.println("Enter date of birth (dd/mm/yyyy)");
                        date_str = scanner.nextLine();
                        date = Tests.checkDateStr(date_str);
                    	system.addUserToSystem(new Instructor(id, first_name, last_name, date, id, id));
                        break;
                    case 3:
                    	return;
                    default:
                        System.out.println("\nInvalid choice. Please enter a valid number.");
                }
    		} catch (Exception e) {
	            System.out.println(e.getMessage());
        	}
    	}while(choice>=1 && choice <=3);
    }
    
    public static String selectDay()
    {
    	String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        for (int i = 0; i < days.length; i++) 
        {
            System.out.println((i + 1) + ". " + days[i]);
        }
        int choice = Tests.getChoice();
        if (choice>=1 && choice<=7)
        	return days[choice-1];
        else
        	throw new IllegalArgumentException("\nError! Invalid day selected.");
    }
    
    public static int yesNoMenu()
    {
    	System.out.println("1. Yes");
    	System.out.println("2. No");
    	int choice = Tests.getChoice();
    	return choice;
    }
}
