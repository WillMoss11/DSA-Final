package DSA_Final;

public class BinarySearchTree {

    // a node class for each element in the tree
    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    // insert a new value into the BST
    public void insert(int data) {
        root = insertRec(root, data);
    }

    // insert a value in the tree
    private Node insertRec(Node root, int data) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Node(data);
            return root;
        }

        // go down the tree
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    // show the tree as a string with comma-separated values (in-order traversal)
    public String inorder() {
        StringBuilder sb = new StringBuilder();
        inorderRec(root, sb);
        // remove the trailing space and return the string with commas
        return sb.toString().trim().replace(" ", ",");
    }

    // perform in-order of the tree
    private void inorderRec(Node root, StringBuilder sb) {
        if (root != null) {
            inorderRec(root.left, sb);
            sb.append(root.data).append(" ");
            inorderRec(root.right, sb);
        }
    }

    // to convert the tree into a JSON-like string
    public String toJson() {
        return toJsonHelper(root);
    }

    // helper conversion to JSON-like format
    private String toJsonHelper(Node node) {
        return toJsonHelper(node, 0); // Pass the indentation level (initially 0)
    }

    // helper method with indentation
    private String toJsonHelper(Node node, int level) {
        if (node == null) {
            return "null";
        }

        String indent = "  ".repeat(level); // Indentation for current level
        String left = toJsonHelper(node.left, level + 1); // get left child
        String right = toJsonHelper(node.right, level + 1); // get right child

        return String.format("%s{ \"value\": %d,\n%s  \"left\": %s,\n%s  \"right\": %s\n%s}",
                indent, node.data, indent, left, indent, right, indent);
    }
}



