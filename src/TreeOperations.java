/**
 * Created by Kevin on 4/25/2017.
 */
public class TreeOperations {

    private static int getHeight(BinaryNode node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }

    private static BinaryNode setHeight(BinaryNode node) {
        int tempHeight = 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
        node.setHeight(tempHeight);
        return node;
    }

    private static int getBalance(BinaryNode node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }

    public static BinaryNode insert(BinaryNode node, int key) {

        if (node == null) {
            return new BinaryNode(key);
        }

        if (node.getValue() > key) {
            node.setLeft(insert(node.getLeft(), key));
        } else if (node.getValue() < key) {
            node.setRight(insert(node.getRight(), key));
        } else {
            node.incrementCount();
            return node;
        }

        node = setHeight(node);
        node = performRotations(node, key);

        return node;
    }

    private static BinaryNode performRotations(BinaryNode node, int key) {
        int balance = getBalance(node);

        if (balance > 1 && key < node.getLeft().getValue()) {
            System.out.println("Right rotation");
            return rotateRight(node);
        }

        if (balance < -1 && key > node.getRight().getValue()) {
            System.out.println("Left rotation");
            return rotateLeft(node);
        }

        if (balance > 1 && key > node.getLeft().getValue()) {
            System.out.println("Left-Right rotation");
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }

        if (balance < -1 && key < node.getRight().getValue()) {
            System.out.println("Right-Left rotation");
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }

        return node;
    }

    private static BinaryNode rotateLeft(BinaryNode node) {
        BinaryNode temp1 = node.getRight();
        BinaryNode temp2 = temp1.getLeft();

        temp1.setLeft(node);
        node.setRight(temp2);

        setHeight(node);
        setHeight(temp1);


        return temp1;
    }

    private static BinaryNode rotateRight(BinaryNode node) {
        BinaryNode temp1 = node.getLeft();
        BinaryNode temp2 = temp1.getRight();

        temp1.setRight(node);
        node.setLeft(temp2);

        setHeight(node);
        setHeight(temp1);

        return temp1;
    }

    private static BinaryNode getMinimum(BinaryNode node) {
        BinaryNode currentNode = node;

        while (currentNode.getLeft() != null) {
            currentNode = currentNode.getLeft();
        }
        return currentNode;
    }

    public static BinaryNode delete(BinaryNode node, int key) {
        if (node == null) {
            return node;
        }

        if (node.getValue() > key) {
            node.setLeft(delete(node.getLeft(), key));
        } else if (node.getValue() < key) {
            node.setRight(delete(node.getRight(), key));
        } else {
            if ((node.getLeft() == null) || (node.getRight() == null)) {
                BinaryNode temp = null;
                if (temp == node.getLeft()) {
                    temp = node.getRight();
                } else {
                    temp = node.getLeft();
                }

                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                BinaryNode temp = getMinimum(node.getRight());
                node.setValue(temp.getValue());
                node.setRight(delete(node.getRight(), temp.getValue()));
            }
        }

        if (node == null) {
            return node;
        }

        setHeight(node);
        node = performDeleteRotations(node, key);
        return node;
    }

    private static BinaryNode performDeleteRotations(BinaryNode node, int key) {
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.getLeft()) >= 0){
            System.out.println("Right rotation");
            return rotateRight(node);
        }

        if (balance > 1 && getBalance(node.getLeft()) < 0) {
            System.out.println("Left-Right rotation");
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }

        if (balance < -1 && getBalance(node.getRight()) <= 0) {
            System.out.println("Left rotation");
            return rotateLeft(node);
        }

        if (balance < -1 && getBalance(node.getRight()) > 0) {
            System.out.println("Right-Left rotation");
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }

        return node;
    }
}