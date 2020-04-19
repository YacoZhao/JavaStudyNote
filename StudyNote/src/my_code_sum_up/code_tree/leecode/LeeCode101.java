package my_code_sum_up.code_tree.leecode;

import my_code_sum_up.code_tree.util.TreeNode;

//101. 对称二叉树
public class LeeCode101 {

    // 递归求解
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return process(root,root);
    }

    private boolean process(TreeNode r1, TreeNode r2) {
        if(r1 == null && r2 == null) return true;
        if(r1 == null || r2 == null || r1.val != r2.val) return false;
        return process(r1.left,r2.right) && process(r1.right,r2.left);
    }
}
