package algorithms.implementation.larrys_array;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

    // Complete the larrysArray function below.
    static String larrysArray(int[] A) {
        boolean result = sortArray(A, 0, A.length);
        return result? "YES": "NO";
    }

    private static boolean sortArray(int[] a, int start, int end) {
        if(start + 1 == end) {
            return true;
        }
        int minPos = findMinPos(a, start, end);
        boolean result = moveMinToLeft(a, start, end, minPos);
        if(!result) {
            return false;
        }
        return sortArray(a, start+1, end);
    }

    private static boolean moveMinToLeft(int[] a, int start, int end, int minPos) {
        if(minPos == start) {
            return true;
        }
//        we are not at the last position
        if(minPos + 1 != end) {
            switchAtMid(a, minPos);
            return moveMinToLeft(a, start, end, minPos-1);
        } else { //we are at the last pos
            if(start <= minPos - 2) { //there is space for right switch
                switchAtRight(a, minPos);
                return moveMinToLeft(a, start, end, minPos-1);
            } else {
                return false;
            }
        }
    }

    private static void switchAtRight(int[] a, int minPos) {
        int left = a[minPos-1];
        int mid = a[minPos];
        int right = a[minPos-2];
        a[minPos-2] = left;
        a[minPos-1] = mid;
        a[minPos] = right;
    }

    private static void switchAtMid(int[] a, int minPos) {
        int left = a[minPos];
        int mid = a[minPos+1];
        int right = a[minPos-1];
        a[minPos-1] = left;
        a[minPos] = mid;
        a[minPos+1] = right;
    }

    private static int findMinPos(int[] a, int start, int end) {
        int min = a[start];
        int minPos = start;
        for(int i=start; i < end; i++) {
            if(a[i] < min) {
                minPos = i;
                min = a[i];
            }
        }
        return minPos;
    }

    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\algorithms\\implementation\\larrys_array\\data.txt"));
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

            int[] A = new int[n];

            String[] AItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int AItem = Integer.parseInt(AItems[i]);
                A[i] = AItem;
            }

            String result = larrysArray(A);

            System.out.println(result);
        }
        scanner.close();
    }
}
