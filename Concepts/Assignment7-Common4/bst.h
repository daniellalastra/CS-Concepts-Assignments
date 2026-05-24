#ifndef BST_H
#define BST_H
#include "student.h"


struct Node{
    
    int student_number;
    char name[50];
    int onProbation;
    struct Node* left;
    struct Node* right;
};

struct Node* insert(struct Node* root, int student_number, char name[], int onProbation);
void inorder(struct Node* root);
struct Node* createNode(int studentNumber, char name[], int onProbation);

#endif