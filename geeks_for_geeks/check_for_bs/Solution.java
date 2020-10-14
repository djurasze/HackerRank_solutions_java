package geeks_for_geeks.check_for_bs;

class Node {
   int data;
   Node left;
   Node right;

   public Node(int data) {
      this.data = data;
   }
}

public class Solution {

   public static void main(String[] args) {

   }

   boolean isBST(Node root) {
      int min = Integer.MIN_VALUE;
      int max = Integer.MAX_VALUE;
      return isBST(root, min, max);
   }

   private boolean isBST(Node root, int min, int max) {
      if(root == null) {
         return true;
      }
      if(root.data > min && root.data < max) {
         return isBST(root.left, min, root.data) && isBST(root.right, root.data, max);
      } else {
         return false;
      }
   }

}
