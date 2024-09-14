package test1;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Instructor extends User
{
    private Queue<Student> office_hours; 	// Queue for managing help requests
    private WeeklySchedule schedule;

    // Constructors
    public Instructor(String id, String first_name, String last_name, Date birthDate, String username, String password, WeeklySchedule schedule) 
    {
        super(id, first_name, last_name, birthDate, username, password);
        this.office_hours = new LinkedList<Student>();		// LinkedList implements Queue interface
        this.schedule = schedule;
    }
    
    public Instructor(String id, String first_name, String last_name, Date birthDate, String username, String password) 
    {
        super(id, first_name, last_name, birthDate, username, password);
        this.office_hours = new LinkedList<Student>();		// LinkedList implements Queue interface
        this.schedule = new WeeklySchedule();
    }
    
    public Instructor() 
    {
        super();
        this.office_hours = new LinkedList<Student>();		// LinkedList implements Queue interface
        this.schedule = new WeeklySchedule();
    }

    // Getters and Setters
    public void updateCourseByID(String course_id, Scanner scanner, ManagementSystem system)
    {
        Course course_to_update = Course.findCourseByID(system.getCourses(), course_id);
        if (course_to_update.getTeacherName().equals(getFirstName()))
        	course_to_update.updateVariables(scanner);
        else
        	System.out.println("You are not in charge of this course. Cannot update.");
    } 
    
    public void viewSchedule()
    {
        schedule.displayWeeklySchedule();
    }
    
    public void addStudentToOfficeHours(Student student)
    {
    	office_hours.add(student);
    }

    public Student getNextStudent()
    {
        if (office_hours.isEmpty())
        	throw new IllegalArgumentException("No more students in queue for office hours");
        return office_hours.poll();
    }
    
    public void addGradeByCourseAndStudentID(String course_id, String student_id, double grade, ManagementSystem system)
    {
    	Course course_found = Course.findCourseByID(system.getCourses(), course_id);
        if (course_found.getTeacherName().equals(getFirstName()))
        	course_found.addGradeByStudentID(student_id, grade);
        else
        	System.out.println("You are not in charge of this course. Cannot grade.");   	
    }  
    
    public void GradeCourse(String course_id, Scanner scanner, ManagementSystem system)
    {
    	Course course_found = Course.findCourseByID(system.getCourses(), course_id);
    	if (course_found.getTeacherName().equals(getFirstName()))
    	{
    		System.out.println("\nEntering grades for course " + course_found.getCourseName() + " :\n");
    		if(course_found.getStudents().isEmpty())
    			throw new IllegalArgumentException("No students have enrolled to this class.");
        	for (Student student : course_found.getStudents())
        	{
        		double grade;
        		System.out.println("Enter grade for student ID: " + student.getId());
        		if (scanner.hasNextInt()) 
        			grade = scanner.nextInt();
                else 
                	grade = scanner.nextDouble();
        		Tests.checkGrade(grade);
        		course_found.addGradeByStudentID(student.getId(), grade);
        		student.updateGPA(system);
        	}
    	}
    	else
        	System.out.println("You are not in charge of this course. Cannot grade."); 
    }
    
    public void addCourse(Course course)
    {
    	schedule.addCourse(course);
    }
    
    public void removeCourse(Course course)
    {
    	schedule.removeCourse(course);
    }

    public Queue<Student> getOfficeHours()
    {
        return office_hours;
    }   
    
    public void displayOfficeHours() 
    {
        if (office_hours.isEmpty()) 
            System.out.println("\nNo students are currently in the office hours queue.");
        else 
        {
            System.out.println("\nStudents in office hours queue:\n");
            for (Student student : office_hours) 
            {
                System.out.println(student.getFirstName() + " " + student.getLastName());
            }
        }
    }

    public void displayCoursesTeachingIDName(ManagementSystem system) 
    {
    	System.out.println("\nThe courses you are able to edit:");
    	for (Course course : system.getCourses()) 
        {
            if (course.getTeacherName().equals(getFirstName())) 
            	System.out.println("Course ID: " + course.getCourseID() + "\tCourse Name: " + course.getCourseName());
        }	
    }
    
    public void displayCoursesTeaching(ManagementSystem system) 
    {
    	for (Course course : system.getCourses()) 
    	{
    		 if (course.getTeacherName().equals(getFirstName()))
    		 {			 
    			 System.out.println();
    			 course.displayCourse();
    			 System.out.println("\n**********************");	
    		 }
        }
    }

    public void displayInfo(ManagementSystem system) 
    {
        super.displayInfo();
        displayCoursesTeaching(system);
    }
    
    @Override
    public String toString() 
    {
        return "Instructor{" +
                "office_hours=" + office_hours +
                ", schedule=" + schedule +
                "} " + super.toString(); // Include User's toString() output
    }
}
