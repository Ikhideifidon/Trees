

public class Main {
    public static void main(String[] args) {
        LinkedBinaryTree<Integer> linkedBinaryTree = new LinkedBinaryTree<>();
        linkedBinaryTree.buildBST(new Integer[] {33, 20, 40, 18, 32, 35, 45, 19, 25, 34, 38, 43, 50, 23, 28, 27, 29});
        System.out.println(linkedBinaryTree.nonRecursiveInorder());
//        linkedBinaryTree.nonRecursiveInsert(8);
//        linkedBinaryTree.nonRecursiveInsert(3);
//        linkedBinaryTree.nonRecursiveInsert(15);
//        linkedBinaryTree.nonRecursiveInsert(15);
//        linkedBinaryTree.nonRecursiveInsert(2);
//        linkedBinaryTree.nonRecursiveInsert(12);
////        System.out.println(linkedBinaryTree.nonRecursiveInorder());
//        linkedBinaryTree.remove(8);
//        System.out.println(linkedBinaryTree.nonRecursiveInorder());
//        System.out.println(linkedBinaryTree.search(8));
//        System.out.println(4>>1);
//        System.out.println(linkedBinaryTree.morrisPostorderTraversal(45));
    }
}
