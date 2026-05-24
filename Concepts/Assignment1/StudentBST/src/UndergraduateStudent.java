import java.time.LocalDate;

public class UndergraduateStudent extends Student {
    private final Classification classification;

    public UndergraduateStudent(String name, String studentNumber, LocalDate birthday, String major, double gpa, Classification classification){
        super(name, studentNumber, birthday, major, gpa);
        this.classification = classification;
    }

    public Classification getClassification() {
        return classification;
    }
    @Override
    public boolean isOnProbation() {
        return getGpa() < 2.0;
    }
}
