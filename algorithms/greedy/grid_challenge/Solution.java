package algorithms.greedy.grid_challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    static String gridChallenge(String[] grid) {

        char[][] sorted = sortRows(grid);
        Boolean isSorted = checkColumns(sorted);
        return isSorted? "YES":"NO";

    }

    private static Boolean checkColumns(char[][] grid) {
        for(int i=0; i < grid[0].length; i++) {
            for(int j=0; j<grid.length-1; j++) {
                if(grid[j][i] > grid[j+1][i]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static char[][] sortRows(String[] grid) {
        char[][] sorted = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            char[] rowAsArray = grid[i].toCharArray();
            Arrays.sort(rowAsArray);
            sorted[i] = rowAsArray;
        }
        return sorted;
    }


    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\algorithms\\greedy\\grid_challenge\\data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String[] grid = new String[n];

            for (int i = 0; i < n; i++) {
                String gridItem = scanner.nextLine();
                grid[i] = gridItem;
            }

            String result = gridChallenge(grid);
            System.out.println(result);
        }

        scanner.close();
    }
}
