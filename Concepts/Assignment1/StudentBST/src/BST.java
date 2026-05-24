public class BST<T extends Comparable<T>> {
    private static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data) {
            this.data = data;
        }
    }
    private Node<T> root;
    public void insert(T value){
        root = insertRecursive(root, value);
    }
    private Node<T> insertRecursive(Node<T> current, T value){
        if (current == null){
            return new Node<>(value);
        }
        int cmp = value.compareTo(current.data);
        if (cmp < 0){
            current.left = insertRecursive(current.left, value);
        }else if (cmp > 0){
            current.right = insertRecursive(current.right, value);
        }else{

        }
        return current;
    }
    public void inOrderTraversal(){
        inOrderRecursive(root);
    }
    private void inOrderRecursive(Node<T> current){
        if (current == null) return;

        inOrderRecursive(current.left);
        System.out.println(current.data);
        inOrderRecursive(current.right);
    }
}
