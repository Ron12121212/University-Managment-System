package test1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Tests 
{
	private static Scanner scanner = new Scanner(System.in);
	
	public static void checkID(String id)
	{
        if (id.length() != 9) 
            throw new IllegalArgumentException("Error, invalid Id length");
        
        for (char c : id.toCharArray()) 
        {
            if (c < '0' || c > '9') 
                throw new IllegalArgumentException("Error! Invalid Id, can only contain numbers");
        }
    }
	
	public static void checkName(String name)
	{
		if (name.isEmpty())
            throw new IllegalArgumentException("Error! Invalid Name, cannot be empty");

        for (char c : name.toCharArray()) 
        {
            if (c < 'A' || (c > 'Z' && c < 'a') || c > 'z') 
                throw new IllegalArgumentException("Error! Invalid Name, can only contain characters");
        }
    }
	
	public static void checkNameWithSpace(String name)
	{
		if (name.isEmpty())
            throw new IllegalArgumentException("Error! Invalid Name, cannot be empty");

        for (char c : name.toCharArray()) 
        {
            if (c < 'A' || (c > 'Z' && c < 'a') || c > 'z') 
				if (c != ' ')
					throw new IllegalArgumentException("Error! Invalid Name, can only contain characters and spaces");
        }
    }
	
	public static void checkNoSpecialSigns(String str)
	{
		if (str.isEmpty())
            throw new IllegalArgumentException("Error! Invalid string, cannot be empty");

        for (char c : str.toCharArray()) 
        {
            if ((c < 'A' || (c > 'Z' && c < 'a') || c > 'z') && (c < '0' || c > '9')) 
				if (c != ' ')
					throw new IllegalArgumentException("Error! Invalid string, cannot contain special signs");
        }
    }
	
	public static void checkUsername(String name)
	{
		if (name.isEmpty())
            throw new IllegalArgumentException("Error! Invalid Username, cannot be empty");

        for (char c : name.toCharArray()) 
        {
        	if ((c < 'A' || (c > 'Z' && c < 'a') || c > 'z') && (c < '0' || c > '9'))
                throw new IllegalArgumentException("Error! Invalid Username, can only contain characters and numbers");
        }
    }
	
	public static void checkPassword(String name)
	{
		if (name.isEmpty())
            throw new IllegalArgumentException("Error! Invalid Password, cannot be empty");

        for (char c : name.toCharArray()) 
        {
        	if (c == ' ')
                throw new IllegalArgumentException("Error! Invalid Password, cannot contain spaces");
        }
    }
	
	public static void checkHours(int hours)
	{
		if (hours < 0 || hours > 23)
			throw new IllegalArgumentException("Error! Invalid Hours, must be between 0 and 23.");
	}
	
	public static void checkMinutes(int minutes)
    {
        if (minutes < 0 || minutes > 59)
            throw new IllegalArgumentException("Error! Invalid Minutes, must be between 0 and 59.");
    }
	
	public static void checkNull(Object obj) 
    {
        if (obj == null)
            throw new IllegalArgumentException("Error! Cannot be null.");
    }
	
	public static void checkGrade(double grade) 
    {
        if (grade < 0 || grade > 100)
            throw new IllegalArgumentException("Error! Invalid Grade, must be between 0 and 100.");
    }
	
	public static void checkCredits(double credits) 
    {
        if (credits < 1 || credits > 6)
            throw new IllegalArgumentException("Error! Invalid Credits, must be between 1 and 6.");
    }
	
	public static Date checkDateStr(String date_str) 
    {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = dateFormat.parse(date_str);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Error! invalid Date format.");
		}
		return date;
    }
	
	public static void checkDay(String day) 
    {
		String[] dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Unknown"};
    	boolean flag = false;
        for (String d : dayNames) 
        {
            if (d.equalsIgnoreCase(day)) 
            {
                flag = true;
                break;
            }
        }
        if (!flag)
        	throw new IllegalArgumentException("Error! Invalid day value entered.");
    }
	
	public static User findUsernamePasswordInSystem(LinkedList<User> list, String username, String password) 
    {
        for (User user : list) 
        {
            if (user.getUsername().equals(username) && user.confirmPassword(password)) 
                return user;
        }
        return null;
    }
	
	public static int getChoice() 
	{
        int choice;
        if (scanner.hasNextInt()) 
            choice = scanner.nextInt(); 
        else 
        {
            scanner.next(); // Clear the invalid input
            choice = -1; 
        }
        scanner.nextLine(); // Clear the buffer
        return choice;
    }
}
