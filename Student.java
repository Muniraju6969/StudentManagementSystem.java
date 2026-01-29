import java.util.Objects;

public class Student implements Comparable<Student> {
    private String rollNumber;
    private String name;
    private double marks;
    private String grade;

    public Student(String rollNumber, String name, double marks) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.marks = marks;
        this.grade = calculateGrade(marks);
    }

    private String calculateGrade(double marks) {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 70) return "B";
        if (marks >= 60) return "C";
        if (marks >= 50) return "D";
        return "F";
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(rollNumber, student.rollNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNumber);
    }

    @Override
    public String toString() {
        return String.format("Roll: %-10s | Name: %-20s | Marks: %5.2f | Grade: %s",
                rollNumber, name, marks, grade);
    }
}