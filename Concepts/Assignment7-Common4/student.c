#include "student.h"
#include <stdio.h>

int undergradIsOnProbation(struct UndergraduateStudent student){
    
    if (student.base.gpa <2.0){
        return 1;
    }else{
        return 0;
    }
}

int gradIsOnProbation(struct GraduateStudent student){
    
    if (student.base.gpa <3.0){
        return 1;
    }else{
        return 0;
    }
}

void printStudent(struct Student student, int onProbation) {
    printf("%s - %d - Probation: %s\n", student.name, student.student_number, onProbation ? "Yes" : "No");
}