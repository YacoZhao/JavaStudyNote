package my_code_sum_up.code_tree.zuo;

import my_code_sum_up.code_tree.util.TreeNode;

/**
 * 左神基础课————判断一颗树是否是平衡二叉树
 *
 * 树型DP问题
 */
public class Zuo_IsBalancedTree {

    // 主函数，判断结果是否为二叉树
    public static boolean isBalanceTree(TreeNode root) {
        ResultMap res = isBalance(root);
        return res.isAvl;
    }

    // 封装一个结果级
    public static class ResultMap {
        boolean isAvl;
        int height;

        public ResultMap(boolean isAvl, int height) {
            this.isAvl = isAvl;
            this.height = height;
        }
    }

    // 判断当前的树是否为Avl，返回结果为ResultMap
    public static ResultMap isBalance(TreeNode root) {
        if (root == null) return new ResultMap(true, 0);
        ResultMap left = isBalance(root.left);
        if (!left.isAvl) return new ResultMap(false, 0);
        ResultMap right = isBalance(root.right);
        if (!right.isAvl) return new ResultMap(false, 0);
        if (Math.abs(left.height - right.height) > 1) return new ResultMap(false, 0);
        return new ResultMap(true, Math.max(left.height, right.height) + 1);
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);

        System.out.println(isBalanceTree(head));
    }
}
