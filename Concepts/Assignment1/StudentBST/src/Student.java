import java.time.LocalDate;

public abstract class Student implements Comparable<Student>{
    private final String name;
    private final String studentNumber;
    private final LocalDate birthday;
    private final String major;
    private final double gpa;

    public Student(String name, String studentNumber, LocalDate birthday, String major, double gpa) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.birthday = birthday;
        this.major = major;
        this.gpa = gpa;
    }
    public String getName() {
        return name;
    }
    public String getStudentNumber() {
        return studentNumber;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public String getMajor() {
        return major;
    }
    public double getGpa() {
        return gpa;
    }
    public abstract boolean isOnProbation();

    @Override
    public int compareTo(Student other) {
        return this.getStudentNumber().compareTo(other.getStudentNumber());
    }
    @Override
    public String toString() {
        return getName() + " " + getStudentNumber() + " " +
                (isOnProbation() ? "PROBATION" : "OK");
    }


}
