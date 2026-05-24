from student import Student
class Graduate(Student):
    def __init__(self, name, student_number, birthday, major, gpa, thesis_title, advisor):
        super().__init__(name, student_number, birthday, major, gpa)
        self.thesis_title = thesis_title
        self.advisor = advisor

    def on_probation(self):
        if self.gpa < 3.0:
            return True
        else:
            return False