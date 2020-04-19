package my_code_sum_up.code_tree.zuo;

import my_code_sum_up.code_tree.util.PrintTree;
import my_code_sum_up.code_tree.util.TreeNode;

/**
 * 左神进阶班——————morris遍历
 *
 */
public class Zuo_MorrisTraversal {

    // 莫里斯前序遍历
    public static void morrisPre(TreeNode root) {
      if(root == null) return;
      TreeNode cur = root;
      TreeNode morris = null;
      while(cur != null){

          morris = cur.left;
          if(morris != null){
              while(morris.right != null && morris.right != cur){
                  morris = morris.right;
              }
              if(morris.right == null){
                  morris.right = cur;
                  System.out.print(cur.val + " ");
                  cur = cur.left;
                  continue;
              }else{
                  morris.right = null;
              }
          }else{
              System.out.print(cur.val + " ");
          }
          cur = cur.right;
      }
      System.out.println();
    }

    // 莫里斯中序遍历
    public static void morrisIn(TreeNode root) {
        if(root == null) return;
        TreeNode cur = root;
        TreeNode morris = null;
        while(cur != null){
            morris = cur.left;
            if(morris != null){
                while(morris.right != null && morris.right != cur){
                    morris = morris.right;
                }
                if(morris.right == null){
                    morris.right = cur;
                    cur = cur.left;
                    continue;
                }else{
                    morris.right = null;
                }
            }
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        System.out.println();
    }

    // 莫里斯后序遍历
    public static void morrisPos(TreeNode root) {
        if(root == null) return;
        TreeNode cur = root;
        TreeNode morris = null;
        while(cur != null){
            morris = cur.left;
            if(morris != null){
                while(morris.right != null && morris.right != cur){
                    morris = morris.right;
                }
                if(morris.right == null){
                    morris.right = cur;
                    cur = cur.left;
                    continue;
                }else{
                    morris.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(root);
        System.out.println();
    }

    private static void printEdge(TreeNode root) {
        TreeNode tail = reverseNode(root);
        TreeNode cur = tail;
        while(cur != null){
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        reverseNode(tail);
    }

    private static TreeNode reverseNode(TreeNode root) {
        TreeNode pre = null;
        TreeNode next = null;
        while(root != null){
            next = root.right;
            root.right = pre;
            pre = root;
            root = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(2);
        head.right = new TreeNode(6);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(3);
        head.right.left = new TreeNode(5);
        head.right.right = new TreeNode(7);
        PrintTree.printTree(head);
        morrisIn(head);
        morrisPre(head);
        morrisPos(head);
        PrintTree.printTree(head);
    }
}
