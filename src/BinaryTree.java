
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
            root = TreeOperations.insert(root, value);
        }
    }

    public void removeFromTree(int value) {
        if (root == null) {
            System.out.println("No root set.");
        } else {
            root = TreeOperations.delete(root,value);
        }
    }

    public void printInorder(String order) {
        if (root == null) {
            System.out.println("No root set.");
        } else {
            if (order.toLowerCase().equals("inorder")) {
                inorderTraverse(root);

            } else if (order.toLowerCase().equals("preorder")) {
                preorderTraverse(root, 0);
            }
        }
    }

    private void preorderTraverse(BinaryNode node, int depth) {
        String line = "";
        for (int i = 0; i < depth; i++) {
            line += "\t";
        }

        System.out.println(line + node.getValue() + " " + node.getDuplicateCount());

        if (node.getLeft() != null) {
            preorderTraverse(node.getLeft(), depth + 1);
        }

        if (node.getRight() != null) {
            preorderTraverse(node.getRight(), depth + 1);
        }
    }

    private void inorderTraverse(BinaryNode node) {
        if (node.getLeft() != null) {
            inorderTraverse(node.getLeft());
        }

        System.out.println(node.getValue() + " " + node.getDuplicateCount());

        if (node.getRight() != null) {
            inorderTraverse(node.getRight());
        }
    }

}
