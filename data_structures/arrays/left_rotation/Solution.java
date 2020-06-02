package data_structures.arrays.left_rotation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\data_structures\\arrays\\left_rotation\\data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int[] rotated = rotate(a, d);

        System.out.println(Arrays.stream(rotated).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        scanner.close();
    }

    private static int[] rotate(int[] data, int positions) {
        int[] result = new int[data.length];
        IntStream.range(0, data.length).forEach(currentIndex -> result[(currentIndex + data.length - positions) % data.length] = data[currentIndex]);
        return result;
    }
}
