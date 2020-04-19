package my_code_sum_up.code_tree.leecode;

import my_code_sum_up.code_tree.util.TreeNode;

//112. 路径总和
public class LeeCode112 {

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        sum -= root.val;
        if(root.left == null && root.right == null) {
            return sum == 0;
        }
        return hasPathSum(root.left,sum) || hasPathSum(root.right,sum);
    }
}
