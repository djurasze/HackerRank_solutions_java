package interview_preparation_kit.search.minimum_time_required;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Solution {

    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\interview_preparation_kit\\search\\minimum_time_required\\data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static long minTime(long[] machines, long goal) {
        long slowest = findSlowestMachine(machines);
        long fastest = findFastestMachine(machines);
        long lowerBound = findBound(fastest, goal, machines.length);
        long upperBound = findBound(slowest, goal, machines.length);
        return binarySearch(lowerBound, upperBound, machines, goal);
    }

    private static long binarySearch(long lowerBound, long upperBound, long[] machines, long goal) {
        if(lowerBound + 1 == upperBound) {
            long time = calculateTime(lowerBound, machines);
            return time >= goal? lowerBound: upperBound;
        }
        long mid = calculateMiddleValue(lowerBound, upperBound);
        long time = calculateTime(mid, machines);
        return time >= goal? binarySearch(lowerBound, mid, machines, goal): binarySearch(mid, upperBound, machines, goal);
    }

    private static long calculateMiddleValue(long lowerBound, long upperBound) {
        return (long) (lowerBound + Math.floor((double) (upperBound - lowerBound)/2));
    }

    private static long calculateTime(long days, long[] machines) {
        long result = 0;
        for(long machineEfficiency: machines) {
            result += Math.floor((double) days/machineEfficiency);
        }
        return result;
    }

    private static long findBound(long speed, long goal, int numberOfMachines) {
        return (long) (Math.ceil((double) goal/numberOfMachines) * speed);
    }

    private static long findFastestMachine(long[] machines) {
        return Arrays.stream(machines).min().orElseThrow(() -> new RuntimeException("No fastest machine found!"));
    }

    private static long findSlowestMachine(long[] machines) {
        return Arrays.stream(machines).max().orElseThrow(() -> new RuntimeException("No slowest machine found!"));
    }


    public static void main(String[] args) throws IOException {

        String[] nGoal = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nGoal[0]);

        long goal = Long.parseLong(nGoal[1]);

        long[] machines = new long[n];

        String[] machinesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long machinesItem = Long.parseLong(machinesItems[i]);
            machines[i] = machinesItem;
        }

        long ans = minTime(machines, goal);

        System.out.println(ans);

        scanner.close();
    }


}
