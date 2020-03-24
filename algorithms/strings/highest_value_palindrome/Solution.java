package algorithms.strings.highest_value_palindrome;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    // Complete the highestValuePalindrome function below.
    static String highestValuePalindrome(String inputText, int n, int movesAmount) {
        if(movesAmount >= inputText.length()) {
            return getMaxValue(inputText);
        }

        char[] analyzedText = inputText.toCharArray();
        List<Integer> skipped = new LinkedList<>();

        int madeMovesAmount = changeToPalindrome(analyzedText, skipped);
        movesAmount = movesAmount - madeMovesAmount;
        if(movesAmount < 0) {
            return "-1";
        }

        if(movesAmount > 0) {
            maximizeNumber(analyzedText, skipped, movesAmount);
        }

        return new String(analyzedText);
    }

    private static void maximizeNumber(char[] analyzedText, List<Integer> skipped, int movesAmount) {
        for(int position=0; position < movesAmount; position++) {
            if(position >= analyzedText.length) {
                break;
            }
            if(analyzedText[position] != '9' && !skipped.contains(position)) {
                analyzedText[position] = '9';
                analyzedText[analyzedText.length-1-position] = '9';
            } else if(analyzedText[position] == '9'){
                movesAmount++;
            } else {
                if(position + 1 == movesAmount) {
                    movesAmount++;
                }else {
                    analyzedText[position] = '9';
                    analyzedText[analyzedText.length-1-position] = '9';
                    movesAmount--;
                }
            }
        }
    }

    private static int changeToPalindrome(char[] analyzedText, List<Integer> skipped) {
        int madeMovesAmount = 0;
        int position = 0;
        while(position < analyzedText.length/2) {
            char left = analyzedText[position];
            char right = analyzedText[analyzedText.length-1-position];
            if(left != right) {
                char larger = left > right? left: right;
                analyzedText[position] = larger;
                analyzedText[analyzedText.length-1-position]=larger;
                madeMovesAmount++;
            } else {
                skipped.add(position);
            }
            position++;
        }
        return madeMovesAmount;
    }

    private static String getMaxValue(String s) {
        return new String(new char[s.length()]).replace("\0", "9");
    }


    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\algorithms\\strings\\highest_value_palindrome\\data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String s = scanner.nextLine();

        String result = highestValuePalindrome(s, n, k);

        System.out.println(result);

        scanner.close();
    }
}
