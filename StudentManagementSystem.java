import java.util.*;

public class StudentManagementSystem {
    private static final ArrayList<Student> students = new ArrayList<>();
    private static final HashMap<String, Student> studentMap = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Student Management System ===");
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search by Roll Number");
            System.out.println("4. Remove Duplicates");
            System.out.println("5. Sort Students");
            System.out.println("6. Exit");
            System.out.print("Choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input! Enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> searchStudent();
                case 4 -> removeDuplicates();
                case 5 -> sortStudents();
                case 6 -> running = false;
                default -> System.out.println("Invalid choice!");
            }
        }
        System.out.println("Exiting...");
        scanner.close();
    }

    private static void addStudent() {
        System.out.print("Roll Number: ");
        String roll = scanner.nextLine().trim();
        if (studentMap.containsKey(roll)) {
            System.out.println("Roll number already exists!");
            return;
        }
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Marks (0-100): ");
        double marks = scanner.nextDouble();
        scanner.nextLine();

        Student student = new Student(roll, name, marks);
        students.add(student);
        studentMap.put(roll, student);
        System.out.println("Added!");
    }

    private static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students.");
            return;
        }
        System.out.println("\nStudents:");
        System.out.println("------------------------------------------------------------");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void searchStudent() {
        System.out.print("Roll Number: ");
        String roll = scanner.nextLine().trim();
        Student s = studentMap.get(roll);
        System.out.println(s != null ? "\nFound: " + s : "Not found.");
    }

    private static void removeDuplicates() {
        Set<Student> unique = new HashSet<>(students);
        int removed = students.size() - unique.size();
        students.clear();
        students.addAll(unique);
        studentMap.clear();
        for (Student s : students) {
            studentMap.put(s.getRollNumber(), s);
        }
        System.out.println("\nDuplicates removed: " + removed);
        viewAllStudents();
    }

    private static void sortStudents() {
        if (students.isEmpty()) {
            System.out.println("No students.");
            return;
        }
        System.out.println("Sort by: 1=Name, 2=Marks Desc, 3=Roll");
        int opt = scanner.nextInt();
        scanner.nextLine();

        List<Student> sorted = new ArrayList<>(students);
        switch (opt) {
            case 1 -> Collections.sort(sorted);
            case 2 -> sorted.sort(Comparator.comparingDouble(Student::getMarks).reversed());
            case 3 -> sorted.sort(Comparator.comparing(Student::getRollNumber));
            default -> Collections.sort(sorted);
        }

        System.out.println("\nSorted:");
        System.out.println("------------------------------------------------------------");
        for (Student s : sorted) {
            System.out.println(s);
        }
    }
}