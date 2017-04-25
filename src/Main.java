import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    private static final String filePath = "C:\\Users\\Kevin\\IdeaProjects\\AVLTree\\src\\input.txt";
    private static ArrayList<String> tokens;


    public static void main(String[] args) throws IOException {
        tokens = new ArrayList<>();
        readData();

        BinaryTree binaryTree = new BinaryTree();

        for (String string: tokens) {
            String[] temp = string.trim().split(" ");

            if (temp[0].equals("+")) {
                binaryTree.addToTree(Integer.parseInt(temp[1]));
            } else if (temp[0].equals("-")) {
                binaryTree.removeFromTree(Integer.parseInt(temp[1]));
            } else {
                //System.err.println("Invalid entry:" + string);
            }
        }


        //binaryTree.printInorder("inorder");
        binaryTree.printInorder("preorder");

    }

    private static void readData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] temp = line.split(",");
            fillTokens(temp);
        }
    }

    private static void fillTokens(String[] temp) {
        tokens.addAll(Arrays.asList(temp));
    }
}
