package test1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Course
{
    private String courseID;
    private String course_name;
    private double credits;
    private String location;
    private String day;
    private Time start_time;
    private Time end_time;
    private String teacher_name;
    private ArrayList<Student> students;
    private ArrayList<Grade> grades;

    // Constructor
    public Course(String courseID, String course_name, String day, Time start_time, Time end_time, 
                  String location, double credits)
    {
        setCourseID(courseID);
        setCourseName(course_name);
        setLocation(location);
        setDay(day);
        setStartTime(start_time);
        setEndTime(end_time);
        setCredits(credits);
        this.teacher_name = null;
        this.students = new ArrayList<Student>();
        this.grades = new ArrayList<Grade>();
    }

    public Course(Course other) 
    {
    	setCourseID(other.courseID);
    	setCourseName(other.course_name);
        setLocation(other.location);
        setStartTime(other.start_time);
        setEndTime(other.end_time);       
        setDay(other.day);
        setCredits(other.credits); 
        this.teacher_name = other.teacher_name;
        this.students = new ArrayList<>();
        for (Student student : other.students) 
        {
            this.students.add(new Student(student, other.course_name));
        }
        this.grades = new ArrayList<>();
        for (Grade grade : other.grades) 
        {
            this.grades.add(new Grade(grade));
        }
    }
    
 // Getters and setters
    public String getCourseID() 
    {
        return courseID;
    }

    public void setCourseID(String courseID) 
    {
        Tests.checkID(courseID);
        this.courseID = courseID;
    }

    public String getCourseName() 
    {
        return course_name;
    }

    public void setCourseName(String course_name)
    {
    	Tests.checkNull(course_name);
        this.course_name = course_name;
    }
    
    public String getTeacherName() 
    {
        return teacher_name;
    }
    
    public void setTeacherName(String teacher_name, ManagementSystem system)
    {
    	system.updateCourseInstructor(this.getCourseID(), teacher_name); 	
    }
    
    public void setTeacherName(String teacher_name)
    {
    	Tests.checkName(teacher_name);
    	this.teacher_name = teacher_name;
    }

    public String getDay() 
    {
        return day;
    }
    
    public void setDay(String day) 
    {
    	Tests.checkDay(day);
    	this.day = day;
    }
    
    public String getLocation() 
    {
        return location;
    }
    
    public void setLocation(String location) 
    {
        Tests.checkNoSpecialSigns(location);
        this.location = location;
    }
    
    public Time getStartTime() 
    {
        return start_time;
    }
    
    public void setStartTime(Time start_time) 
    {
        this.start_time = start_time;
    }
    
    public Time getEndTime() 
    {
        return end_time;
    }
    
    public void setEndTime(Time end_time) 
    {
        this.end_time = end_time;
    }
    
    public double getCredits() 
    {
        return credits;
    }
    
    public void setCredits(double credits) 
    {
        Tests.checkCredits(credits);
        this.credits = credits;
    }
    
    public ArrayList<Student> getStudents() 
    {
        return students;
    }

    // manage students and grades
    public void addStudent(Student student)
    {
    	Tests.checkNull(student);
        if (students.contains(student))
        	throw new IllegalArgumentException("This student is already enrolled for this course.");
        students.add(student);  
        grades.add(new Grade(student.getId()));
    }
    
    public void addStudent(Student student, double grade)
    {
    	addStudent(student);
    	addGradeByStudentID(student.getId(), grade);
    }

    public void removeStudent(Student student) 
    {
    	if (!students.contains(student))
        	throw new IllegalArgumentException("This student is not enrolled for this course.");
        students.remove(student);
        for (Grade grade : grades)
    	{
        	grades.remove(grade);
        	break;
    	}
    }

    public void addGradeByStudentID(String student_id, double new_grade)
    {
    	Tests.checkGrade(new_grade);
    	Grade grade_found = Grade.findGradeByStudentID(grades, student_id);
    	grade_found.enterGrade(new_grade);
    }
    
    public void removeLatestGradeByStudentID(String student_id)
    {
    	Grade grade_found = Grade.findGradeByStudentID(grades, student_id);
    	grade_found.removeLatestGrade();
    	
    }
    
    public void displayGradesByStudentID(String student_id)
    {
    	Grade grade_found = Grade.findGradeByStudentID(grades, student_id);
    	grade_found.displayGrades();
    }
    
    public double getGradeByStudentID(String student_id)
    {
    	for (Grade grade : grades)
    	{
    		if (grade.getStudentID().equals(student_id))
    			return grade.getLatestGrade();
    	}
    	throw new IllegalArgumentException("This student is not enrolled for this course.");
    }
    
    public void displayAllGrades()
    {
    	for (Grade grade : grades)
    	{
    		grade.displayGrades();
    	}
    }
    
    public static Course findCourseByID(LinkedList<Course> list, String course_id) 
    {
        for (Course course : list) 
        {
            if (course.getCourseID().equals(course_id)) 
                return course;
        }
        throw new IllegalArgumentException("No course with that ID.");
    }
    
    public static Course findCourseByName(LinkedList<Course> list, String course_name) 
    {
        for (Course course : list) 
        {
            if (course.getCourseName().equals(course_name)) 
                return course;
        }
        throw new IllegalArgumentException("No course by that name.");
    }

    public void updateVariables(Scanner scanner)
    {
    	System.out.println("\nPlease select which detail you want to update: ");
    	System.out.println("1. Course name");
        System.out.println("2. Course Lecture start time");
        System.out.println("3. Course Lecture end time");
        System.out.println("4. Lecture Day");
        System.out.println("5. Lecture Location");
        System.out.println("6. Back To Previous Menu");
    	int choice = Tests.getChoice();
    	switch(choice)
    	{
    	case 1:
    		System.out.println("\nEnter a new course name");
            String new_course_name = scanner.nextLine();
            setCourseName(new_course_name);
            break;
        case 2:
            System.out.println("\nEnter a new lecture start time (hh:mm)");
            String start = scanner.nextLine();
            start_time = new Time(start);
            break;
        case 3:
        	System.out.println("\nEnter a new lecture end time (hh:mm)");
        	String end = scanner.nextLine();
            end_time = new Time(end);
            break;
        case 4:
        	System.out.println("\nChoose a new day for the lectures by it's number:");
            String day = Menu.selectDay();
            setDay(day);
            break;
        case 5:
        	System.out.println("\nEnter a new location for the lectures");
            String newlocation = scanner.nextLine();
            setLocation(newlocation);
            break;
        case 6:
        	return;
        default:
            System.out.println("Invalid choice. No changes made.");
            return;  
    	}
    	System.out.println("Course " + getCourseName() + " was updated successfully.");
    }
    
    public void displayCourse() 
    {
        System.out.println("Course ID: " + courseID);
        System.out.println("Course Name: " + course_name);
        System.out.println("Credits: " + credits);
        System.out.println("Location: " + location);
        System.out.println("Day: " + day);
        System.out.println("Start Time: " + start_time.toString());
        System.out.println("End Time: " + end_time.toString());
        System.out.println("Teacher Name: " + teacher_name);
    }

    // toString method for course details
    @Override
    public String toString() 
    {
        return "Course{" +
                "courseID='" + courseID + '\'' +
                ", course_name='" + course_name + '\'' +
                ", credits=" + credits +
                ", location='" + location + '\'' +
                ", day='" + day + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", teacher_name='" + teacher_name + '\'' +
                ", students=" + students +
                ", grades=" + grades +
                '}';
    }
}
