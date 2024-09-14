# University-Management-System

Java project implementing a login system for different user roles: student, lecturer, and administration. 
Each role is assigned unique credentials and has access to specific functionalities within a menu system. 
The system emphasizes case sensitivity for usernames and passwords.

# Project Overview

This Java project implements a role-based system that offers different functionality to users based on their role (student, lecturer, or administrator). Each user role has a unique set of login credentials and access to specific features through a case-sensitive login system. The project ensures that only authorized users can perform actions relevant to their role.

# Key Features

* Case-Sensitive Login System: Users must enter correct usernames and passwords with attention to uppercase and lowercase letters.
* Role-Based Access:  
        ``Students can access student-related functions (e.g., view grades, assignments, etc).``  
        ``Lecturers can manage course-related data (e.g., updating grades, reviewing assignments, etc).``  
        ``Administrators have full control over the system, including managing user accounts and system settings.``
* Predefined Credentials: The system comes with predefined usernames and passwords for testing purposes (see below).
* User-Friendly Menus: Each user role has a customized menu with available options tailored to their permissions.

# How to Use
**1. Running the Program**

    Ensure that Java is installed on your system.
    Compile and run the program using your preferred Java IDE or command-line tool.

**2. Logging In**

Upon launching the program, you will be prompted to log in with a username and password. Ensure that you enter both in the correct case.  

**Sample Login Credentials:**  

**Students:**  
* Username: rs123  
  Password: ronsmith
* Username: mb123  
Password: matanbrown
* Username: hd123  
Password: harvardduke
* Username: sn123  
Password: samminor
* Username: fs123  
Password: fionashrek
* Username: gc123  
Password: georgeclooney  

**Lecturers:**  
* Username: hm456  
Password: mirimontana
* Username: ns567  
Password: nadavsmith
* Username: yd678  
Password: yanadoc
* Username: dl789  
Password: davidlee
* Username: rg890  
Password: rachelgreen

**Administrator:**  
* Username: ADMIN    
Password: admin

**3. Exploring Menus**

Once logged in, users will be directed to a main menu based on their role. Each menu presents options relevant to the user’s role:

    Students: Options to view personal academic data.
    Lecturers: Options to manage student data, grades, and course content.
    Administrator: Full system control, including the ability to manage users and system settings.

# System Requirements

    Java Development Kit (JDK) 8 or higher
    Any operating system that supports Java
    
# Notes

    The program is case-sensitive, so ensure usernames and passwords are entered correctly.
    The predefined usernames and passwords are for testing purposes. You can modify these in the source code if needed.
