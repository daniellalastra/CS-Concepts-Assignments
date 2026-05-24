import java.time.LocalDate;

public class GraduateStudent extends Student {
    private final String thesisTitle;
    private final String facultyAdvisor;

    public GraduateStudent(String name, String studentNumber, LocalDate birthday, String major, double gpa, String thesisTitle,
                           String facultyAdvisor){
        super(name, studentNumber, birthday, major, gpa);
        this.thesisTitle = thesisTitle;
        this.facultyAdvisor = facultyAdvisor;
    }
    public String getThesisTitle() {
        return thesisTitle;
    }
    public String getFacultyAdvisor() {
        return facultyAdvisor;
    }
    @Override
    public boolean isOnProbation() {
        return getGpa() < 3.0;
    }
}
