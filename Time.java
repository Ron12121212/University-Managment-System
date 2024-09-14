package test1;

import java.util.Scanner;

public class Time 
{
	private int hours;
    private int minutes;

    public Time(int hours, int minutes)
    {
        setHours(hours);
        setMinutes(minutes);
    }
    
    public Time(String time)
    {
    	String[] parts = time.split(":");
        setHours(Integer.parseInt(parts[0]));
        setMinutes(Integer.parseInt(parts[1]));
    }
    
    public Time() 
    {
        this.hours = 0;
        this.minutes = 0;
    }
    
    public Time(Time other) 
    {
        this.hours = other.hours;
        this.minutes = other.minutes;
    }

    // Getters and setters
    public int getHours() 
    {
        return hours;
    }

    public void setHours(int hours)
    {
        Tests.checkHours(hours);
        this.hours = hours;
    }

    public int getMinutes() 
    {
        return minutes;
    }

    public void setMinutes(int minutes)
    {
        Tests.checkMinutes(minutes);
        this.minutes = minutes;
    }
    
    public static Time enterTime(Scanner scanner)
    {
    	System.out.println("\nEnter time (hh:mm)");
        String time_str = scanner.nextLine();
        Time time = new Time(time_str);
    	return time;
    }

    @Override
    public String toString() 
    {
        return String.format("%02d:%02d", hours, minutes);
    }
}
