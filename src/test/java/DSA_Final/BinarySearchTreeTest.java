package DSA_Final;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {

    private BinarySearchTree bst;

    @BeforeEach
    public void setUp() {
        bst = new BinarySearchTree(); // a fresh binary search tree before each test
    }

    @Test
    public void testInsertAndInOrderTraversal() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        bst.insert(7);

        String expectedInOrder = "3,5,7,10,15";
        assertEquals(expectedInOrder, bst.inorder());
    }

    @Test
    public void testInsertEmptyTree() {
        String expectedInOrder = ""; // an empty tree, no numbers inserted
        assertEquals(expectedInOrder, bst.inorder());
    }

    @Test
    public void testInsertSingleNode() {
        bst.insert(10);
        String expectedInOrder = "10"; // only one number in the tree
        assertEquals(expectedInOrder, bst.inorder());
    }
}




