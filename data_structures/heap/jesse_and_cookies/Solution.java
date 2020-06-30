package data_structures.heap.jesse_and_cookies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\data_structures\\heap\\jesse_and_cookies\\data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0].trim());

        int k = Integer.parseInt(nk[1].trim());

//        PriorityQueue<Integer> data = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toCollection(() -> new PriorityQueue<>(n)));

        String[] vs = scanner.nextLine().split(" ");

        PriorityQueue<Integer> data = new PriorityQueue<>();
        for(String s : vs) {
            data.add(Integer.parseInt(s));
        }

        int result = cookies(k, data);

        System.out.print(result);
    }

    static int cookies(int k, PriorityQueue<Integer> data) {
        int operations = 0;

        while (data.size() > 1) {
            Integer lowest = data.poll();
            Integer secondLowest = data.poll();

            if (lowest >= k) {
                return operations;
            }

            data.add(lowest + secondLowest * 2);
            operations++;
        }

        if(data.poll() < k) {
            return -1;
        }

        return operations;

    }
}
