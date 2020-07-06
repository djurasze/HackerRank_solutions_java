package data_structures.heap.qheap1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.BiConsumer;

public class Solution {

    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\data_structures\\heap\\qheap1\\data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);


        List<String[]> operations = new ArrayList<>();

        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String[] operation = scanner.nextLine().split(" ");
            operations.add(operation);
        }

        Map<Integer, BiConsumer<PriorityQueue<Integer>, String[]>> methods = init();

        calculate(operations, methods, new PriorityQueue<>());
        scanner.close();
    }

    private static void calculate(List<String[]> operations, Map<Integer, BiConsumer<PriorityQueue<Integer>, String[]>> methods, PriorityQueue<Integer> objects) {
        operations.forEach(args -> {
            BiConsumer<PriorityQueue<Integer>, String[]> method = methods.get(Integer.valueOf(args[0]));
            method.accept(objects, args);
        });
    }

    private static Map<Integer, BiConsumer<PriorityQueue<Integer>, String[]>> init() {
        Map<Integer, BiConsumer<PriorityQueue<Integer>, String[]>> methods = new HashMap<>();
        methods.put(1, (heap, args) -> heap.add(Integer.valueOf(args[1])));
        methods.put(2, (heap, args) -> heap.remove(Integer.valueOf(args[1])));
        methods.put(3, (heap, args) -> System.out.println(heap.peek()));
        return methods;
    }

}
