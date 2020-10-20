package geeks_for_geeks.remove_loop_in_linked_list;


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
      Node second = new Node(2);
      Node third = new Node(3);
      Node fourth = new Node(4);

      first.next = second;
      second.next = third;
      third.next = fourth;
      fourth.next = second;

      removeLoop(first);

      System.out.println(fourth.next == null);
   }

   public static void removeLoop(Node head){
      int loopLen =  countLoopLen(head, head, false);
      if(loopLen == 0) {
         return;
      }
      Node aheadNode = goAhead(head, loopLen);
      Node junctionNode = findJunctionNode(head, aheadNode);
      Node nodeToDisjoint = goAhead(junctionNode, loopLen-1);
      nodeToDisjoint.next = null;
   }

   private static Node findJunctionNode(Node first, Node second) {
      if(first == second) {
         return first;
      }
      return findJunctionNode(first.next, second.next);
   }

   private static Node goAhead(Node head, int loopLen) {
      if(loopLen == 0) {
         return head;
      }
      return goAhead(head.next, loopLen-1);
   }

   public static int countLoopLen(Node fast, Node slow, boolean hop){
      if(fast == null) {
         return 0;
      }
      fast = fast.next;
      if(hop){
         slow = slow.next;
      }
      if(fast == slow) {
         return countLoopLen(fast, slow.next, 1);
      }
      return countLoopLen(fast, slow, !hop);
   }

   private static int countLoopLen(Node standing, Node moving, int currentLength) {
      if(standing == moving) {
         return currentLength;
      }
      return 1 + countLoopLen(standing, moving.next, currentLength);
   }
}
