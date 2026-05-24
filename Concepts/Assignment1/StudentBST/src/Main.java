import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;



public class Main {
    private static final DateTimeFormatter BDAY_FMT = DateTimeFormatter.ofPattern("M.d.yyyy");

    public static void main(String[] args) {

        //System.out.println("Project is running!");
        String majorsFile = "majors.txt";



        Set<String> majors = new HashSet<String>();
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(majorsFile));
            String line;
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
                lineNum++;
                line = line.trim();
                if (!line.isEmpty()) {
                    majors.add(line.toUpperCase());
                }

            }
            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading majors file: " + e.getMessage());
            return;
        }
        //System.out.println("Total majors: " + majors.size());

        if (args.length < 1) {
            System.out.println("Usage: java Main <student_file>");
            return;
        }
        String studentFile = args[0];


        BST<Student> tree = new BST<>();

        try {BufferedReader stuReader = Files.newBufferedReader(Paths.get(studentFile));
            String stuLine;
            int lineNum = 0;
            while ((stuLine = stuReader.readLine()) != null) {
                lineNum++;
                stuLine = stuLine.trim();
                if (stuLine.isEmpty()) continue;

                Student s = parseStudentLine(stuLine, lineNum, majors);
                if (s != null) {
                    tree.insert(s);
                }

            }
            stuReader.close();
        } catch (IOException e) {
            System.out.println("Error reading student file: " + e.getMessage());

        }
       tree.inOrderTraversal();
    }

    private static Student parseStudentLine(String line, int lineNum, Set<String> validMajors) {
        String[] parts = line.trim().split("\\s+");

        if (parts.length != 6 && parts.length != 7) {
            System.out.println("Line " + lineNum + ": wrong number of fields (" + parts.length + "). Skipping.");
            return null;
        }

        String name = parts[0];
        String studentNum = parts[1];
        String major = parts[2].toUpperCase();

        if (!studentNum.matches("\\d{9}")) {
            System.out.println("Line " + lineNum + ": invalid student number '" + studentNum + "'. Skipping.");
            return null;
        }

        if (!validMajors.contains(major)) {
            System.out.println("Line " + lineNum + ": invalid major '" + parts[2] + "'. Skipping.");
            return null;
        }
        String birthdayRaw = parts[3];
        String gpaRaw = parts[4];


        LocalDate birthday;
        try {
            birthday = LocalDate.parse(birthdayRaw, BDAY_FMT);
        } catch (DateTimeParseException e) {
            System.out.println("Line " + lineNum + ": invalid birthday '" + birthdayRaw + "'. Skipping.");
            return null;
        }


        double gpa;
        try {
            gpa = Double.parseDouble(gpaRaw);
        } catch (NumberFormatException e) {
            System.out.println("Line " + lineNum + ": invalid GPA '" + gpaRaw + "'. Skipping.");
            return null;
        }


        if (gpa < 0.0 || gpa > 4.0) {
            System.out.println("Line " + lineNum + ": GPA out of range '" + gpa + "'. Skipping.");
            return null;
        }


        if (parts.length == 6) {
            String classificationSTR = parts[5];
            int classificationCode;
            try {
                classificationCode = Integer.parseInt(classificationSTR);
            } catch (NumberFormatException e) {
                System.out.println("Line " + lineNum + ": invalid class '" + classificationSTR + "'. Skipping.");
                return null;
            }
            Classification classification;
            if (classificationCode == 1) {
                classification = Classification.FRESHMAN;
            } else if (classificationCode == 2) {
                classification = Classification.SOPHOMORE;
            } else if (classificationCode == 3) {
                classification = Classification.JUNIOR;
            } else if (classificationCode == 4) {
                classification = Classification.SENIOR;
            } else {
                System.out.println("line " + lineNum + ": classification must be 1-4. Skipping.");
                return null;
            }
            return new UndergraduateStudent(name, studentNum, birthday, major,gpa, classification);

        }
        String thesisTitle = parts[5];
        String advisor =  parts[6];
        return new GraduateStudent(name, studentNum, birthday, major,gpa, thesisTitle, advisor);
    }

}

