package geeks_for_geeks.number_of_paths;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class Solution {

   public static void main(String[] args) {
      Solution solution = new Solution();
      System.out.println(solution.numberOfPaths(12, 30));
   }

   private Map<Map.Entry<Integer, Integer>, Long> memo = new HashMap<>();

   long numberOfPaths(int m, int n) {
      if (m == 1 || n == 1) {
         return 1;
      }
      AbstractMap.SimpleImmutableEntry<Integer, Integer> key = new AbstractMap.SimpleImmutableEntry<>(m, n);
      Long value = memo.get(key);
      if (value != null) {
         return value;
      }
      value = numberOfPaths(m - 1, n) + numberOfPaths(m, n - 1);
      memo.put(key, value);
      return value;
   }
}
