package algorithms.implementation.strange_counter;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the strangeCounter function below.
    static long strangeCounter(long t) {
        long time = 0;
        long cycleStart;
        for(cycleStart=3; time < t; cycleStart=cycleStart*2) {
            time += cycleStart;
        }
        cycleStart /= 2;
        time = time - cycleStart;

        return cycleStart + 1 - (t - time);
    }

    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\algorithms\\implementation\\strange_counter\\data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        long t = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = strangeCounter(t);

        System.out.println(result);
        scanner.close();
    }
}
