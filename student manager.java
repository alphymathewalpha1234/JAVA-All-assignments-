
import java.util.*;

class StudentNotFoundException extends Exception {

    public StudentNotFoundException(String message) {
        super(message);
    }
}

class Loader implements Runnable {

    private String task;

    public Loader(String task) {
        this.task = task;
    }

    @Override
    public void run() {
        System.out.print(task);
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(400);
                System.out.print(".");
            }
            System.out.println();
        } catch (InterruptedException e) {
            System.out.println("Loading interrupted!");
        }
    }
}

class Student {
    int rollNo;
    String name;
    String email;
    String course;
    double marks;
    String grade;

    public Student(int rollNo, String name, String email, String course, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.course = course;
        this.marks = marks;
        this.grade = calculateGrade();
        if (name.isEmpty() || email.isEmpty() || course.isEmpty()) {
            throw new IllegalArgumentException("Name, email, and course cannot be empty!");
        }
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100!");
        }
    }

    private String calculateGrade() {
        if (marks >= 90) {
            return "A";
        } else if (marks >= 75) {
            return "B";
        } else if (marks >= 60) {
            return "C";
        } else {
            return "D";
        }
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

    void deleteStudent(int rollNo) throws StudentNotFoundException;

    void updateStudent(int rollNo, Student s) throws StudentNotFoundException;

    Student searchStudent(int rollNo) throws StudentNotFoundException;

    void viewAllStudents();
}

public class StudentManager implements RecordActions {

    Map<Integer, Student> studentMap = new HashMap<>();

    @Override
    public void addStudent(Student s) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter Roll No (Integer): ");
            Integer rollNo = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            if (name.isEmpty()) {
                throw new StudentNotFoundException("Name cannot be empty!");
            }

            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            if (email.isEmpty()) {
                throw new StudentNotFoundException("Email cannot be empty!");
            }

            System.out.print("Enter Course: ");
            String course = sc.nextLine();
            if (course.isEmpty()) {
                throw new StudentNotFoundException("Course cannot be empty!");
            }

            System.out.print("Enter Marks: ");
            Double marks = Double.parseDouble(sc.nextLine());
            if (marks < 0 || marks > 100) {
                throw new IllegalArgumentException("Marks must be between 0 and 100!");
            }
            Thread t = new Thread(new Loader("Loading"));
            t.start();
            t.join();
            String grade;
            if (marks >= 90) {
                grade = "A";
            } else if (marks >= 75) {
                grade = "B";
            } else if (marks >= 60) {
                grade = "C";
            } else {
                grade = "D";
            }
            System.out.println("\n--- Student Details ---");
            System.out.println("Roll No: " + rollNo);
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Course: " + course);
            System.out.println("Marks: " + marks);
            System.out.println("Grade: " + grade);

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numeric data!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        } finally {
            System.out.println("\nProgram execution completed.");
            sc.close();
        }
    }

    public static void main(String[] args) {
        StudentManager sm = new StudentManager();
        try {
            sm.addStudent();
        } catch (StudentNotFoundException e) {
            System.out.println("Student not found: " + e.getMessage());
        }
    }
}
