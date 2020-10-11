package geeks_for_geeks.print_first_letter_of_every_word_in_the_string;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collector;

public class Solution {

   private static Scanner scanner;

   static {
      try {
         scanner = new Scanner(new File(
               "C:\\root\\src\\private\\HackerRank_solutions_java\\geeks_for_geeks\\print_first_letter_of_every_word_in_the_string\\data.txt"));
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
   }

   static String firstAlphabet(String S) {
      return Arrays.stream(S.split(" "))
            .map(s -> s.charAt(0))
            .collect(Collector.of(StringBuilder::new, StringBuilder::append, StringBuilder::append,
                  StringBuilder::toString));
   }

   public static void main(String[] args) throws IOException {

      String text = scanner.nextLine();

      String result = firstAlphabet(text);

      System.out.println(result);

      scanner.close();
   }

}
