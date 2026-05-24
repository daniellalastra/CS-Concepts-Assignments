from student import Student

class Undergraduate(Student):
    def __init__(self, name, student_number, birthday, major, gpa, classification):
        super().__init__(name, student_number, birthday, major, gpa)
        self.classification = classification

    def on_probation(self):
        if self.gpa < 2.0:
            return True
        else:
            return False