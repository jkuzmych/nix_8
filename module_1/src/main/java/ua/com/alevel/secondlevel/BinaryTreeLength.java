package ua.com.alevel.secondlevel;

import java.io.BufferedReader;
import java.io.IOException;

public class BinaryTreeLength {
    public void run(BufferedReader reader) throws IOException {
        while (true) {
            System.out.println();
            String src;
            System.out.println("Enter the binary tree->");
            src = reader.readLine();
            String[] array = src.split(" ");
            ;
            Node tree = new Node(0);
            for (int i = 0; i < array.length; i++) {
                setTree(Integer.parseInt(array[i]), tree);
            }
            getTree(tree);
            System.out.println(tree);
            System.out.println("Tree depth is " + maxDepth(tree));
            System.out.println("Select 0 to break");
            src = reader.readLine();
            if (src.compareTo("0") == 0 || src.compareTo("q") == 0) break;
        }
    }

    void setTree(int x, Node tree) {
        if (tree == null) {
            tree = new Node(x);
            tree.value = x;
            tree.left = null;
            tree.right = null;
        }
        if (x < tree.value) {
            if (tree.left != null) setTree(x, tree.left);
            else {
                tree.left = new Node(x);
                tree.left.left = null;
                tree.left.right = null;
            }
        }
        if (x > tree.value) {
            if (tree.right != null) setTree(x, tree.right);
            else {
                tree.right = new Node(x);
                tree.right.left = null;
                tree.right.right = null;
            }
        }
    }

    void getTree(Node tree) {
        if (tree.left != null && tree.right != null) {
            getTree(tree.left);
            System.out.print(tree.value + " ");
            getTree(tree.left);
        }
    }

    public int maxDepth(Node root) {
        if (root == null)
            return 0;

        int nleft = maxDepth(root.left);
        int nright = maxDepth(root.right);

        return nleft > nright ? nleft + 1 : nright + 1;
    }

    class Node {
        int value;
        Node left, right;

        Node(int v) {
            value = v;
            left = right = null;
        }
    }
}