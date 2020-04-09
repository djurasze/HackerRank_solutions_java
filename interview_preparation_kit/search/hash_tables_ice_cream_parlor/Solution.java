package interview_preparation_kit.search.hash_tables_ice_cream_parlor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Solution {

    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\interview_preparation_kit\\search\\hash_tables_ice_cream_parlor\\data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void whatFlavors(int[] cost, int money) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < cost.length; i++) {
            int currentPrice = cost[i];
            if(currentPrice < money) {
                Integer lackingAmount = money - currentPrice;
                if (map.get(lackingAmount) == null) {
                    map.put(currentPrice, i+1);
                } else {
                    System.out.println(String.format("%s %s", map.get(lackingAmount), i+1));
                }
            }
        }
    }

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int money = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] cost = new int[n];

            String[] costItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int costItem = Integer.parseInt(costItems[i]);
                cost[i] = costItem;
            }

            whatFlavors(cost, money);
        }

        scanner.close();
    }


}
