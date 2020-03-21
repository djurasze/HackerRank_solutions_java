package algorithms.greedy.maximum_perimeter_triangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Solution {

    // Complete the strangeCounter function below.
    static int[] maximumPerimeterTriangle(int[] sticks) {
        Arrays.sort(sticks);
        for(int i = sticks.length-1; i >1; i--) {
            if(sticks[i-1] + sticks[i-2] > sticks[i]) {
                return new int[]{sticks[i-2], sticks[i-1], sticks[i]};
            }
        }
        return new int[]{-1};
    }


    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\algorithms\\greedy\\maximum_perimeter_triangle\\data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] sticks = new int[n];

        String[] sticksItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int sticksItem = Integer.parseInt(sticksItems[i]);
            sticks[i] = sticksItem;
        }

        int[] result = maximumPerimeterTriangle(sticks);

        System.out.println(Arrays.toString(result));
        scanner.close();
    }
}
