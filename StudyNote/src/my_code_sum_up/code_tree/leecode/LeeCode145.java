package my_code_sum_up.code_tree.leecode;

import my_code_sum_up.code_tree.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeeCode145 {

    //二叉树的后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root != null){
            Stack<TreeNode> s1 = new Stack<>();
            Stack<TreeNode> s2 = new Stack<>();
            s1.add(root);
            while(!s1.isEmpty()){
                root = s1.pop();
                s2.push(root);
                if(root.left != null){
                    s1.push(root.left);
                }
                if(root.right != null){
                    s1.push(root.right);
                }
            }
            while(!s2.isEmpty()){
                res.add(s2.pop().val);
            }
        }
        return res;
    }
}
