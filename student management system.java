
import java.util.*;

abstract class Person {

    String name;
    String email;

    Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public abstract void displayInfo();
}

class Student extends Person {

    int rollNo;
    String course;
    double marks;
    String grade;

    Student(int rollNo, String name, String email, String course, double marks) {
        super(name, email);
        this.rollNo = rollNo;
        this.course = course;
        this.marks = marks;
        this.grade = calculateGrade(marks);
    }

    String calculateGrade(double marks) {
        if (marks >= 90) {
            return "A";
        } else if (marks >= 75) {
            return "B";
        } else if (marks >= 50) {
            return "C";
        } else {
            return "D";
        }
    }

    String calculateGrade(int marks) {
        return calculateGrade((double) marks);
    }

    public void displayInfo() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + grade);
    }
}

interface RecordActions {

    void addStudent(Student s);

    void deleteStudent(int rollNo);

    void updateStudent(int rollNo, Student s);

    Student searchStudent(int rollNo);

    void viewAllStudents();
}

class StudentManager implements RecordActions {

    Map<Integer, Student> studentMap = new HashMap<>();

    public void addStudent(Student s) {
        if (studentMap.containsKey(s.rollNo)) {
            System.out.println("Student with Roll No " + s.rollNo + " already exists!");
        } else {
            studentMap.put(s.rollNo, s);
            System.out.println("Student added successfully!");
        }
    }

    public void deleteStudent(int rollNo) {
        if (studentMap.remove(rollNo) != null) {
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("No student found with Roll No " + rollNo);
        }
    }

    public void updateStudent(int rollNo, Student s) {
        if (studentMap.containsKey(rollNo)) {
            studentMap.put(rollNo, s);
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("No student found with Roll No " + rollNo);
        }
    }

    public Student searchStudent(int rollNo) {
        return studentMap.get(rollNo);
    }

    public void viewAllStudents() {
        if (studentMap.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (Student s : studentMap.values()) {
                s.displayInfo();
                System.out.println("-----------------");
            }
        }
    }
}

public class StudentManagementSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        while (true) {
            System.out.println("\n--- Student Management Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll No: ");
                    int roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();

                    Student s = new Student(roll, name, email, course, marks);
                    manager.addStudent(s);
                    break;

                case 2:
                    manager.viewAllStudents();
                    break;

                case 3:
                    System.out.print("Enter Roll No to search: ");
                    int searchRoll = sc.nextInt();
                    Student found = manager.searchStudent(searchRoll);
                    if (found != null) {
                        found.displayInfo();
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Roll No to update: ");
                    int updateRoll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Email: ");
                    String newEmail = sc.nextLine();
                    System.out.print("Enter New Course: ");
                    String newCourse = sc.nextLine();
                    System.out.print("Enter New Marks: ");
                    double newMarks = sc.nextDouble();

                    Student updated = new Student(updateRoll, newName, newEmail, newCourse, newMarks);
                    manager.updateStudent(updateRoll, updated);
                    break;

                case 5:
                    System.out.print("Enter Roll No to delete: ");
                    int deleteRoll = sc.nextInt();
                    manager.deleteStudent(deleteRoll);
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
