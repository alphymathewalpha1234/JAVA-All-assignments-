import java.util.ArrayList;
import java.util.Scanner;

class Person {
    protected String name;
}

class Student extends Person {
    private int rollNo;
    private String course;
    private double marks;
    private char grade;

    public Student() {}

    public Student(int rollNo, String name, String course, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.course = course;
        setMarks(marks);
        calculateGrade();
    }

    public void inputDetails(Scanner sc) {
        System.out.print("Enter Roll No: ");
        rollNo = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        name = sc.nextLine();

        System.out.print("Enter Course: ");
        course = sc.nextLine();

        System.out.print("Enter Marks (0-100): ");
        marks = sc.nextDouble();

        while (marks < 0 || marks > 100) {
            System.out.print("Invalid marks. Enter again (0-100): ");
            marks = sc.nextDouble();
        }

        calculateGrade();
    }

    public void displayDetails() {
        System.out.println(this);
    }

    private void calculateGrade() {
        if (marks >= 90) grade = 'A';
        else if (marks >= 75) grade = 'B';
        else if (marks >= 50) grade = 'C';
        else grade = 'D';
    }

    public void setMarks(double marks) {
        if (marks < 0 || marks > 100) throw new IllegalArgumentException("Marks must be between 0 and 100.");
        this.marks = marks;
        calculateGrade();
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNo +
               "\nName: " + name +
               "\nCourse: " + course +
               "\nMarks: " + marks +
               "\nGrade: " + grade;
    }
}

public class StudentRecordSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n===== Student Record Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Student s = new Student();
                    s.inputDetails(sc);
                    students.add(s);
                    break;

                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No student records found.");
                    } else {
                        for (Student st : students) {
                            st.displayDetails();
                            System.out.println();
                        }
                    }
                    break;

                case 3:
                    System.out.println("Exiting the application. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 3);

        sc.close();
    }
}
