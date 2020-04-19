package my_code_sum_up.code_tree.leecode;

import my_code_sum_up.code_tree.util.TreeNode;

//124. 二叉树中的最大路径和
public class LeeCode124 {

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }

    private int helper(TreeNode root) {
        if(root == null) return 0;

        int left = Math.max(helper(root.left),0);
        int right = Math.max(helper(root.right),0);

        max = Math.max(max,left + right + root.val);

        return Math.max(left,right) + root.val;
    }
}
