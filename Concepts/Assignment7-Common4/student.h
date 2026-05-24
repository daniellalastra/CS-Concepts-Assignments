#ifndef STUDENT_H
#define STUDENT_H

struct Student {
    char name[50];
    int student_number;
    char birthday[20];
    char major[50];
    float gpa;
};

struct UndergraduateStudent{
    struct Student base;
    char classification[20];
};

struct GraduateStudent{
    struct Student base;
    char thesis_title[100];
    char advisor[50];
};

int undergradIsOnProbation(struct UndergraduateStudent student);
int gradIsOnProbation(struct GraduateStudent student);
void printStudent(struct Student student, int onProbation);


#endif