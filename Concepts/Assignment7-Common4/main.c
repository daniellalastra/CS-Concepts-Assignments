#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "student.h"
#include "bst.h"

int main(int argc, char* argv[]){
    if (argc != 2){
        printf("Usage: %s <filename>\n", argv[0]);
        exit(1);
    }
    FILE* file = fopen(argv[1], "r");

    if (file == NULL){
        printf("Error: Could not open file %s\n", argv[1]);
        exit(1);
    }

    struct Node* root = NULL;

    char line[200];
    while (fgets(line, sizeof(line), file)) {
        char* name = strtok(line, " ");
        char* student_number_str = strtok(NULL, " ");
        int student_number = atoi(student_number_str);
        char* major = strtok(NULL, " ");
        char* birthday = strtok(NULL, " ");
        char* gpa_str = strtok(NULL, " ");
        char* extra = strtok(NULL, " ");
        float gpa = atof(gpa_str);
        if(atoi(extra) != 0){
            struct UndergraduateStudent student;
            strcpy(student.base.name, name);
            student.base.student_number = student_number;
            student.base.gpa = gpa;
            strcpy(student.classification, extra);
            int onProbation = undergradIsOnProbation(student);
            root = insert(root, student_number, name, onProbation);
        }else{
            struct GraduateStudent student;
            strcpy(student.base.name, name);
            student.base.student_number = student_number;
            student.base.gpa = gpa;
            strcpy(student.thesis_title, extra);
            char* advisor = strtok(NULL, " ");
            strcpy(student.advisor, advisor);
            int onProbation = gradIsOnProbation(student);
            root = insert(root, student_number, name, onProbation);
        }

        
    }
    inorder(root);
    fclose(file);
    return 0;
}

