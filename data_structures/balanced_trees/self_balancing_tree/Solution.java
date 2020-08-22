package data_structures.balanced_trees.self_balancing_tree;

public class Solution {

    static Node insert(Node root, int val) {
        if (root == null) {
            Node newLeaf = new Node();
            newLeaf.val = val;
            newLeaf.ht = 0;
            return newLeaf;
        }

        if (root.val <= val) {
            root.right = insert(root.right, val);
        } else {
            root.left = insert(root.left, val);
        }

        int bf = getBf(root);

        if (bf >= 2) {
            if (getHeight(root.left.left) < getHeight(root.left.right)) {
                //System.out.println("Left right case");
                root.left = rotateLeft(root.left);
                root = rotateRight(root);
            } else {
                //System.out.println("Left left case");
                root = rotateRight(root);
            }
        } else if (bf <= -2) {
            if (getHeight(root.right.left) > getHeight(root.right.right)) {
                //System.out.println("Right left case");
                root.right = rotateRight(root.right);
                root = rotateLeft(root);
            } else {
                //System.out.println("Right right case");
                root = rotateLeft(root);

            }
        } else {
            root.ht = calculateHeight(root);
        }


        return root;
    }

    static int getBf(Node root) {
        if (root == null) {
            throw new RuntimeException("You cannot get BF from null node");
        }
        return getHeight(root.left) - getHeight(root.right);
    }

    static int getHeight(Node root) {
        if (root == null) {
            return -1;
        }
        return root.ht;
    }

    static int calculateHeight(Node root) {
        if (root == null) {
            throw new RuntimeException("You cannot calculate height for null node");
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    static Node rotateRight(Node root) {
        Node newRoot = root.left;
        root.left = newRoot.right;

        newRoot.right = root;

        root.ht = calculateHeight(root);
        newRoot.ht = calculateHeight(newRoot);

        return newRoot;
    }

    static Node rotateLeft(Node root) {
        Node newRoot = root.right;

        root.right = newRoot.left;
        newRoot.left = root;


        root.ht = calculateHeight(root);
        newRoot.ht = calculateHeight(newRoot);

        return newRoot;
    }

    static class Node {
        int val;    //Value
        int ht;        //Height
        Node left;    //Left child
        Node right;    //Right child
    }
}
