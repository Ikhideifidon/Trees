import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static java.lang.System.out;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LinkedBinaryTreeTest {

    LinkedBinaryTree<Integer> linkedBinaryTree;

    @BeforeEach
    public void setUp() {
        linkedBinaryTree = new LinkedBinaryTree<>();
        // {6, 2, 7, 1, 4, 3, 5, 9, 8}
        int[] linkedBinaryTreeBuild = {33, 20, 40, 18, 32, 35, 45, 19, 25, 34, 38, 43, 50, 23, 28, 27, 29};
        for (int data : linkedBinaryTreeBuild)
            linkedBinaryTree.nonRecursiveInsert(data);
    }
    @Test
    void isEmpty() {
    }

    @Test
    void height() {
        Assertions.assertEquals(8, linkedBinaryTree.height());
    }

    @Test
    void size() {
    }

    @Test
    void insert() {
        out.println(linkedBinaryTree);
    }

    @Test
    void remove() {
    }

    @Test
    void search() {
        Assertions.assertEquals(true, linkedBinaryTree.search(32));
    }

    @Test
    void minimumValue() {
        Assertions.assertEquals(18, linkedBinaryTree.minimumValue());
    }

    @Test
    void maximumValue() {
        Assertions.assertEquals(50, linkedBinaryTree.maximumValue());
    }

    @Test
    void inorderSuccessor() {
        out.println(linkedBinaryTree.inorderSuccessor(32));
    }

    @Test
    void inorderPredecessor() {
        Assertions.assertEquals(20, linkedBinaryTree.inorderPredecessor(25));

    }

    @Test
    void testEquals() {
    }

    @Test
    void mirror() {
    }

    @Test
    void preOrder() {
        linkedBinaryTree.preOrder();
    }

    @Test
    void inOrder() {
        linkedBinaryTree.inOrder();
    }

    @Test
    void testInOrder() {
    }

    @Test
    void postOrder() {
    }

    @Test
    void inorder() {
    }

    @Test
    void levelOrderTraversal() {
        linkedBinaryTree.levelOrderTraversal();
    }

    @Test
    void nonRecursiveHeight() {
        out.println(linkedBinaryTree.nonRecursiveHeight());
    }

    @Test
    void nonRecursivePreorder() {
        out.println(linkedBinaryTree.nonRecursivePreorder());
    }

    @Test
    void nonRecursiveInorder() {
        out.println(linkedBinaryTree.nonRecursiveInorder());
    }

    @Test
    void nonRecursivePostorder() {
        out.println(linkedBinaryTree.nonRecursivePostorder());
        out.println(linkedBinaryTree.size());
        linkedBinaryTree.nonRecursiveRemove(45);
        linkedBinaryTree.nonRecursiveRemove(25);
        linkedBinaryTree.nonRecursiveRemove(50);
        out.println(linkedBinaryTree.nonRecursiveInorder());
        out.println(linkedBinaryTree.nonRecursiveFindMaximum());
        out.println(linkedBinaryTree.size());
        out.println(linkedBinaryTree.numberOfLeavesNode());
        out.println(linkedBinaryTree.isValidNonRecursive());
        out.println(linkedBinaryTree.diameterOfBinaryTree());
        out.println(linkedBinaryTree.morrisInorderTraversal());
        out.println(linkedBinaryTree.nonRecursivePreorder());
    }
    @Test
    void morrisInorderTraversal() {
        Assertions.assertEquals(linkedBinaryTree.morrisInorderTraversal(), linkedBinaryTree.nonRecursiveInorder());
        out.println(linkedBinaryTree.morrisPreorderTraversal());
        out.println(linkedBinaryTree.nonRecursivePostorder());
        out.println(linkedBinaryTree.morrisPostorderTraversal(56));
        out.println(linkedBinaryTree.getPathFromRoot(28));
        out.println(linkedBinaryTree.nonRecursivePostorder());
        out.println(linkedBinaryTree.height());
        linkedBinaryTree.flattenBSTToLinkedListUsingRecursionPreorder();
        out.println(linkedBinaryTree.height());
        out.println(linkedBinaryTree.diameterOfBinaryTree());


    }
}