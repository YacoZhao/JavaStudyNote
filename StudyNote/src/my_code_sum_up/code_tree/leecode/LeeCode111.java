package my_code_sum_up.code_tree.leecode;

import my_code_sum_up.code_tree.util.TreeNode;

public class LeeCode111 {

    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        return process(root);
    }

    private int process(TreeNode root){
        if(root == null) return 0;
        int left = process(root.left);
        int right = process(root.right);
        if(left == 0) return right + 1;
        if(right == 0) return left + 1;
        return Math.min(left,right) + 1;
    }
}
