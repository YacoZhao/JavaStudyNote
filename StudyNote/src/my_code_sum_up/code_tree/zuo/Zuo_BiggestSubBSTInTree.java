package my_code_sum_up.code_tree.zuo;

import my_code_sum_up.code_tree.util.PrintTree;
import my_code_sum_up.code_tree.util.TreeNode;

/**
 * 左神进阶班————树形DP问题
 * 题目描述：给定一棵二叉树的头节点head，请返回最大搜索二叉子树的头节点

 *
 */
public class Zuo_BiggestSubBSTInTree {

    // 定义返回结果集
    public static class ResultType{
        int maxSize;
        TreeNode head;
        int max;
        int min;

        public ResultType(int maxSize,TreeNode head, int max, int min){
            this.maxSize = maxSize;
            this.head = head;
            this.max = max;
            this.min = min;
        }
    }

    // 函数主体
    public static TreeNode biggestSubBST(TreeNode root){
        if(root == null) return null;
        return process(root).head;
    }

    // 递归体
    private static ResultType process(TreeNode root) {
        if(root == null) {
            return new ResultType(0,null,Integer.MIN_VALUE,Integer.MAX_VALUE);
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        ResultType leftResult = process(left);
        ResultType rightResult = process(right);
        int includeHeadCase = 0;
        if(leftResult.head == left && rightResult.head == right && leftResult.max < root.val && rightResult.min > root.val){
            includeHeadCase = leftResult.maxSize + rightResult.maxSize + 1;
        }
        int p1 = leftResult.maxSize;
        int p2 = rightResult.maxSize;
        int maxSize = Math.max(Math.max(p1,p2),includeHeadCase);
        TreeNode head = p1 > p2 ? leftResult.head : rightResult.head;
        if(maxSize == includeHeadCase){
            head = root;
        }
        return new ResultType(
            maxSize,
            head,
            Math.max(Math.max(leftResult.max,rightResult.max),root.val),
            Math.min(Math.min(leftResult.min,rightResult.min),root.val)
        );
    }

    // 测试函数
    public static void main(String[] args) {
        TreeNode head = new TreeNode(6);
        head.left = new TreeNode(1);
        head.left.left = new TreeNode(0);
        head.left.right = new TreeNode(3);
        head.right = new TreeNode(12);
        head.right.left = new TreeNode(10);
        head.right.left.left = new TreeNode(4);
        head.right.left.left.left = new TreeNode(2);
        head.right.left.left.right = new TreeNode(5);
        head.right.left.right = new TreeNode(14);
        head.right.left.right.left = new TreeNode(11);
        head.right.left.right.right = new TreeNode(15);
        head.right.right = new TreeNode(13);
        head.right.right.left = new TreeNode(20);
        head.right.right.right = new TreeNode(16);

        PrintTree.printTree(head);
        TreeNode bst = biggestSubBST(head);
        PrintTree.printTree(bst);
    }
}
