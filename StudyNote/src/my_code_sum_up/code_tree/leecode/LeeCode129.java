package my_code_sum_up.code_tree.leecode;

import my_code_sum_up.code_tree.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

//129. 求根到叶子节点数字之和
public class LeeCode129 {

    int result = 0;

    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        helper(root,0);
        return result;
    }

    // 从上到下获取每根路径的字符串
    private void helper(TreeNode root, int s){
        s =  s * 10 + root.val;
        if(root.left == null && root.right == null){
            result += s;
            return;
        }
        int t = s;
        if(root.left != null) helper(root.left,s);
        if(root.right != null) helper(root.right,t);
    }
}
