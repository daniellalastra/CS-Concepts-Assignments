class Student:
    def __init__(self,  name, student_number, birthday, major, gpa):
        self.name = name
        self.student_number = student_number
        self.birthday = birthday
        self.major = major
        self.gpa = gpa

    def on_probation(self):
        pass

    def __str__(self):
        probation = "Yes" if self.on_probation() else "No"
        return f"{self.name} - {self.student_number} - Probation: {probation}"
