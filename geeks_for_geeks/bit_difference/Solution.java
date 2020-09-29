package geeks_for_geeks.bit_difference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {

   private static Scanner scanner;

   static {
      try {
         scanner = new Scanner(new File(
               "C:\\root\\src\\private\\HackerRank_solutions_java\\geeks_for_geeks\\bit_difference\\data.txt"));
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
   }

   public static void main(String[] args) throws IOException {

      String[] nGoal = scanner.nextLine()
            .split(" ");

      int numberOfCases = Integer.parseInt(nGoal[0]);
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < numberOfCases; i++) {
         String[] rawInput = scanner.nextLine()
               .split(" ");
         List<Integer> input = Arrays.stream(rawInput)
               .map(Integer::parseInt)
               .collect(Collectors.toList());
         int result = countBitDifference(input.get(0), input.get(1));
         System.out.println(result);
      }


      scanner.close();
   }

   private static int countBitDifference(Integer first, Integer second) {
      return Integer.bitCount(first ^ second);
   }

}
