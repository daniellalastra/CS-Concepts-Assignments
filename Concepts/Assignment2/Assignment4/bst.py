class Node:
    def __init__(self, student):
        self.student = student
        self.left = None
        self.right = None

class BinarySearchTree:
    def __init__(self):
        self.root = None

    def insert(self, student):
        if self.root == None:
            self.root = Node(student)
        else:
            self._insert_recursive(self.root, student)

    def _insert_recursive(self, current_node, student):
        if student.student_number < current_node.student.student_number:
            if current_node.left == None:
                current_node.left = Node(student)
            else:
                self._insert_recursive(current_node.left, student)

        else:
            if current_node.right == None:
                current_node.right = Node(student)
            else:
                self._insert_recursive(current_node.right, student)

    def inorder_traversal(self):
        result = []
        self._inorder_recursive(self.root, result)
        return result

    def _inorder_recursive(self, current_node, result):
        if current_node is not None:
            self._inorder_recursive(current_node.left, result)
            result.append(current_node.student)
            self._inorder_recursive(current_node.right, result)

