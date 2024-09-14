package test1;

import java.util.Date;
import java.util.Scanner;

public class Student extends User       // inheriting from User
{
    private String degreeName;
	private double gpa;
	private int currentYear;
	private int currentSemester;
	private WeeklySchedule schedule;

    // Constructor
	public Student(String id, String firstName, String lastName, Date birthDate, String username, String password, String degreeName) 
	{
        super(id, firstName, lastName, birthDate, username, password);
        setDegreeName(degreeName);
        this.gpa = 0;
        setCurrentYear(1);
        setCurrentSemester(1);
        this.schedule = new WeeklySchedule();
    }
	
	public Student(String id, String firstName, String lastName, Date birthDate, String username, String password, String degreeName,
			int currentYear, int currentSemester, double gpa) 
	{
        super(id, firstName, lastName, birthDate, username, password);
        setDegreeName(degreeName);
        setGPA(gpa);
        setCurrentYear(currentYear);
        setCurrentSemester(currentSemester);
        this.schedule = new WeeklySchedule();
    }
    
    public Student() 
    {
        super();
        this.degreeName = "";
        this.gpa = 0;
        this.currentYear = 0;
        this.currentSemester = 0;
        this.schedule = new WeeklySchedule();
    }
    
    public Student(Student other, String course_name) 
    {
        super(other);
        setDegreeName(other.getDegreeName());
        setGPA(other.getGPA());
        setCurrentYear(other.getCurrentYear());
        setCurrentSemester(other.getCurrentSemester());
        this.schedule = new WeeklySchedule(other.schedule);
    }

    // Getters and Setters    
    public String getDegreeName() 
    {
		return degreeName;
	}

	public void setDegreeName(String degreeName) 
	{
		Tests.checkNameWithSpace(degreeName);
		this.degreeName = degreeName;
	}
    
    public double getGPA() 
    {
		return gpa;
	}
    
    public void setGPA(double gpa)
	{
		if (gpa < 0 || gpa > 100)
			throw new IllegalArgumentException("Error! Invalid GPA, must be between 0 and 100.");
		this.gpa = gpa;
	}

	public void updateGPA(ManagementSystem system)
	{
		double sum_grades = 0;
		double sum_points = 0;
		for (Course course : system.getCourses())
		{
			if (course.getStudents().contains(this))
			{
				sum_grades += course.getGradeByStudentID(getId()) * course.getCredits();
				sum_points += course.getCredits();
			}
		}
		gpa = sum_grades / sum_points;
	}

	public int getCurrentSemester() 
	{
		return currentSemester;
	}
	
	public void setCurrentSemester(int currentSemester) 
	{
		if (currentSemester < 1 || currentSemester > 3)
			throw new IllegalArgumentException("Error! Invalid Semester, must be between 1 to 3");
		this.currentSemester = currentSemester;
	}
	
	public int getCurrentYear() 
	{
		return currentYear;
	}
	
	public void setCurrentYear(int currentYear) 
	{
		if (currentYear < 1 || currentYear > 8)
			throw new IllegalArgumentException("Error! Invalid Year of study, must be between 1 to 8");
		this.currentYear = currentYear;
	}

	public WeeklySchedule getSchedule() 
	{
		return schedule;
	}
	
	public void viewGradesByCourseID(String course_id, ManagementSystem system)
	{
		Course course_found = Course.findCourseByID(system.getCourses(), course_id);
		course_found.displayGradesByStudentID(getId());
	}
	
	public void viewGradesByCourseName(String course_name, ManagementSystem system)
	{
		Course course_found = Course.findCourseByName(system.getCourses(), course_name);
		course_found.displayGradesByStudentID(getId());
	}
	
    // manage courses and grades
    public void enrollInCourse(ManagementSystem system, Scanner scanner)
    {
    	System.out.println("\nAvailable courses :");
        system.displayCoursesNotEnrolledIDName(this);
        System.out.println("\nEnter course ID of the course you want to enroll");
        String enroll_id = scanner.nextLine();
        Tests.checkID(enroll_id);
    	for (Course course : system.getCourses())
    	{
    		if (course.getCourseID().equals(enroll_id))
    		{
    			if (course.getStudents().contains(this))
    				System.out.println("Student is already enrolled in this course.");
    			else
    			{
    				schedule.addCourse(course);
    				system.enrollStudentToCourse(enroll_id, this);
    				System.out.println("Successfully enrolled.");
    			}
    		}
    	}        	
    }
    
    public void enrollInCourse(String enroll_id, ManagementSystem system, Scanner scanner)
    {
        Tests.checkID(enroll_id);
    	for (Course course : system.getCourses())
    	{
    		if (course.getCourseID().equals(enroll_id))
    		{
    			if (course.getStudents().contains(this))
    				System.out.println("Student is already enrolled in this course.");
    			else
    			{
    				schedule.addCourse(course);
    				system.enrollStudentToCourse(enroll_id, this);
    			}
    		}
    	}        	
    }

    public void dropCourse(ManagementSystem system, Scanner scanner)
    {
    	System.out.println("\nCourses currently taking :");
    	system.displayCoursesEnrolledIDName(this);
        System.out.println("\nEnter course ID of the course you want to drop");
        String drop_id = scanner.nextLine();
        Tests.checkID(drop_id);
    	for (Course course : system.getCourses())
    	{
    		if (course.getCourseID().equals(drop_id))
    		{
    			if (course.getStudents().contains(this))
    			{
    				schedule.removeCourse(course);
    				system.removeStudentFromCourse(drop_id, this);
    				System.out.println("Successfully dropped.");
    			}
    			else
    				System.out.println("Student is not enrolled in this course.");
    		}
    	} 	
    }

    @Override
    public void displayInfo()
    {
        super.displayInfo();
        System.out.println("Degree Name: " + degreeName);
        System.out.println("GPA: " + gpa);
        System.out.println("Current Year: " + currentYear);
        System.out.println("Current Semester: " + currentSemester);
    }
    
    @Override
    public String toString() 
    {
        return "Student{" +
                "degreeName='" + degreeName + '\'' +
                ", gpa=" + gpa +
                ", currentYear=" + currentYear +
                ", currentSemester=" + currentSemester +
                ", schedule=" + schedule +
                "} " + super.toString();
    }
}
