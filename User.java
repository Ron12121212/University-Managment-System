package test1;

import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public abstract class User
{
    private String id;
    private String first_name;
    private String last_name;
    private Date birthDate;
    private String username;
    private String password;

    // Constructor
    public User(String id, String first_name, String last_name, Date birthDate, String username, String password) 
    {
    	setId(id);
    	setFirstName(first_name);
    	setLastName(last_name);
    	setUsername(username);
    	setBirthDate(birthDate);
    	setPassword(password);
    }
    
    public User() 
    {
        this.id = "";
        this.first_name = "";
        this.last_name = "";
        this.birthDate = new Date();
        this.username = "";
        this.password = "";
    }
    
    public User(User other) 
    {
    	setId(other.getId());
    	setFirstName(other.getFirstName());
    	setLastName(other.getLastName());
    	setUsername(other.getUsername());
    	setBirthDate(other.getBirthDate());
    	setPassword(other.password);
    }

    // Getters and Setters
    public String getId() 
    {
        return id;
    }

    private void setId(String id)
    {
        Tests.checkID(id);
        this.id = id;
    }

    public String getFirstName() 
    {
        return first_name;
    }

    private void setFirstName(String first_name)
    {
        Tests.checkName(first_name);
        this.first_name = first_name;
    }

    public String getLastName()
    {
        return last_name;
    }

    private void setLastName(String last_name)
    {
        Tests.checkName(last_name);
        this.last_name = last_name;
    }

    public Date getBirthDate()
    {
        return birthDate;
    }

    private void setBirthDate(Date birthDate) 
    {
        Tests.checkNull(birthDate);
        this.birthDate = birthDate;
    }

    public String getUsername()
    {
        return username;
    }

    private void setUsername(String username)
    {
        Tests.checkUsername(username);
        this.username = username;
    }

    private void setPassword(String password) 
    {
    	Tests.checkPassword(password);
        this.password = password;
    }
    
    public boolean confirmPassword(String password) 
    {
    	return this.password.equals(password);
    }

    public void updateVariables(Scanner scanner)
    {
    	System.out.println("\nPlease select which detail you want to update: ");
    	System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Username");
        System.out.println("4. Password");
        System.out.println("5. Back To Previous Menu");
    	int choice = Tests.getChoice();
    	switch(choice)
    	{
    	case 1:
    		System.out.println("\nEnter a new first name:");
            String new_first_name = scanner.nextLine();
            setFirstName(new_first_name);
            break;
        case 2:
            System.out.println("\nEnter a new last name:");
            String new_last_name = scanner.nextLine();
            setLastName(new_last_name);
            break;
        case 3:
            System.out.println("\nEnter a new username:");
            String newUsername = scanner.nextLine();
            setUsername(newUsername);
            break;
        case 4:
        	System.out.println("\nEnter your current password for varification:");
        	String current_password = scanner.nextLine();
        	if(!confirmPassword(current_password))
        		throw new IllegalArgumentException("Password does not match, cannot proceed");
            System.out.println("Enter new password:");
            String newPassword = scanner.nextLine();
            setPassword(newPassword);
            break;
        case 5:
        	return;
        default:
            System.out.println("\nInvalid choice. No changes made.");
            break;
    	}
    }
    
    public static User findUserByID(LinkedList<User> list, String id) 
    {
        for (User user : list) 
        {
            if (user.getId().equals(id)) 
                return user;
        }
        throw new IllegalArgumentException("No user with that ID.");
    }
    
    public static User findUserByName(LinkedList<User> list, String name) 
    {
        for (User user : list) 
        {
            if (user.getFirstName().equals(name)) 
                return user;
        }
        throw new IllegalArgumentException("No user by that name.");
    }
    
    @SuppressWarnings("deprecation")	// It works fine and unrelated to our project
    public void displayInfo() 
    {
        System.out.println("ID : " + getId());
        System.out.println("Name : " + getFirstName() + " " + getLastName());
        System.out.println("Birth Date : " + String.format("%02d", getBirthDate().getDay()) + "/" +
                String.format("%02d", getBirthDate().getMonth()) + "/" + String.format("%04d", getBirthDate().getYear()));
    }
    
    @Override
    public String toString() 
    {
        return "User{" +
                "id = '" + id + '\'' +
                ", first_name = '" + first_name + '\'' +
                ", last_name = '" + last_name + '\'' +
                ", birthDate = " + birthDate +
                ", username = '" + username + '\'' +
                ", password = '" + password + '\'' + '}';
    }
}
