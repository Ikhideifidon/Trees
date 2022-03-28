
public interface BinaryTree<E> extends Cloneable {
    boolean isEmpty();
    int height();
    int size();
    void insert(E element);
    boolean search(E element);
    E minimumValue();
    E inorderSuccessor(E element);
    E inorderPredecessor(E element);
    E maximumValue();
    int hashCode();
    boolean equals();
    String toString();
    void mirror();
    void preOrder();

    void postOrder();

    void inOrder();








}
