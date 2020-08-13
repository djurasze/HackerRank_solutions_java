package interview_preparation_kit.search.swap_nodes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\interview_preparation_kit\\search\\swap_nodes\\data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /*
     * Complete the swapNodes function below.
     */
    static int[][] swapNodes(int[][] indexes, int[] queries) {
//        System.out.println("indexes = " + Arrays.deepToString(indexes) + ", queries = " + Arrays.toString(queries));
        BinaryTree binaryTree = new BinaryTree(indexes);
//        System.out.println(binaryTree);
//        System.out.println(binaryTree.getTreeHeight());
        int treeHeight = binaryTree.getTreeHeight();
        int[][] result = new int[queries.length][];

        for(int i = 0; i < queries.length; i++) {
            int query = queries[i];
            int step = query;
            while(query <= treeHeight) {
                binaryTree.swapNodes(query);
                query = query + step;
            }
            result[i] = binaryTree.print();
//            System.out.println(binaryTree);
        }


        return result;

    }


    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] indexes = new int[n][2];

        for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
            String[] indexesRowItems = scanner.nextLine().split(" ");

            for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
                int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
                indexes[indexesRowItr][indexesColumnItr] = indexesItem;
            }
        }

        int queriesCount = Integer.parseInt(scanner.nextLine().trim());

        int[] queries = new int[queriesCount];

        for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
            int queriesItem = Integer.parseInt(scanner.nextLine().trim());
            queries[queriesItr] = queriesItem;
        }

        int[][] result = swapNodes(indexes, queries);

        System.out.println(Arrays.deepToString(result));
    }


}

class BinaryTree {
    private Node root;

    public BinaryTree(int[][] indexes) {
        root = new Node(1);
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        for (int[] index : indexes) {
            Node left = index[0] != -1 ? new Node(index[0]) : null;
            Node right = index[1] != -1 ? new Node(index[1]) : null;
            Node current = nodeQueue.poll();
            if (current == null) {
                break;
            }
            current.setLeft(left);
            current.setRight(right);
            if (left != null)
                nodeQueue.add(left);
            if (right != null)
                nodeQueue.add(right);
        }
    }

    @Override
    public String toString() {
        return root.toString().trim();
    }

    public int[] print() {
        return root.print().stream().mapToInt(i->i).toArray();
    }

    public int getTreeHeight() {
        return root.getHeight(1);
    }

    public List<Node> getNodesOnLevel(int level) {
        return root.getNodesOnLevel(level, 1).collect(Collectors.toList());
    }

    public void swapNodes(int level) {
        List<Node> nodesOnLevel = getNodesOnLevel(level);
        for (Node node: nodesOnLevel) {
            Node left = node.left;
            Node right = node.right;
            node.setLeft(right);
            node.setRight(left);
        }
    }

    static class Node {
        private int id;
        private Node left;
        private Node right;

        @Override
        public String toString() {
            String left = getLeft().map(Node::toString).orElse("").trim();
            String right = getRight().map(Node::toString).orElse("").trim();
            return left + " " + id + " " + right;
        }

        public List<Integer> print() {
            List<Integer> left = getLeft().map(Node::print).orElse(new ArrayList<>());
            List<Integer> right = getRight().map(Node::print).orElse(new ArrayList<>());
            left.add(id);
            left.addAll(right);
            return left;
        }

        public Node(int id) {
            this.id = id;
        }

        public Optional<Node> getLeft() {
            return Optional.ofNullable(left);
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Optional<Node> getRight() {
            return Optional.ofNullable(right);
        }

        public void setRight(Node right) {
            this.right = right;
        }

        private Stream<Node> getNodesOnLevel(int level, int currentLevel) {
            if (level == currentLevel) {
                return Stream.of(this);
            } else {
                return Stream.of(getLeft().map(node -> node.getNodesOnLevel(level, currentLevel + 1)).orElse(Stream.empty()),
                        getRight().map(node -> node.getNodesOnLevel(level, currentLevel + 1)).orElse(Stream.empty()))
                        .flatMap(nodeStream -> nodeStream);
            }
        }

        private int getHeight(int current) {
            return Math.max(getLeft().map(node -> node.getHeight(current + 1)).orElse(current),
                    getRight().map(node -> node.getHeight(current + 1)).orElse(current));
        }


    }
}
