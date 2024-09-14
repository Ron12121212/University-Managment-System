package test1;

import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Main
{
	@SuppressWarnings("deprecation")	// It works fine and unrelated to our project
	public static void main(String[] args)
    { 
    	Scanner scanner = new Scanner(System.in);

        // linked lists for users and courses
        LinkedList<User> users = new LinkedList<>();
        LinkedList<Course> courses = new LinkedList<>();

        // Adding Students and Instructors
        Student student1 = new Student("123456789", "Ron", "Smith", new Date(1992, 2, 21), "ronsmith", "rs123", "Computer Science");
        Student student2 = new Student("123451234", "Matan", "Brown", new Date(1993, 4, 15), "matanbrown", "mb123", "Mathematics");
        Student student3 = new Student("123452345", "Harvard", "Duke", new Date(1991, 6, 30), "harvardduke", "hd123", "Biology");
        Student student4 = new Student("123453456", "Sami", "Nor", new Date(1994, 10, 5), "saminor", "sn123", "Physics");
        Student student5 = new Student("123454567", "Fiona", "Shrek", new Date(1995, 1, 12), "fionashrek", "fs123", "Engineering");
        Student student6 = new Student("123455678", "George", "Clooney", new Date(1990, 11, 22), "georgeclooney", "gc123", "History");
        users.add(student1);
        users.add(student2);
        users.add(student3);
        users.add(student4);
        users.add(student5);
        users.add(student6);
        users.add(new Instructor("987656789", "Miri", "Montana", new Date(1975, 5, 15), "mirimontana", "hm456"));
        users.add(new Instructor("987657890", "Nadav", "Smith", new Date(1970, 3, 10), "nadavsmith", "ns567"));
        users.add(new Instructor("987658901", "Yana", "Doc", new Date(1980, 8, 25), "yanadoc", "yd678"));
        users.add(new Instructor("987659012", "David", "Lee", new Date(1968, 2, 5), "davidlee", "dl789"));
        users.add(new Instructor("987660123", "Rachel", "Green", new Date(1972, 11, 30), "rachelgreen", "rg890"));
     
        // Adding Courses
        courses.add(new Course("000000101", "Intro to Computer Science", "Monday", new Time("9:30"), new Time("11:00"), "Room 101", 5.0));
        courses.add(new Course("000000102", "Algebra", "Tuesday", new Time("10:00"), new Time("11:30"), "Room 102", 2.5));
        courses.add(new Course("000000103", "Biology", "Wednesday", new Time("13:00"), new Time("14:30"), "Room 103", 4.0));
        courses.add(new Course("000000104", "Physics 1", "Thursday", new Time("8:00"), new Time("11:00"), "Room 104", 3.5));
        courses.add(new Course("000000105", "Intro to Engineering", "Friday", new Time("16:00"), new Time("17:30"), "Room 105", 1.5));
        courses.add(new Course("000000106", "Chemistry", "Monday", new Time("14:00"), new Time("15:30"), "Room 106", 3.0));
        courses.add(new Course("000000107", "Machine Learning", "Tuesday", new Time("12:00"), new Time("15:00"), "Room 107", 5.5));
        courses.add(new Course("000000108", "Data Structures", "Wednesday", new Time("8:30"), new Time("10:00"), "Room 108", 2.0));
        courses.add(new Course("000000109", "Electronic Devices", "Thursday", new Time("18:00"), new Time("19:30"), "Room 109", 4.5));
        courses.add(new Course("000000110", "Probability and Statistics", "Friday", new Time("9:00"), new Time("10:30"), "Room 110", 1.0));

        ManagementSystem system = new ManagementSystem(users, courses);
        
     // Enrolling students to courses
        student1.enrollInCourse("000000101", system, scanner);
        student1.enrollInCourse("000000107", system, scanner);
        student1.enrollInCourse("000000103", system, scanner);
        student1.enrollInCourse("000000109", system, scanner);
        student2.enrollInCourse("000000102", system, scanner);
        student2.enrollInCourse("000000108", system, scanner);
        student2.enrollInCourse("000000104", system, scanner);
        student2.enrollInCourse("000000110", system, scanner);
        student3.enrollInCourse("000000109", system, scanner);
        student3.enrollInCourse("000000105", system, scanner);
        student3.enrollInCourse("000000106", system, scanner);
        student4.enrollInCourse("000000104", system, scanner);
        student4.enrollInCourse("000000110", system, scanner);
        student5.enrollInCourse("000000105", system, scanner);
        student5.enrollInCourse("000000101", system, scanner);
        student5.enrollInCourse("000000107", system, scanner);
        student5.enrollInCourse("000000103", system, scanner);
   
        // Assigning instructors to courses
        system.updateCourseInstructor("000000101", "Nadav");
        system.updateCourseInstructor("000000102", "Yana");
        system.updateCourseInstructor("000000103", "Rachel");
        system.updateCourseInstructor("000000104", "David");
        system.updateCourseInstructor("000000105", "Nadav");
        system.updateCourseInstructor("000000106", "Rachel");
        system.updateCourseInstructor("000000107", "Yana");
        system.updateCourseInstructor("000000108", "Miri");
        system.updateCourseInstructor("000000109", "David");
        system.updateCourseInstructor("000000110", "Miri");
        
        // start the menu
        Menu.displayMenu(users, courses, system);
    }
}