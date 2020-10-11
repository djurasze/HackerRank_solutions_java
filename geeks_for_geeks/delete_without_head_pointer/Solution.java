package geeks_for_geeks.delete_without_head_pointer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Node {
   int data;
   Node next;

   public Node(int data) {
      this.data = data;
   }
}

public class Solution {

   public static void main(String[] args) {
      Node root = new Node(1);
      Node next = new Node(2);
      root.next = next;

      Solution solution = new Solution();
      solution.deleteNode(root);

   }

   void deleteNode(Node node)
   {
      if(node.next != null) {
         node.data = node.next.data;
         if(node.next.next != null) {
            deleteNode(node.next);
         } else {
            node.next = null;
         }
      }
   }


}
