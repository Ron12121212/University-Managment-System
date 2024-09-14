package test1;

import java.util.ArrayList;
import java.util.Stack;

public class Grade
{
    private String student_id;
    private Stack<Double> grades;

    // Constructor
    public Grade(String student_id)
    {
        setStudentID(student_id);
        this.grades = new Stack<Double>();
    }
    
    public Grade(Grade other) 
    {
        this.student_id = new String(other.student_id);
        this.grades = new Stack<Double>();
        for (Double grade : other.grades) 
        {
            this.grades.add(grade);
        }
    }

    public String getStudentID() 
    {
        return student_id;
    }

    public void setStudentID(String student_id) 
    {
    	Tests.checkID(student_id);
        this.student_id = student_id;
    }
    
    public void enterGrade(double grade) 
    {
        grades.push(grade);
    }
    
    double getLatestGrade()
    {
        return grades.peek();
    }
    
    public double removeLatestGrade() 
    {
        return grades.pop();
    }
    
    public static Grade findGradeByStudentID(ArrayList<Grade> list, String student_id) 
    {
        for (Grade grade : list) 
        {
            if (grade.getStudentID().equals(student_id)) 
                return grade;
        }
        throw new IllegalArgumentException("This student is not enrolled for this course.");
    }

    public void displayGrades() 
    {
        System.out.println("Grades for " + student_id + ": " + grades);
    }
    
    @Override
    public String toString() 
    {
        return "Grade{" +
                "student_id='" + student_id + '\'' +
                ", grades=" + grades + '}';
    }
}

