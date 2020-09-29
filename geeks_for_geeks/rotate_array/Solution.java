package geeks_for_geeks.rotate_array;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {

   private static Scanner scanner;

   static {
      try {
         scanner = new Scanner(
               new File("C:\\root\\src\\private\\HackerRank_solutions_java\\geeks_for_geeks\\rotate_array\\data.txt"));
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
         Integer rotation = Integer.parseInt(scanner.nextLine()
               .split(" ")[1]);

         Integer[] data = Arrays.stream(scanner.nextLine()
               .split(" "))
               .map(Integer::parseInt)
               .collect(Collectors.toList())
               .toArray(Integer[]::new);

         rotateArrayUsingJugglingAlg(data, rotation);

         String resultString = Arrays.stream(data)
               .map(Object::toString)
               .collect(Collectors.joining(" "));
         System.out.println(resultString);
      }

      scanner.close();
   }

   private static void rotateArrayWithTempArray(Integer[] data, Integer rotation) {
      Integer[] tempArray = new Integer[rotation];

      System.arraycopy(data, 0, tempArray, 0, rotation);

      for (int i = rotation; i < data.length; i++) {
         int oldPosition = i;
         int newPosition = i - rotation;
         data[newPosition] = data[oldPosition];
      }

      int endIndex = data.length - rotation;

      System.arraycopy(tempArray, 0, data, endIndex, rotation);

   }

   private static void rotateArrayOneByOne(Integer[] data, Integer rotation) {
      for (int i = 0; i < rotation; i++) {
         int tmp = data[0];
         System.arraycopy(data, 1, data, 0, data.length - 1);
         data[data.length - 1] = tmp;
      }
   }

   private static void rotateArrayUsingJugglingAlg(Integer[] data, Integer rotation) {
      int numberOfCycles = findGCD(data.length, rotation);

      for (int i = 0; i < numberOfCycles; i++) {
         int tmp = data[i];
         int current = i;
         int prev = (i + rotation) % data.length;
         while(prev != i) {
            data[current] = data[prev];
            int tmp1 = current;
            current = prev;
            prev = (tmp1 + rotation) % data.length;
         }
         data[current] = tmp;
      }
   }

   private static int findGCD(int n1, int n2) {
      if(n2 == 0) {
         return n1;
      }
      return findGCD(n2, n1 % n2);
   }

}
