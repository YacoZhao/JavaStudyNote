package my_code_sum_up.code_tree.leecode;

import my_code_sum_up.code_tree.util.TreeNode;

public class LeeCode104 {

    // 递归
    public int maxDepth(TreeNode root) {
        int res = 0;
        if(root != null){
            res = process(root);
        }
        return res;
    }

    private int process(TreeNode root) {
        if(root == null) return 0;
        int res = 1;
        res += Math.max(process(root.left),process(root.right));
        return res;
    }
}
