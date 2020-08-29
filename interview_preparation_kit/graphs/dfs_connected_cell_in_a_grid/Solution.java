package interview_preparation_kit.graphs.dfs_connected_cell_in_a_grid;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution {

    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\interview_preparation_kit\\graphs\\dfs_connected_cell_in_a_grid\\data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] gridRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int gridItem = Integer.parseInt(gridRowItems[j]);
                grid[i][j] = gridItem;
            }
        }

        int res = maxRegion(grid);
        System.out.println(res);
        scanner.close();
    }

    // Complete the maxRegion function below.
    static int maxRegion(int[][] grid) {
        Set<Map.Entry<Integer, Integer>> visited = new HashSet<>();
        int max = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                Map.Entry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(i, j);
                if(!visited.contains(entry)){
                    if(grid[i][j] == 0) {
                        visited.add(entry);
                    }
                    int regionSize = calculateRegionSize(entry, grid, visited);
                    if(regionSize > max) {
                        max = regionSize;
                    }
                }
            }
        }
        return max;

    }

    static int calculateRegionSize(Map.Entry<Integer, Integer> entry, int[][] grid,Set<Map.Entry<Integer, Integer>> visited) {
        if(visited.contains(entry)) {
            return 0;
        }
        visited.add(entry);
        Set<Map.Entry<Integer, Integer>> neighbours = getNeighbours(entry, grid, visited);
        int neighboursSize = neighbours.stream().map(neighbour -> {
            return calculateRegionSize(neighbour, grid, visited);
        }).reduce(0, Integer::sum);

        return 1 + neighboursSize;
    }

    static Set<Map.Entry<Integer, Integer>> getNeighbours(Map.Entry<Integer, Integer> entry, int[][] grid,Set<Map.Entry<Integer, Integer>> visited) {
        Set<Map.Entry<Integer, Integer>> neighbours = new HashSet<>();
        int x = entry.getKey();
        int y = entry.getValue();
        for(int i = x-1; i <= x+1; i++) {
            for(int j = y-1; j <= y+1; j++) {
                if(i != x || j != y) {
                    if(i >= 0 && i < grid.length) {
                        if(j >= 0 && j < grid[i].length) {
                            if(grid[i][j] == 1) {
                                Map.Entry<Integer, Integer> neighbour = new AbstractMap.SimpleEntry<>(i, j);
                                if(!visited.contains(neighbour)) {
                                    neighbours.add(neighbour);
                                }
                            }
                        }
                    }
                }

            }
        }
        return neighbours;
    }
}
