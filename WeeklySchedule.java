package test1;

import java.util.ArrayList;
import java.util.List;

public class WeeklySchedule // Implemented as tree structure with Day as children and TimeSlot as leafs
{
	// TimeSlot is a private class encapsulated inside WeeklySchedule to limit access
	private class TimeSlot 
	{
		private Time start_time;
	    private Time end_time;
	    private Course course;

	    public TimeSlot(Course course) 
	    {
	        this.start_time = course.getStartTime();
	        this.end_time = course.getEndTime();
	        this.course = course;
	    }

	    public Time getStartTime() 
	    {
	        return start_time;
	    }

	    public Time getEndTime() 
	    {
	        return end_time;
	    }

	    public Course getCourse() 
	    {
	        return course;
	    }
	}
	
	// Day is a private class encapsulated inside WeeklySchedule to limit access
	private class Day 
	{
		private String name;
	    private List<TimeSlot> timeSlots;

	    public Day(String name) 
	    {
	        this.name = name;
	        this.timeSlots = new ArrayList<>();
	    }

	    public String getName() 
	    {
	        return name;
	    }

	    public void addTimeSlot(TimeSlot timeSlot) 
	    {
	        timeSlots.add(timeSlot);
	    }

	    public List<TimeSlot> getTimeSlots() 
	    {
	        return timeSlots;
	    }
	}
	
	// Continuing to implement WeeklySchedule
	private List<Day> days;

    public WeeklySchedule()
    {
        days = new ArrayList<>();
        initializeDays();
    }
    
    public WeeklySchedule(WeeklySchedule other) 
    {
        days = other.getDays();
    }

    private void initializeDays() 
    {
        String[] dayNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (String name : dayNames) 
        {
            days.add(new Day(name));
        }
    }

    public void addCourse(Course course) 
    {
        TimeSlot timeSlot = new TimeSlot(course);
        for (Day day : days) 
        {
            if (day.getName().equalsIgnoreCase(course.getDay())) 
            {
                day.addTimeSlot(timeSlot);
                break;
            }
        }
    }
    
    public void removeCourse(Course course) 
    {
        for (Day day : days) 
        {
            if (day.getName().equalsIgnoreCase(course.getDay())) 
            {
                List<TimeSlot> timeSlots = day.getTimeSlots();
                timeSlots.removeIf(timeSlot -> timeSlot.getCourse().equals(course));
                break;
            }
        }
    }

    public List<Day> getDays() 
    {
        return days;
    }
    
    public void displayWeeklySchedule() 
    {
        System.out.println("+---------------------------------------------+");
        System.out.println("|                Weekly Schedule              |");
        System.out.println("+---------------------------------------------+");
        for (Day day : days) 
        {
            System.out.printf("| %-23s                     |\n", day.getName());
            List<TimeSlot> timeSlots = day.getTimeSlots();
            for (TimeSlot timeSlot : timeSlots) 
            {
            	Course course = timeSlot.getCourse();
                System.out.printf("|    %s-%s: %-28s|\n", timeSlot.getStartTime().toString(), timeSlot.getEndTime().toString(), course.getCourseName());
                System.out.printf("|    Teacher: %-32s|\n", course.getTeacherName());
                System.out.printf("|    Location: %-31s|\n", course.getLocation());
            }
            System.out.println("+---------------------------------------------+");
        }
    }
}