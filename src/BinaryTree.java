
public class BinaryTree {
    private BinaryNode root;

    public BinaryTree(int value) {
        root = new BinaryNode(value);
    }

    public BinaryTree() {

    }

    public void addToTree(int value) {
        if (root == null) {
            root = new BinaryNode(value);
        } else {
            placeInTree(root, new BinaryNode(value));
        }
    }

    private BinaryNode rotateRight(BinaryNode y) {
        BinaryNode x = y.getLeft();
        BinaryNode T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        x.setHeight();
        y.setHeight();

        return x;
    }

    private BinaryNode rotateLeft(BinaryNode x) {
        BinaryNode y = x.getRight();
        BinaryNode T2 = y.getLeft();

        // Perform rotation
        y.setLeft(x);
        x.setRight(T2);

        //  Update heights
        x.setHeight();
        y.setHeight();

        // Return new root
        return y;
    }

    private BinaryNode placeInTree(BinaryNode currentNode, BinaryNode binaryNode) {

        if (currentNode == null) {
            return binaryNode;
        }

        if (currentNode.getValue() > binaryNode.getValue()) {
            currentNode.setLeft(placeInTree(currentNode.getLeft(), binaryNode));
        }

        if (currentNode.getValue() < binaryNode.getValue()) {
            currentNode.setRight(placeInTree(currentNode.getRight(), binaryNode));
        }

        //TODO condition to maintain count of duplicate keys

        currentNode.setHeight();

        return performNeededRotations(currentNode, binaryNode);

    }

    private BinaryNode performNeededRotations(BinaryNode currentNode, BinaryNode binaryNode) {
        int balance = currentNode.getLeft().getHeight() - currentNode.getRight().getHeight();

        if (balance > 1 && binaryNode.getValue() < currentNode.getLeft().getValue()) {
            return rotateRight(currentNode);
        }

        if (balance < -1 && binaryNode.getValue() > currentNode.getRight().getValue()) {
            return rotateLeft(currentNode);
        }

        // Left Right Case
        if (balance > 1 && binaryNode.getValue() > currentNode.getLeft().getValue()) {
            currentNode.setLeft(rotateLeft(currentNode.getLeft()));
            return rotateRight(currentNode);
        }

        // Right Left Case
        if (balance < -1 && currentNode.getValue() < currentNode.getRight().getValue()) {
            currentNode.setRight(rotateRight(currentNode.getRight()));
            return rotateLeft(currentNode);
        }

        return currentNode;
    }

    public void delete(int value) {
        deleteNode(root, value);
    }

    private BinaryNode deleteNode(BinaryNode root, int value) {
        if (root == null)
            return root;

        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        if (value < root.getValue())
            root.setLeft(deleteNode(root.getLeft(), value));

            // If the key to be deleted is greater than the
            // root's key, then it lies in right subtree
        else if (value > root.getValue())
            root.setRight(deleteNode(root.getRight(), value));

            // if key is same as root's key, then this is the node
            // to be deleted
        else
        {

            // node with only one child or no child
            if ((root.getLeft() == null) || (root.getRight() == null))
            {
                BinaryNode temp = null;
                if (temp == root.getLeft())
                    temp = root.getRight();
                else
                    temp = root.getLeft();

                // No child case
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else   // One child case
                    root = temp; // Copy the contents of
                // the non-empty child
            }
            else
            {

                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                BinaryNode temp = minValueNode(root.getRight());

                // Copy the inorder successor's data to this node
                root.setValue(temp.getValue());

                // Delete the inorder successor
                root.setRight(deleteNode(root.getRight(), temp.getValue()));
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.setHeight();

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        //  this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.getLeft()) >= 0)
            return rotateRight(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.getLeft()) < 0)
        {
            root.setLeft(rotateLeft(root.getLeft()));
            return rotateRight(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.getRight()) <= 0)
            return rotateLeft(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.getRight()) > 0)
        {
            root.setRight(rotateRight(root.getRight()));
            return rotateLeft(root);
        }

        return root;

    }
    private int getBalance(BinaryNode N) {
        if (N == null)
            return 0;
        return N.getLeft().getHeight() - N.getRight().getHeight();
    }

    private BinaryNode minValueNode(BinaryNode node)
    {
        BinaryNode current = node;

        /* loop down to find the leftmost leaf */
        while (current.getLeft() != null)
            current = current.getLeft();
        return current;
    }

}
