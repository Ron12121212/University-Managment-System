package test1;

import java.util.Scanner;

public interface ManagementSystemInterface 
{
    void addUserToSystem(User user);
    void removeUserFromSystemByID(String id);
    void addCourseToSystem(Scanner scanner);
    void removeCourseFromSystemByID(String id);
    void updateCourse(String course_id, Scanner scanner);
    void displayCourses();
    void displayStudents();
    void displayInstructors();
}
