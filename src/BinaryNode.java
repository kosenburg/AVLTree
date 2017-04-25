/**
 * Created by Kevin on 4/21/2017.
 */
public class BinaryNode {
    private BinaryNode left;
    private BinaryNode right;
    private int value;
    private int duplicateCount;
    private int height;

    public BinaryNode(BinaryNode left, int value, BinaryNode right) {
        setLeft(left);
        setRight(right);
        setValue(value);
        setHeight(1);
    }

    public int getDuplicateCount() {
        return duplicateCount;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void incrementCount() {
        duplicateCount++;
    }

    public int getHeight() {
        return height;
    }

    public BinaryNode(int value) {
        this(null,value,null);
    }

    public BinaryNode getRight() {
        return right;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public int getValue() {
        return value;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
