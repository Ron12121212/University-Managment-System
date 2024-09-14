package test1;

import java.util.LinkedList;
import java.util.Scanner;

public class ManagementSystem implements ManagementSystemInterface 
{
	private String username = "Admin";
    private String password = "ADMIN";
    private LinkedList<User> users;
    private static LinkedList<Course> courses;

    public ManagementSystem() 
    {
        this.users = new LinkedList<>();
        this.courses = new LinkedList<>();
    }
    
    public ManagementSystem(LinkedList<User> users, LinkedList<Course> courses) 
    {
        this.users = users;
        this.courses = courses;
    }

    @Override
    public void addUserToSystem(User user)
    {
        Tests.checkNull(user);
        if (users.contains(user))
        	throw new IllegalArgumentException("User is already in the system.");
        users.add(user);
        System.out.println("User was successfully entered into the system.");
    }

    @Override
    public void removeUserFromSystemByID(String id) 
    {
    	for (User user : users) 
        {
            if (user.getId().equals(id)) 
            {
            	users.remove(user);
            	for (Course course : courses)
            	{
            		for (Student student : course.getStudents())
                	{
                		if (student.getId().equals(id))
                			course.getStudents().remove(student);
                	}
            	}
            	System.out.println("\nUser " + user.getFirstName() + " was successfully removed.");
            	return;
            }
        }
    	System.out.println("\nUser with that Id was not found in the system.");
    }

    @Override
    public void addCourseToSystem(Scanner scanner) 
    {
    	System.out.println("\nEnter course id (9 numbers)");
    	String course_id = scanner.nextLine();
        System.out.println("Enter course name");
        String course_name = scanner.nextLine();
        double credits;
        System.out.println("Enter amount of credits");
        if (scanner.hasNextInt()) 
            credits = scanner.nextInt();
        else 
            credits = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter location for the lectures");
        String location = scanner.nextLine();
        System.out.println("Choose lecture day:");
        String day = Menu.selectDay();
        System.out.println("Lecture start time:");
        Time start = Time.enterTime(scanner);
        System.out.println("Lecture end time:");
        Time end = Time.enterTime(scanner);
    	Course course_to_add = new Course(course_id, course_name, day, start, end, location, credits);
    	System.out.println("Enter teacher name");
        String teacher_name = scanner.nextLine();
        course_to_add.setTeacherName(teacher_name);	
        boolean found_teacher = false;
        for (User user : users)
        {
        	if (user.getFirstName().equals(teacher_name) && user instanceof Instructor)
        		found_teacher = true;
        }
        if (!found_teacher)
        	throw new IllegalArgumentException("No teacher found by the name " + teacher_name + " in the system.");
        if (courses.contains(course_to_add))
        	throw new IllegalArgumentException("Course already exits in system.");
        
        courses.add(course_to_add);
        Instructor teacher =  (Instructor)User.findUserByName(users, teacher_name);
        teacher.addCourse(course_to_add);
    }

    @Override
    public void removeCourseFromSystemByID(String id) 
    {
    	for (Course course : courses) 
        {
            if (course.getCourseID().equals(id)) 
            {
            	courses.remove(course);
            	Instructor teacher =  (Instructor)User.findUserByName(users, course.getTeacherName());
                teacher.removeCourse(course);
            	System.out.println("Course " + course.getCourseName() + " was successfully removed from system.");
            	return;
            }
        }
    	throw new IllegalArgumentException("Course does not exit in system.");
    }
    
    @Override
    public void updateCourse(String course_id, Scanner scanner)
    {
        Course course_to_update = Course.findCourseByID(courses, course_id);
        course_to_update.updateVariables(scanner);
    }
    
    public void updateCourseInstructor(String course_id, Scanner scanner)
    {
    	System.out.println("Do you want to update teacher name ?");
    	int choice = Menu.yesNoMenu();
    	if (choice == 1)
    	{
    		System.out.println("Enter teacher name");
    		String name = scanner.nextLine();
    		Course course_to_update = Course.findCourseByID(courses, course_id);
    		for (User user : users) 
            {
            	if (user.getFirstName().equals(course_to_update.getTeacherName()))
            		((Instructor)user).removeCourse(course_to_update);
            }
            course_to_update.setTeacherName(name, this);
            for (User user : users) 
            {
            	if (user.getFirstName().equals(name))
            		((Instructor)user).addCourse(course_to_update);
            }
    	}
    }
    
    public void updateCourseInstructor(String course_id, String instructor_name)
    {
    	Course course_to_update = Course.findCourseByID(courses, course_id);
        for (User user : users) 
        {
        	if (user.getFirstName().equals(instructor_name))
        	{
        		((Instructor)user).addCourse(course_to_update);
        		course_to_update.setTeacherName(instructor_name);
        		return;
        	}            		
        }
    }
    
    public void enrollStudentToCourse(String course_id, Student student)
    {
    	 Course course_found = Course.findCourseByID(courses, course_id);
    	 course_found.addStudent(student);
    }
    
    public void removeStudentFromCourse(String course_id, Student student) 
    {
    	Course course_found = Course.findCourseByID(courses, course_id);
   	 	course_found.removeStudent(student);
    }
    
    public String getUsername() 
    {
    	return username;
    }
    
    public boolean confirmPassword(String password) 
    {
    	return this.password.equals(password);
    }
    
    public void addOfficeHours(Student student, String course_id)
    {
    	Tests.checkNull(student);
    	Course course_found = Course.findCourseByID(courses, course_id);
        User user_found = User.findUserByName(users, course_found.getTeacherName());
        ((Instructor)user_found).addStudentToOfficeHours(student);
        System.out.println("You request for office hours in course " + course_found.getCourseName() + " was sent successfully");
    }
    
    public void displayCoursesNotEnrolledIDName(Student student) 
    {
    	for (Course course : courses) 
        {
            if (!(course.getStudents().contains(student))) 
            	System.out.println("Course ID: " + course.getCourseID() + "\tCourse Name: " + course.getCourseName());
        }	
    }
    
    public void displayCoursesEnrolledIDName(Student student) 
    {
    	for (Course course : courses) 
        {
            if (course.getStudents().contains(student)) 
            	System.out.println("Course ID: " + course.getCourseID() + "\tCourse Name: " + course.getCourseName());
        }	
    }

    public LinkedList<User> getUsers()
    {
        return users;
    }

    public LinkedList<Course> getCourses()
    {
        return courses;
    }
    
    @Override
    public void displayCourses()
    {
    	System.out.println();
    	for (Course course : courses) 
    	{
            course.displayCourse();
            System.out.println("\n**********************");
        }
    }
    
    @Override
    public void displayStudents()
    {
    	for (User user : users) 
    	{
    		if (user instanceof Student)
    		{
    			((Student)user).displayInfo();
                System.out.println("\n**********************");
    		}	
        }
    }
    
    @Override
    public void displayInstructors()
    {
    	for (User user : users) 
    	{
    		if (user instanceof Instructor)
    		{
    			((Instructor)user).displayInfo();
                System.out.println("\n**********************");
    		}	
        }
    }
}
