package interview_preparation_kit.miscellaneous.time_complexity_primality;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Solution {

    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\interview_preparation_kit\\miscellaneous\\time_complexity_primality\\data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

//    static String primality(int n) {
//        return BigInteger.valueOf(n).isProbablePrime(1)? "Prime": "Not prime";
//    }

    static String primality(int n) {
        if(n == 1 || n == 0) {
            return "Not prime";
        }
        if(n == 2) {
            return "Prime";
        }

        int limit = (int) Math.ceil(Math.sqrt(n));

        for(int i= 2; i <= limit; i++) {
            if(n%i == 0) {
                return "Not prime";
            }
        }

        return "Prime";
    }



    public static void main(String[] args) throws IOException {

        int p = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int pItr = 0; pItr < p; pItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String result = primality(n);
            System.out.println(result);
        }


        scanner.close();
    }


}
