package interview_preparation_kit.stacks_and_queues.a_tale_of_two_stacks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution {

    private static Scanner scanner;

    static {
        try {
            scanner = new Scanner(new File("D:\\src\\HackerRank_solutions_java\\interview_preparation_kit\\stacks_and_queues\\a_tale_of_two_stacks\\data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        MyQueue<Integer> queue = new MyQueue<Integer>();

        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scanner.nextInt();
            if (operation == 1) { // enqueue
                queue.enqueue(scanner.nextInt());
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }
        scanner.close();
    }

    private static class MyQueue<T> {

        private Stack<T> stackNewestOnTop = new Stack<>();
        private Stack<T> stackOldestOnTop = new Stack<>();

        public void enqueue(T nextInt) {
            stackNewestOnTop.push(nextInt);
        }

        public void dequeue() {
            prepareOldestOnTop();
            stackOldestOnTop.pop();
        }

        private void prepareOldestOnTop() {
            if(stackOldestOnTop.empty()) {
                while(!stackNewestOnTop.empty()) {
                    stackOldestOnTop.push(stackNewestOnTop.pop());
                }
            }
        }

        public T peek() {
            prepareOldestOnTop();
            return stackOldestOnTop.peek();
        }
    }
}
