import sys
from bst import BinarySearchTree
from undergradute import Undergraduate
from graduate import Graduate

def read_students_from_file(filename):
    bst = BinarySearchTree()
    try:
        with open(filename, 'r') as file:
            for line in file:
                line = line.strip()
                if not line:
                    continue

                parts = line.split(',')
                student_type = parts[0].strip()

                if student_type == "U" and len(parts) < 7:
                    print(f"Warning: Skipping malformed undergraduate line (only {len(parts)} fields): {line}")
                    continue
                elif student_type == "G" and len(parts) < 8:
                    print(f"Warning: Skipping malformed graduate line (only {len(parts)} fields): {line}")
                    continue

                if student_type == "U":
                    student = Undergraduate(parts[1].strip(), int(parts[2].strip()), parts[3].strip(), parts[4].strip(), float(parts[5].strip()), parts[6].strip())
                    bst.insert(student)

                elif student_type == "G":
                    student = Graduate(parts[1].strip(), int(parts[2].strip()), parts[3].strip(), parts[4].strip(), float(parts[5].strip()), parts[6].strip(), parts[7].strip())
                    bst.insert(student)


    except FileNotFoundError:
        print("File Not Found!")
        sys.exit(1)
    except ValueError:
        print("Value Error!")
        sys.exit(1)
    except Exception as e:
        print(f"Unexpected error: {e}")
        sys.exit(1)
    return bst

def main():
    if len(sys.argv) != 2:
        print("Usage: python main.py <filename>")
        sys.exit(1)

    filename = sys.argv[1]
    bst = read_students_from_file(filename)
    students = bst.inorder_traversal()

    print("Students sorted by student number:")
    print("-" * 40)
    for student in students:
        print(student)

if __name__ == "__main__":
    main()
