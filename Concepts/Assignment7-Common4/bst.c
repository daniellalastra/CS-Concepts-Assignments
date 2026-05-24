#include "bst.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Node* createNode(int studentNumber, char name[], int onProbation) {
    struct Node* node = (struct Node*)malloc(sizeof(struct Node));
    node->left = NULL;
    node->right = NULL;
    strcpy(node->name, name);
    node->student_number = studentNumber;
    node->onProbation = onProbation;
    return node;
}

struct Node* insert(struct Node* root, int student_number, char name[], int onProbation){
    if(root == NULL){
        return createNode(student_number, name, onProbation);
    }else if (student_number < root->student_number){
        root->left = insert(root->left, student_number, name, onProbation);
        return root;
    }else if(student_number > root->student_number){
        root->right = insert(root->right, student_number, name, onProbation);
        return root;
    }
    return root;
}

void inorder(struct Node* root){
    if(root==NULL){
        return;
    }
    inorder (root->left);
    printf("%s - %d - Probation: %s\n", root->name, root->student_number, root->onProbation ? "Yes" : "No");
    inorder (root->right);

}
