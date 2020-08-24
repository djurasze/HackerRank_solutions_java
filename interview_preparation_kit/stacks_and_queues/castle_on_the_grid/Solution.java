package interview_preparation_kit.stacks_and_queues.castle_on_the_grid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    // Complete the minimumMoves function below.
    static int minimumMoves(String[] gridStr, int startX, int startY, int goalX, int goalY) {
        if(isWithoutObstacles(gridStr)) {
            if(startX == goalX && startY == goalY)
                return 0;
            if(startX == goalX || startY == goalY)
                return 1;
            return 2;
        }
        char[][] grid = convertGrid(gridStr);
        Queue<Map.Entry<Integer, Integer>> pointsToSearch = new LinkedList<>();
        AbstractMap.SimpleImmutableEntry<Integer, Integer> startPoint = new AbstractMap.SimpleImmutableEntry<>(startX, startY);
        pointsToSearch.add(startPoint);
        Map.Entry<Integer, Integer> goal = new AbstractMap.SimpleImmutableEntry<>(goalX, goalY);
        Map<Map.Entry<Integer, Integer>, Integer> distances = new HashMap<>();
        distances.put(startPoint, 0);
        Set<Map.Entry<Integer, Integer>> visitedPoints = new HashSet<>();

        while (!pointsToSearch.isEmpty()) {
            Map.Entry<Integer, Integer> current = pointsToSearch.poll();
            List<Map.Entry<Integer, Integer>> neighbours = getNeighbours(grid, current).stream().filter(neighbour -> !visitedPoints.contains(neighbour)).collect(Collectors.toList());
            neighbours.forEach(neighbour -> {
                Integer currentDistance = distances.get(current);
                if (!distances.containsKey(neighbour)) {
                    distances.put(neighbour, currentDistance + 1);
                } else if (currentDistance + 1 < distances.get(neighbour)) {
                    distances.put(neighbour, currentDistance + 1);
                }
            });
            neighbours.stream().filter(neighbour -> !pointsToSearch.contains(neighbour)).forEach(pointsToSearch::add);
            visitedPoints.add(current);

        }
        return distances.getOrDefault(goal, -1);
    }

    private static boolean isWithoutObstacles(String[] gridStr) {
        for(String row: gridStr) {
            if(row.contains("X")) {
                return false;
            }
        }
        return true;
    }

    private static List<Map.Entry<Integer, Integer>> getNeighbours(char[][] grid, Map.Entry<Integer, Integer> current) {
        List<Map.Entry<Integer, Integer>> neighbours = new ArrayList<>();

        for (int i = current.getKey() - 1; i >= 0; i--) {
            if (grid[i][current.getValue()] != 'X') {
                neighbours.add(new AbstractMap.SimpleImmutableEntry<>(i, current.getValue()));
            } else {
                break;
            }
        }
        for (int i = current.getKey() + 1; i < grid.length; i++) {
            if (grid[i][current.getValue()] != 'X') {
                neighbours.add(new AbstractMap.SimpleImmutableEntry<>(i, current.getValue()));
            } else {
                break;
            }
        }
        for (int i = current.getValue() - 1; i >= 0; i--) {
            if (grid[current.getKey()][i] != 'X') {
                neighbours.add(new AbstractMap.SimpleImmutableEntry<>(current.getKey(), i));
            } else {
                break;
            }
        }
        for (int i = current.getValue() + 1; i < grid.length; i++) {
            if (grid[current.getKey()][i] != 'X') {
                neighbours.add(new AbstractMap.SimpleImmutableEntry<>(current.getKey(), i));
            } else {
                break;
            }
        }
        return neighbours;
    }

    private static char[][] convertGrid(String[] gridStr) {
        char[][] grid = new char[gridStr.length][];
        for (int col = 0; col < grid.length; col++) {
            grid[col] = gridStr[col].toCharArray();
        }
        return grid;
    }


    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\interview_preparation_kit\\stacks_and_queues\\castle_on_the_grid\\data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        String[] startXStartY = scanner.nextLine().split(" ");

        int startX = Integer.parseInt(startXStartY[0]);

        int startY = Integer.parseInt(startXStartY[1]);

        int goalX = Integer.parseInt(startXStartY[2]);

        int goalY = Integer.parseInt(startXStartY[3]);

        int result = minimumMoves(grid, startX, startY, goalX, goalY);

        System.out.println(result);
        scanner.close();
    }
}
