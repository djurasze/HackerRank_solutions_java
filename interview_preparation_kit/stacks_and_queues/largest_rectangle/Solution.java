package interview_preparation_kit.stacks_and_queues.largest_rectangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    // Complete the largestRectangle function below.
    static long largestRectangle(int[] h) {
        long result = 0;
        Map<Integer, Integer> current = new HashMap<>();
        for (int value : h) {
            Long max = current.entrySet().stream().filter(entry -> entry.getKey() > value).map(entry -> (long) entry.getKey() * entry.getValue()).max(Long::compareTo).orElse(0L);
            result = Math.max(max, result);
            Integer longestBigger = current.entrySet().stream().filter(entry -> entry.getKey() > value).map(Map.Entry::getValue).max(Integer::compareTo).orElse(0);
            current = current.entrySet().stream().filter(entry -> entry.getKey() <= value)
                    .collect(Collectors.toMap(Map.Entry::getKey, o -> o.getValue() + 1));
            current.putIfAbsent(value, longestBigger+1);
        }
        Long max = current.entrySet().stream().map(entry -> (long) entry.getKey() * entry.getValue()).max(Long::compareTo).orElse(0L);
        result = Math.max(max, result);
        return result;
    }


    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\interview_preparation_kit\\stacks_and_queues\\largest_rectangle\\data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] h = new int[n];

        String[] hItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int hItem = Integer.parseInt(hItems[i]);
            h[i] = hItem;
        }

        long result = largestRectangle(h);

        System.out.println(result);

        scanner.close();
    }
}
