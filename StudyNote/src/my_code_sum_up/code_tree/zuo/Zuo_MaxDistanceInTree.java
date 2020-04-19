package my_code_sum_up.code_tree.zuo;

import my_code_sum_up.code_tree.util.PrintTree;
import my_code_sum_up.code_tree.util.TreeNode;

/**
 * 左神进阶版————返回树中的最大距离
 *
 * 题目描述：
 * 二叉树中，一个节点可以往上走和往下走，那么从节点A总能走到节点B。
 * 节点A走到节点B的距离为：A走到B最短路径上的节点个数。
 * 求一棵二叉树上的最远距离
 *
 */
public class Zuo_MaxDistanceInTree {

    public static class ResultType{
        int maxD;
        int height;

        public ResultType(int maxD, int height){
            this.maxD = maxD;
            this.height = height;
        }
    }

    public static int maxDistance(TreeNode root) {
        if(root == null) return 0;
        return process(root).maxD;
    }

    private static ResultType process(TreeNode root) {
        if(root == null) return new ResultType(0,0);

        ResultType left = process(root.left);
        ResultType right = process(root.right);
        int maxD = 0;
        maxD = Math.max(Math.max(left.maxD,right.maxD),left.height + right.height + 1);
        return new ResultType(maxD,Math.max(left.height,right.height) + 1);
    }

    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(1);
        head1.left = new TreeNode(2);
        head1.right = new TreeNode(3);
        head1.left.left = new TreeNode(4);
        head1.left.right = new TreeNode(5);
        head1.right.left = new TreeNode(6);
        head1.right.right = new TreeNode(7);
        head1.left.left.left = new TreeNode(8);
        head1.right.left.right = new TreeNode(9);
        PrintTree.printTree(head1);
        System.out.println(maxDistance(head1));

        TreeNode head2 = new TreeNode(1);
        head2.left = new TreeNode(2);
        head2.right = new TreeNode(3);
        head2.right.left = new TreeNode(4);
        head2.right.right = new TreeNode(5);
        head2.right.left.left = new TreeNode(6);
        head2.right.right.right = new TreeNode(7);
        head2.right.left.left.left = new TreeNode(8);
        head2.right.right.right.right = new TreeNode(9);
        PrintTree.printTree(head2);
        System.out.println(maxDistance(head2));

    }
}
