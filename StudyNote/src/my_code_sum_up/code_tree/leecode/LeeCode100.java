package my_code_sum_up.code_tree.leecode;

import my_code_sum_up.code_tree.util.TreeNode;

// 100. 相同的树
public class LeeCode100 {

    // 递归调用二叉树
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;
        if (!isSameTree(p.left, q.left)) return false;
        if (!isSameTree(p.right, q.right)) return false;
        return true;
    }
}
