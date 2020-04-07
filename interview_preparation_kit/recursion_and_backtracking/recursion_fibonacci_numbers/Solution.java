package interview_preparation_kit.recursion_and_backtracking.recursion_fibonacci_numbers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\interview_preparation_kit\\recursion_and_backtracking\\recursion_fibonacci_numbers\\data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        scanner.close();
        System.out.println(fibonacci(n));
    }


    public static int fibonacci(int n) {
        if(n == 0) {
            return 0;
        }
        if(n==1) {
            return 1;
        }
        List<Integer> mem = new ArrayList<>(n+1);
        mem.add(0, 0);
        mem.add(1, 1);
        return recFib(2, n, mem);
    }

    private static int recFib(int current, int gaol, List<Integer> mem) {
        if(current == gaol) {
            return mem.get(current-2)+mem.get(current-1);
        }
        mem.add(mem.get(current-2)+mem.get(current-1));
        return recFib(current+1, gaol, mem);
    }


}
