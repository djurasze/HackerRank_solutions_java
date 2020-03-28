package interview_preparation_kit.stacks_and_queues.balanced_brackets;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    private final static Map<Character, Character> BRACKETS = Stream.of(new Character[][] {
            { '{', '}' },
            { '(', ')' },
            { '[', ']' }
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    static String isBalanced(String s) {
        Stack<Character> queue = new Stack<>();


        for(Character bracket: s.toCharArray()){
            if(isOpenBracket(bracket)) {
                queue.add(bracket);
            } else {
                if (queue.empty()) {
                    return "NO";
                }
                Character prev = queue.pop();
                if(!isBracketMatch(prev, bracket)) {
                    return "NO";
                }
            }
        }

        return queue.isEmpty()? "YES": "NO";
    }

    private static boolean isOpenBracket(Character bracket) {
        return BRACKETS.containsKey(bracket);
    }

    private static boolean isBracketMatch(Character openBracket, Character closingBracket) {
        return BRACKETS.get(openBracket).equals(closingBracket);
    }


    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\interview_preparation_kit\\stacks_and_queues\\balanced_brackets\\data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String s = scanner.nextLine();

            String result = isBalanced(s);

            System.out.println(result);
        }

        scanner.close();
    }
}
