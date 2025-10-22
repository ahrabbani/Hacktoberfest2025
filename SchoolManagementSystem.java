/*
SchoolManagementSystem/
├── SchoolManagementSystem.java
├── Student.java
├── Teacher.java
├── Admin.java
├── School.java
├── Utils.java
*/
// Student Class

public class Student {
    private int id;
    private String name;
    private int age;
    private String grade;

    public Student(int id, String name, int age, String grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGrade() { return grade; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Grade: " + grade;
    }
}

// Teacher Class

public class Teacher {
    private int id;
    private String name;
    private String subject;

    public Teacher(int id, String name, String subject) {
        this.id = id;
        this.name = name;
        this.subject = subject;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSubject() { return subject; }

    public void setName(String name) { this.name = name; }
    public void setSubject(String subject) { this.subject = subject; }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Subject: " + subject;
    }
}

// Admin Class

public class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String u, String p) {
        return username.equals(u) && password.equals(p);
    }
}


// School Class


import java.util.*;

public class School {
    private List<Student> students = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();
    private Admin admin = new Admin("admin", "admin123");

    // Student CRUD
    public void addStudent(Student s) { students.add(s); }
    public void removeStudent(int id) {
        students.removeIf(s -> s.getId() == id);
    }
    public Student getStudent(int id) {
        for (Student s : students) if (s.getId() == id) return s;
        return null;
    }
    public void listStudents() {
        for (Student s : students) System.out.println(s);
    }

    // Teacher CRUD
    public void addTeacher(Teacher t) { teachers.add(t); }
    public void removeTeacher(int id) {
        teachers.removeIf(t -> t.getId() == id);
    }
    public Teacher getTeacher(int id) {
        for (Teacher t : teachers) if (t.getId() == id) return t;
        return null;
    }
    public void listTeachers() {
        for (Teacher t : teachers) System.out.println(t);
    }

    public boolean login(String user, String pass) {
        return admin.authenticate(user, pass);
    }
}


// Main Class

import java.util.Scanner;

public class SchoolManagementSystem {
    public static void main(String[] args) {
        School school = new School();
        Scanner sc = new Scanner(System.in);

        System.out.println("*** School Management System ***");
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        if (!school.login(user, pass)) {
            System.out.println("Authentication failed!");
            return;
        }
        while (true) {
            System.out.println("\n1. Add Student\n2. Remove Student\n3. List Students");
            System.out.println("4. Add Teacher\n5. Remove Teacher\n6. List Teachers");
            System.out.println("0. Exit");
            int ch = sc.nextInt(); sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.print("Student Name: "); String n = sc.nextLine();
                    System.out.print("Age: "); int a = sc.nextInt(); sc.nextLine();
                    System.out.print("Grade: "); String g = sc.nextLine();
                    int id = school.students.size() + 1;
                    school.addStudent(new Student(id, n, a, g));
                    System.out.println("Student added.");
                    break;
                case 2:
                    System.out.print("Student ID to remove: "); int sid = sc.nextInt(); sc.nextLine();
                    school.removeStudent(sid); System.out.println("Student removed.");
                    break;
                case 3:
                    school.listStudents(); break;
                case 4:
                    System.out.print("Teacher Name: "); String tn = sc.nextLine();
                    System.out.print("Subject: "); String sub = sc.nextLine();
                    int tid = school.teachers.size() + 1;
                    school.addTeacher(new Teacher(tid, tn, sub));
                    System.out.println("Teacher added.");
                    break;
                case 5:
                    System.out.print("Teacher ID to remove: "); int deltid = sc.nextInt(); sc.nextLine();
                    school.removeTeacher(deltid); System.out.println("Teacher removed.");
                    break;
                case 6:
                    school.listTeachers(); break;
                case 0:
                    System.out.println("Goodbye!"); return;
            }
        }
    }
}
