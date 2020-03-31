package interview_preparation_kit.recursion_and_backtracking.recursion_davis_staircase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution {

    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\interview_preparation_kit\\recursion_and_backtracking\\recursion_davis_staircase\\data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        int s = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int sItr = 0; sItr < s; sItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int res = stepPerms(n);

            System.out.println(res);
        }


        scanner.close();
    }


    private static int stepPerms(int n) {
//        return recursiveStepMemo(n, new HashMap<>());
        List<Integer> subProblems = new ArrayList<>(n+1);
        subProblems.add(0, 0);
        subProblems.add(1, 1);
        subProblems.add(2, 2);
        subProblems.add(3, 4);
        return n > 3 ? recursiveStepBottomUp(4, n, subProblems): subProblems.get(n);
    }

    private static int recursiveStepMemo(int n, Map<Integer, Integer> memo) {
        if(n < 0) {
            return 0;
        }
        else if (n == 0) {
           return 1;
        }
        if(memo.containsKey(n)) {
            return memo.get(n);
        }
        int result = recursiveStepMemo(n-1, memo) + recursiveStepMemo(n-2, memo) + recursiveStepMemo(n-3, memo);
        memo.put(n, result);
        return result;
    }
    private static int recursiveStepBottomUp(int pos, int n, List<Integer> subProblems) {
        subProblems.add(pos, subProblems.get(pos - 1) + subProblems.get(pos - 2) + subProblems.get(pos - 3));

        if(pos == n) {
            return subProblems.get(pos);
        }

        return recursiveStepBottomUp(pos+1, n, subProblems);
    }


}
