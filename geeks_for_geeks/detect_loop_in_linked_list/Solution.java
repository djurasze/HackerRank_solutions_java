package geeks_for_geeks.detect_loop_in_linked_list;


class Node {
   int data;
   Node next;

   public Node(int data) {
      this.data = data;
   }
}

public class Solution {

   public static void main(String[] args) {
      Node first = new Node(1);
      Node second = new Node(1);
      Node third = new Node(1);
      Node fourth = new Node(1);

      first.next = second;
      second.next = third;
      third.next = fourth;
      fourth.next = second;

      boolean result = detectLoop(first);

      System.out.println(result);
   }

   public static boolean detectLoop(Node head){
      return detectLoop(head, head, false);
   }
   public static boolean detectLoop(Node fast, Node slow, boolean hop){
      if(fast == null) {
         return false;
      }
      fast = fast.next;
      if(hop){
         slow = slow.next;
      }
      if(fast == slow) {
         return true;
      }
      return detectLoop(fast, slow, !hop);
   }
}
