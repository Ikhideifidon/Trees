import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A Binary Tree Implementation using a Linked Structure
 * @param <E>
 */
public class LinkedBinaryTree<E extends Object & Comparable<E>> implements BinaryTree<E>, Cloneable {

    // ..................Nested light weight Node class......................
    private static class Node<E> {
        private E data;
        private Node<E> left;
        private Node<E> right;

        // A default Constructor that avoids a default null data.
        private Node(E data) {
            this.data = data;
            left = right = null;
        }
    }

    private Node<E> root = null;
    private int t = 0;

    public LinkedBinaryTree() {
    }

    // Copy Constructor
    public LinkedBinaryTree(LinkedBinaryTree<E> that) {

    }


    private E nullChecker(E element) {
        if (element == null)
            throw new IllegalArgumentException("Forbidden Operation. Null cannot be added to the tree.");
        return element;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int height() {
        return height(root);
    }

    /**
     * For an empty tree T, its height is 0
     * For a single node tree, its height is 1.
     *
     * @param root:
     * @return An integer that denotes the number of nodes along the longest path from the root to the deepest leaf node.
     */
    private int height(Node<E> root) {
        if (root == null)
            return 0;
        int left = height(root.left);
        int right = height(root.right);
        return Math.max(left, right) + 1;
    }

    @Override
    public int size() {
        return t;
    }

    @Override
    public void insert(E element) {
        root = insert(root, nullChecker(element));
        t++;
    }

    private Node<E> insert(Node<E> root, E element) {
        if (root == null)
            root = new Node<>(element);
            // left check
        else if (root.data.compareTo(element) >= 0)
            root.left = insert(root.left, element);
            // Right check
        else
            root.right = insert(root.right, element);
        return root;

    }


    public void buildBST(E[] array) {
        if (array.length == 0)
            return;
        buildBST(array, 0, array.length - 1);
    }

    private Node<E> buildBST(E[] array, int low, int high) {
        if (low > high)
            return null;
        Arrays.sort(array);
        int mid = low + (high - low) / 2;
        Node<E> root = new Node<>(array[mid]);
        root.left = buildBST(array, low, mid - 1);
        root.right = buildBST(array, mid + 1, high);
        return root;

    }

    public void remove(E element) {
        remove(root, element);
        t--;
    }

    private Node<E> remove(Node<E> root, E element) {
        if (root == null)
            return null;
        // Search for the given element
        if (root.data.compareTo(element) > 0)
            root.left = remove(root.left, element);
        else if (root.data.compareTo(element) < 0)
            root.right = remove(root.right, element);

            // Node has been found
        else {
            // Case 1: The found node has No child
            if (root.left == null && root.right == null)
                root = null;

                // Case 2: The found node has only one child (Right child).
            else if (root.left == null) {
                root = root.right;
            }
            // Case 2: the found node has only one child (Left child).
            else if (root.right == null) {
                root = root.left;
            }

            // Case 3: The found node has both left and right children.
            else {
                Node<E> temp = minimumValue(root.right);          // find the minimum value in the right subtree.
                root.data = temp.data;                   // this minimum value is copied into the root.
                root.right = remove(root.right, temp.data);
            }
        }
        return root;
    }

    @Override
    public boolean search(E element) {
        return search(root, element) != null;
    }

    private Node<E> search(Node<E> root, E element) {
        if (root == null)
            return null;
        else if (root.data.compareTo(element) == 0)
            return root;
        else if (root.data.compareTo(element) > 0)
            return search(root.left, element);
        else
            return search(root.right, element);
    }

    @Override
    public E inorderSuccessor(E element) {
        Node<E> answer = inorderSuccessor(root, element);
        return answer.data;
    }

    private Node<E> inorderSuccessor(Node<E> root, E element) {
        if (root == null)
            return null;
            // Search for the given element
        else {
            Node<E> current = search(root, element);
            // Node not present in BST
            if (current == null)
                return null;

                // Node present in BST
                // Case 1: Node has right subtree
                // Go deep to the leftmost node in the right subtree of find the minimumValue in the right subtree
            else if (current.right != null)
                return minimumValue(current.right);

                // Case 2: Node has no right subtree.
                // Go to the nearest ancestor for which the given node would be in the left subtree.
            else {
                Node<E> successor = null;
                Node<E> ancestor = root;
                while (ancestor != current) {
                    if (ancestor.data.compareTo(current.data) > 0) {
                        successor = ancestor;
                        ancestor = ancestor.left;
                    } else
                        ancestor = ancestor.right;
                }
                return successor;
            }

        }
    }

    @Override
    public E inorderPredecessor(E element) {
        Node<E> answer = inorderPredecessor(root, element);
        return answer.data;
    }

    private Node<E> inorderPredecessor(Node<E> root, E element) {
        if (root == null)
            return null;
        else {
            Node<E> current = search(root, element);
            // Node is absent;
            if (current == null)
                return null;

                // Node is present
            else {
                // Present node has a left subtree
                // The node with the maximum data is the inorder predecessor of current.
                if (current.left != null)
                    return maximumValue(current.left);
                    // Present node has no left subtree
                    // Go to the nearest ancestor for which the given node would be in the right subtree.
                else {
                    Node<E> successor = null;
                    Node<E> ancestor = root;
                    while (ancestor != current) {
                        if (ancestor.data.compareTo(current.data) > 0)
                            ancestor = ancestor.left;
                        else {
                            successor = ancestor;
                            ancestor = ancestor.right;
                        }
                    }
                    return successor;
                }

            }
        }
    }

    @Override
    public E minimumValue() {
        Node<E> answer = minimumValue(root);
        return answer.data;
    }

    private Node<E> minimumValue(Node<E> root) {
        // Empty Tree
        if (root == null)
            return null;
        else {
            if (root.left != null)
                return minimumValue(root.left);
            return root;
        }
    }

    @Override
    public E maximumValue() {
        Node<E> answer = maximumValue(root);
        return answer.data;
    }

    private Node<E> maximumValue(Node<E> root) {
        if (root == null)
            return null;
        else if (root.right != null)
            return maximumValue(root.right);
        return root;
    }

    @Override
    public boolean equals() {
        return false;
    }

    @Override
    public void mirror() {

    }

    @Override
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node<E> root) {
        if (root == null)
            return;
        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }

    @Override
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node<E> root) {
        if (root == null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);

    }

    @Override
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node<E> root) {
        if (root == null)
            return;
        inOrder(root.left);
        System.out.println(root.data);
        inOrder((root.right));
    }

    public int numberOfLeavesNode() {
        return numberOfLeavesNode(root);
    }
    private int numberOfLeavesNode(Node<E> root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        else
            return numberOfLeavesNode(root.left) + numberOfLeavesNode(root.right);
    }

    public int diameterOfBinaryTree() {
        AtomicInteger diameter = new AtomicInteger();
        diameterOfBST(root, diameter);
        return diameter.get();

    }

    private int diameterOfBST(Node<E> root, AtomicInteger diameter) {
        if (root == null)
            return 0;
        int leftHeight = diameterOfBST(root.left, diameter);
        int rightHeight = diameterOfBST(root.right, diameter);


        diameter.set(Math.max(diameter.get(), leftHeight + rightHeight));
        return Math.max(leftHeight, rightHeight) + 1;

    }

    // Non-Recursive methods
    public void levelOrderTraversal() {
        if (root == null)
            return;
        Queue<Node<E>> deque = new ArrayDeque<>();
        deque.add(root);
        while (deque.isEmpty()) {
            Node<E> node = deque.remove();
            System.out.println(node.data);
            if (node.left != null)
                deque.add(node.left);
            if (node.right != null)
                deque.add(node.right);
        }
    }

    public int nonRecursiveHeight() {
        if (root == null)
            return 0;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        int height = 0;
        while (!queue.isEmpty()) {
            // Calculate the total number of nodes at the current level
            int size = queue.size();

            // Process each node of the current level and enqueue their
            // non-empty left and right child.
            while (size > 0) {
                Node<E> node = queue.poll();
                assert node != null;                    // Since poll method returns a special value null (Though null is never reached)
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
                size--;
            }
            height++;
        }
        return height;
    }

    public List<E> nonRecursivePreorder() {
        List<E> result = new ArrayList<>();
        if (root == null)
            return result;
        // Stack using Deque interface
        Deque<Node<E>> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<E> node = stack.pop();
            result.add(node.data);
            if (node.right != null)
                stack.addFirst(node.right);
            if (node.left != null)
                stack.addFirst(node.left);
        }
        return result;
    }

    public List<E> nonRecursiveInorder() {
        List<E> result = new ArrayList<>();
        if (root == null)
            return result;
        Deque<Node<E>> stack = new LinkedList<>();
        Node<E> node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                result.add(node.data);
                node = node.right;
            }
        }
        return result;
    }

    public List<E> nonRecursivePostorder() {
        List<E> result = new ArrayList<>();
        if (root == null)
            return result;

        Deque<Node<E>> stack = new LinkedList<>();
        Node<E> lastVisited = null;             // to keep track of visited nodes
        Node<E> node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            }
            // Node == null
            else {
                node = stack.peek();
                if (node.right != null && lastVisited != node.right)
                    node = node.right;
                else {
                    lastVisited = node;
                    result.add(stack.pop().data);
                    node = null;
                }
            }
        }
        return result;
    }

    public boolean nonRecursiveSearch(E element) {
        if (root == null || element == null)
            return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (node.data.compareTo(element) == 0)
                return true;
            else {
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }

        }
        return false;
    }

    public void nonRecursiveInsert(E element) {
        Node<E> newest = new Node<>(nullChecker(element));
        if (root == null)
            root = newest;
        else {
            Node<E> current;
            current = root;
            while (true) {
                if (current.data.compareTo(element) >= 0) {
                    if (current.left != null)
                        current = current.left;
                    else {
                        current.left = newest;
                        break;
                    }
                } else {
                    if (current.right != null)
                        current = current.right;
                    else {
                        current.right = newest;
                        break;
                    }
                }
            }
        }
        t++;
    }

    public E nonRecursiveFindMinimum() {
        if (root == null)
            return null;
        Node<E> current = root;
        while (true) {
            if (current.left != null)
                current = current.left;
            else
                return current.data;
        }
    }

    public E nonRecursiveFindMaximum() {
        if (root == null)
            return null;
        Node<E> current = root;
        while (true) {
            if (current.right != null)
                current = current.right;
            else
                return current.data;
        }
    }

    public void nonRecursiveRemove(E element) {
        if (root == null || element == null)
            return;

        Node<E> current, previous;
        current = root;
        previous = null;
        while (current != null && current.data != element) {
            previous = current;
            if (current.data.compareTo(element) > 0)
                current = current.left;
            else
                current = current.right;
        }
        // Exhaustive search: The given element was never found.
        if (current == null)
            return;

        // Such that the rooted node data equals the given element.
        // The given element is found
        if (previous == null) {
            // Check if it is a single node BST
            if (current.left == null && current.right ==  null)
                root = null;
            else
                deleteRootNode(current);
        }

        else {
            if (previous.left == current)
                previous.left = deleteRootNode(current);
            else
                previous.right = deleteRootNode(current);
        }
        t--;
    }

    private Node<E> deleteRootNode(Node<E> root) {
        if (root == null)
            return null;
        if (root.left == null)
            return root.right;
        else if (root.right == null)
            return root.left;
        else {
            Node<E> next = root.right;
            Node<E> nextPrevious = null;
            while(next.left != null) {
                nextPrevious = next;
                next = next.left;
            }
            if (nextPrevious == null)
                next.left = root.left;
            else {
                nextPrevious.left = next.right;
                next.left = root.left;
                next.right = root.right;
            }
            return next;
        }
    }

    public E NonRecursiveDeepestNode() {
        if (root == null)
            return null;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        Node<E> node = new Node<>(null);
        while (!queue.isEmpty()) {
             node = queue.poll();
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }
        return node.data;
    }

    public int nonRecursiveNumberOfLeavesNode() {
        if (root == null)
            return 0;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        int leavesNode = 0;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (node.left == null && node.right == null)
                leavesNode++;
            else {
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
        }
        return leavesNode;
    }

    public boolean isValidNonRecursive() {
        if (root == null)
            return true;
        List<E> result = new ArrayList<>();
        Deque<Node<E>> stack = new LinkedList<>();
        Node<E> node = root;
        int left = 0;
        int right = left + 1;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                result.add(node.data);
                // We only compare after the result has at least two elements.
                if (result.size() >= 2) {
                    if (result.get(left).compareTo(result.get(right)) >= 0)
                        return false;
                    else {
                        left++;
                        right++;
                    }
                }
                node = node.right;
            }
        }
        return true;
    }

    public boolean isValidNonRecursive2() {
        if (root == null)
            return true;
        Deque<Node<E>> stack = new LinkedList<>();
        Node<E> node, previous;
        node = root;
        previous = null;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                // We only compare after the previous data equals the initial data of the node.
                if (previous != null && node.data.compareTo(previous.data) <= 0)
                    return false;
                previous = node;
                node = node.right;
            }
        }
        return true;
    }

    // Morris Inorder Traversal without Recursion and Extra Space
    public List<E> morrisInorderTraversal() {
        if (root == null)
            return new ArrayList<>();
        List<E> list = new ArrayList<>();
        Node<E> current, predecessor;
        current = root;
        while (current != null) {
            // Check if current does not have a left subtree
            if (current.left == null) {
                list.add(current.data);
                current = current.right;
            } else {                                  // Current has a left subtree
                // Find the inorder predecessor of current that is, the largest element smaller than the current.data
                predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current)
                    predecessor = predecessor.right;

                // Check it the loop terminates as a result of predecessor.right being equal to null.
                // If true, connect it back to the current. This also means that the current still has
                // some unvisited left subtree. Hence, move current to its next available left subtree
                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // check if the loop terminates as a result of predecessor.right being equal to
                    // current. If so, it means that such node has been visited before.
                    // Also, this means that we have reached the deepest left node of the current.
                    // Since no more left subtree, print the data and hence disconnect the predecessor.right and
                    // move current to its next available current.right.
                    predecessor.right = null;
                    list.add(current.data);
                    current = current.right;
                }
            }
        }
        return list;
    }

    public List<E> morrisPreorderTraversal() {
        if (root == null)
            return new ArrayList<>();
        List<E> result = new ArrayList<>();
        Node<E> current, predecessor;
        current = root;
        while (current != null) {
            if (current.left == null) {
                result.add(current.data);
                current = current.right;
            } else {
                predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current)
                    predecessor = predecessor.right;

                if (predecessor.right == null) {
                    result.add(current.data);
                    predecessor.right = current;
                    current = current.left;
                } else {
                    predecessor.right = null;
                    current = current.right;
                }
            }
        }
        return result;
    }

    public List<E> morrisPostorderTraversal(E value) {
        if (root == null)
            return new ArrayList<>();
        List<E> result = new ArrayList<>();
        Node<E> dummyRoot, current, first, middle, last;
        dummyRoot = new Node<>(value);
        dummyRoot.left = root;
        current = dummyRoot;
        while (current != null) {
            if (current.left == null) {
                current = current.right;
            }
            else {
                // Find the inorder predecessor of current that is, the largest element smaller than the current.data
                Node<E> predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current)
                    predecessor = predecessor.right;

                // End of loop as a result of predecessor.right being equal to null
                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                }
                // End of loop as a result of predecessor.right being equal to current.
                // This means second predecessor finding (meaning there is a cycle)
                // Reverse these links
                else {
                    first = current;
                    middle = current.left;
                    while (middle != current) {
                        last = middle.right;
                        middle.right = first;
                        first = middle;
                        middle = last;
                    }

                    // Code snippet to print the data and reverse the links again.
                    // This is the recovery process.
                    first = current;
                    middle = predecessor;
                    while (middle != current) {
                        result.add(middle.data);
                        last = middle.right;
                        //noinspection ConstantConditions
                        middle.right = first;
                        first = middle;
                        middle = last;
                    }
                    // Remove the predecessor link to the current
                    predecessor.right = null;
                    current = current.right;
                }
            }
        }
        return result;
    }

    public void flattenBSTToLinkedListUsingStackPreorder() {
        if (root == null)
            return;
        Deque<Node<E>> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<E> node = stack.pop();
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
            if (!stack.isEmpty())
                node.right = stack.peek();
        }
    }

    public void flattenBSTToLinkedListUsingThreadedBinaryTreePreorder() {
        if (root == null)
            return;
        Node<E> current, predecessor;
        current = root;
        while (current != null) {
            if (current.left != null) {
                // Search for the predecessor of the current
                predecessor = current.left;
                while (predecessor.right != null)
                    predecessor = predecessor.right;

                predecessor.right = current.right;
                current.right = current.left;
                current.left = null;
            }
            current = current.right;
        }
    }

    public void flattenBSTToLinkedListUsingRecursionPreorder() {
        flattenBSTToLinkedListUsingRecursionPreorder(root);
    }

    private Node<E> previous = null;
    private void flattenBSTToLinkedListUsingRecursionPreorder(Node<E> root) {
        if (root == null)
            return;
        flattenBSTToLinkedListUsingRecursionPreorder(root.right);
        flattenBSTToLinkedListUsingRecursionPreorder(root.left);
        root.right = previous;
        root.left = null;
        previous = root;
    }

    /**
     * Given an element, check if it is present in a BST; if present return the list of its path.
     * Otherwise, return false;
     * @return
     */
    public List<E> getPathFromRoot(E value) {
        List<E> path = new ArrayList<>();
        getPathFromRoot(root, value, path);
        return path;
    }

    private boolean getPathFromRoot(Node<E> root, E value, List<E> path) {
        if (root == null)
            return false;
        path.add(root.data);
        if (root.data.compareTo(value) == 0 || getPathFromRoot(root.left, value, path) || getPathFromRoot(root.right, value, path))
            return true;
        else
            path.remove(path.size() - 1);
        return false;

    }
}
