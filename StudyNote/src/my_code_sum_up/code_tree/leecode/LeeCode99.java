package my_code_sum_up.code_tree.leecode;

import my_code_sum_up.code_tree.util.TreeNode;

import java.util.PriorityQueue;
import java.util.Stack;

//99. 恢复二叉搜索树
public class LeeCode99 {

    // 获取一颗二叉树
    public void recoverTree(TreeNode root) {
        if (root == null) return;
        //首先按照边一边获取所有得元素值
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        getNum(root, queue);
        //将queue得值依次弹出，填入root中
        addNum(root, queue);
    }

    private void addNum(TreeNode root, PriorityQueue<Integer> queue) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                root.val = queue.poll();
                root = root.right;
            }
        }
    }

    // 获取元素值
    private void getNum(TreeNode root, PriorityQueue<Integer> queue) {
        if (root == null) return;
        queue.add(root.val);
        getNum(root.left, queue);
        getNum(root.right, queue);
    }
}
