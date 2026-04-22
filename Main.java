import java.util.*;

public class Main {

    // Student Class
    static class Student {
        int id;
        String name;
        int age;
        String course;

        Student(int id, String name, int age, String course) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.course = course;
        }
    }

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // ===== Helper: Safe Integer Input =====
    static int getValidInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("❌ Please enter a valid number!");
                sc.next(); // clear invalid input
            }
        }
    }

    // ===== Add Student =====
    static void addStudent() {
        int id = getValidInt("Enter ID: ");

        // Duplicate ID check
        if (findStudent(id) != null) {
            System.out.println("❌ ID already exists!");
            return;
        }

        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();

        int age = getValidInt("Enter Age: ");
        if (age <= 0) {
            System.out.println("❌ Invalid age!");
            return;
        }

        sc.nextLine();
        System.out.print("Enter Course: ");
        String course = sc.nextLine().trim();

        students.add(new Student(id, name, age, course));
        System.out.println("✅ Student added successfully!");
    }

    // ===== View Students =====
    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("⚠️ No records found!");
            return;
        }

        System.out.println("\n===== STUDENT LIST =====");
        System.out.println("No | ID | Name | Age | Course");
        System.out.println("----------------------------------------");

        int count = 1;
        for (Student s : students) {
            System.out.println(count++ + " | " + s.id + " | " + s.name + " | " + s.age + " | " + s.course);
        }
    }

    // ===== Find Student =====
    static Student findStudent(int id) {
        for (Student s : students) {
            if (s.id == id) return s;
        }
        return null;
    }

    // ===== Update Student =====
    static void updateStudent() {
        int id = getValidInt("Enter ID to update: ");
        Student s = findStudent(id);

        if (s == null) {
            System.out.println("❌ Student not found!");
            return;
        }

        sc.nextLine();
        System.out.print("Enter new name: ");
        s.name = sc.nextLine().trim();

        int age = getValidInt("Enter new age: ");
        if (age <= 0) {
            System.out.println("❌ Invalid age!");
            return;
        }
        s.age = age;

        sc.nextLine();
        System.out.print("Enter new course: ");
        s.course = sc.nextLine().trim();

        System.out.println("✅ Student updated successfully!");
    }

    // ===== Delete Student =====
    static void deleteStudent() {
        int id = getValidInt("Enter ID to delete: ");
        Student s = findStudent(id);

        if (s == null) {
            System.out.println("❌ Student not found!");
            return;
        }

        sc.nextLine();
        System.out.print("Are you sure you want to delete? (yes/no): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            students.remove(s);
            System.out.println("✅ Student deleted successfully!");
        } else {
            System.out.println("❌ Deletion cancelled.");
        }
    }

    // ===== Search Student =====
    static void searchStudent() {
        int id = getValidInt("Enter ID to search: ");
        Student s = findStudent(id);

        if (s != null) {
            System.out.println("\n===== STUDENT FOUND =====");
            System.out.println("ID | Name | Age | Course");
            System.out.println("--------------------------------");
            System.out.println(s.id + " | " + s.name + " | " + s.age + " | " + s.course);
        } else {
            System.out.println("❌ Student not found!");
        }
    }

    // ===== Main Menu =====
    public static void main(String[] args) {

        while (true) {
            System.out.println("\n==============================");
            System.out.println("  STUDENT MANAGEMENT SYSTEM");
            System.out.println("==============================");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student");
            System.out.println("6. Exit");

            int choice = getValidInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    System.out.println("👋 Exiting... Thank you!");
                    return;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
}