
import java.util.Scanner;

class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(int a, int b) {
        double result = Double.NaN;
        try {
            if (b == 0) {
                throw new ArithmeticException("Cannot divide by zero!");
            }
            result = (double) a / b;
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Division operation completed.");
        }
        return result;
    }

}

public class CalculatorApplication {

    private Scanner sc = new Scanner(System.in);
    private Calculator calculator = new Calculator();

    public void performAddition() {
        System.out.println("\nChoose type of addition:");
        System.out.println("1. Two Integers");
        System.out.println("2. Two Doubles");
        System.out.println("3. Three Integers");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter first integer: ");
                int a = sc.nextInt();
                System.out.print("Enter second integer: ");
                int b = sc.nextInt();
                System.out.println("Result: " + calculator.add(a, b));
                break;

            case 2:
                System.out.print("Enter first double: ");
                double x = sc.nextDouble();
                System.out.print("Enter second double: ");
                double y = sc.nextDouble();
                System.out.println("Result: " + calculator.add(x, y));
                break;

            case 3:
                System.out.print("Enter three integers separated by space: ");
                int n1 = sc.nextInt();
                int n2 = sc.nextInt();
                int n3 = sc.nextInt();
                System.out.println("Result: " + calculator.add(n1, n2, n3));
                break;

            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

    public void performSubtraction() {
        System.out.print("\nEnter first integer: ");
        int a = sc.nextInt();
        System.out.print("Enter second integer: ");
        int b = sc.nextInt();
        System.out.println("Result: " + calculator.subtract(a, b));
    }

    public void performMultiplication() {
        System.out.print("\nEnter first double: ");
        double a = sc.nextDouble();
        System.out.print("Enter second double: ");
        double b = sc.nextDouble();
        System.out.println("Result: " + calculator.multiply(a, b));
    }

    public void performDivision() {
        System.out.print("\nEnter dividend: ");
        int a = sc.nextInt();
        System.out.print("Enter divisor: ");
        int b = sc.nextInt();
        double result = calculator.divide(a, b);
        if (!Double.isNaN(result)) {
            System.out.println("Result: " + result);
        }
    }

    public void mainMenu() {
        int choice;
        do {
            System.out.println("\n===============================");
            System.out.println("   CALCULATOR APPLICATION   ");
            System.out.println("1. Add Numbers");
            System.out.println("2. Subtract Numbers");
            System.out.println("3. Multiply Numbers");
            System.out.println("4. Divide Numbers");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    performAddition();
                    break;
                case 2:
                    performSubtraction();
                    break;
                case 3:
                    performMultiplication();
                    break;
                case 4:
                    performDivision();
                    break;
                case 5:
                    System.out.println("\nThank you for using the Calculator! ");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter between 1-5.");
            }

        } while (choice != 5);
    }

    public static void main(String[] args) {
        CalculatorApplication app = new CalculatorApplication();
        app.mainMenu();
    }
}
