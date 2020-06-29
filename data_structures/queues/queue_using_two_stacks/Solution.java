package data_structures.queues.queue_using_two_stacks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\data_structures\\queues\\queue_using_two_stacks\\data.txt"));
//            scanner = new Scanner(System.in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int numOfOperations = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        List<String[]> operations = IntStream.range(0, numOfOperations).mapToObj(value -> scanner.nextLine().split(" ")).collect(Collectors.toList());
        CustomQueue<Integer> queue = new CustomQueue<>();
        operations.forEach(op -> execute(op, queue));
    }


    private static final Map<String, BiConsumer<String[], CustomQueue<Integer>>> operations = new HashMap<>();

    static {
        operations.put("1", (op, queue) -> queue.enqueue(Integer.valueOf(op[1])));
        operations.put("2", (op, queue) -> queue.dequeue());
        operations.put("3", (op, queue) -> System.out.println(queue.get()));
    }


    private static void execute(String[] op, CustomQueue<Integer> queue) {
        operations.get(op[0]).accept(op, queue);
    }

    static private class CustomQueue<T> {

        private Stack<T> firstStack = new Stack<>();
        private Stack<T> secondStack = new Stack<>();
//        private Queue<T> queue = new LinkedList<>();

        public void enqueue(T newEl) {
            firstStack.push(newEl);
//            queue.add(newEl);
        }

        public T dequeue() {
            get();
            return secondStack.pop();

//            return queue.poll();
        }

        public T get() {
            if(secondStack.empty()){
                while (!firstStack.empty()){
                    secondStack.push(firstStack.pop());
                }
            }
            return secondStack.peek();
        }
    }

}
