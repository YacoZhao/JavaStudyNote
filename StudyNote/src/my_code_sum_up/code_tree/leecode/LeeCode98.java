package my_code_sum_up.code_tree.leecode;

import my_code_sum_up.code_tree.util.TreeNode;
import org.omg.CORBA.INTERNAL;

import java.util.Stack;

//98. 验证二叉搜索树
public class LeeCode98 {

    // 判断一颗二叉树是否是有效的搜索二叉树

    /**
     * 解法一： 中序遍历的方法进行比较
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        long cur = Long.MIN_VALUE;   // 注意这里必须用long，否则首位如果是Integer.MIN_VALUE,就回报错
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (root.val <= cur) {
                    return false;
                }
                cur = root.val;
                root = root.right;
            }
        }
        return true;
    }

    /**
     * 方法二：递归的思想
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        // 头节点没头范围的限制，litter和upper位置直接上null
        return process(root,null,null);
    }

    /**
     * 递归体
     * @param root   根节点
     * @param litter 当前的节点值得下限
     * @param upper  当前得节点值得上限
     * @return
     */
    private boolean process(TreeNode root, Integer litter, Integer upper) {
        if (root == null) return true;  // root为null,直接true

        int val = root.val;
        if (litter != null && val <= litter) return false;  // 如果有下限，向右走了，有了之前的根节点作为下限，超限则直接false
        if (upper != null && val >= upper) return false;    // 如果有下限，向左走了，有了之前的根节点作为下限，超限则直接false

        if (!process(root.right, val, upper)) return false; // 向右走，提供下限
        if (!process(root.left, litter, val)) return false; // 向左走，提供上限

        return true;
    }

    // 解法三：树形Dp
    public boolean isValidBST3(TreeNode root){
        if(root == null){
            return true;
        }
        return process3(root).isBST;
    }

    // 封装结果集
    public class ResultData{
        boolean isBST;
        long max;
        long min;

        public ResultData(boolean isBST, long max, long min){
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    // 递归体
    private ResultData process3(TreeNode root) {
        // 如果为null，min返回最大，max返回最小
        if(root == null) return new ResultData(true,Long.MIN_VALUE, Long.MAX_VALUE);
        // 首先求出左右子树的结果集
        ResultData left = process3(root.left);
        ResultData right = process3(root.right);
        // 对比分析： 左右都必须为BST，且root.val 大于左子树最大值，小于右子树最小值，返回结果，找出最小值和最大值返回
        if(left.isBST && right.isBST && (left.max < root.val) && (root.val < right.min)){
            return new ResultData(true,Math.max(Math.max(left.max,right.max),root.val),Math.min(Math.min(left.min,right.min),root.val));
        }
        return new ResultData(false,0,0);
    }
}
