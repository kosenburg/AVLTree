/**
 * Created by Kevin on 4/21/2017.
 */
public class BinaryTree {
    private BinaryNode root;

    public BinaryTree() {

    }

    public void addToTree(int value) {
        if (root == null) {
            root = new BinaryNode(value);
        } else {
            placeInTree(root, new BinaryNode(value));
        }
    }

    private BinaryNode placeInTree(BinaryNode currentNode, BinaryNode binaryNode) {

        if (currentNode == null) {
            return binaryNode;
        }

        if (currentNode.getValue() > binaryNode.getValue()) {
            if (currentNode.getLeft() != null) {
                currentNode.setLeft(placeInTree(currentNode.getLeft(), binaryNode));
            }
        }

        if (currentNode.getValue() < binaryNode.getValue()) {
            if (currentNode.getRight() != null) {
                currentNode.setRight(placeInTree(currentNode.getRight(), binaryNode));
            }
        }

        currentNode.setHeight(1 + Math.max(currentNode.getLeft().getHeight(), currentNode.getRight().getHeight()));


        int balance = currentNode.getLeft().getHeight() - currentNode.getRight().getHeight();

        if (balance > 1 && binaryNode.getValue() < currentNode.getLeft().getValue()) {
            return rightRotate(currentNode);
        }

        if (balance < -1 && binaryNode.getValue() > currentNode.getRight().getValue()) {
            return leftRotate(currentNode);
        }

        // Left Right Case
        if (balance > 1 && binaryNode.getValue() > currentNode.getLeft().getValue()) {
            currentNode.setLeft(leftRotate(currentNode.getLeft()));
            return rightRotate(currentNode);
        }

        // Right Left Case
        if (balance < -1 && currentNode.getValue() < currentNode.getRight().getValue()) {
            currentNode.getRight(rightRotate(currentNode.getRight()));
            return leftRotate(currentNode);
        }



        return currentNode;
    }
}
