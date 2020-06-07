package data_structures.trees.tree_top_view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}



public class Solution {

    static class NodeObj {
        Node node;

        public NodeObj(Node node, Integer position) {
            this.node = node;
            this.position = position;
        }

        Integer position;
    }

    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\data_structures\\trees\\tree_top_view\\data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void topView(Node root) {
        Map<Integer, Integer> topMost = new TreeMap<>();
        Queue<NodeObj> queue = new LinkedList<>();
        queue.add(new NodeObj(root, 0));

        while (!queue.isEmpty()) {
            NodeObj current = queue.poll();
            if(!topMost.containsKey(current.position)) {
                topMost.put(current.position, current.node.data);
            }

            if (current.node.left != null) {
                queue.add(new NodeObj(current.node.left, current.position -1));
            }
            if (current.node.right != null) {
                queue.add(new NodeObj(current.node.right, current.position +1));
            }
        }


        System.out.println(topMost.values().stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }


    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        int t = scanner.nextInt();
        Node root = null;
        while (t-- > 0) {
            int data = scanner.nextInt();
            root = insert(root, data);
        }
        scanner.close();
        topView(root);
    }
}
