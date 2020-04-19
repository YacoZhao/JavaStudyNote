package my_code_sum_up.code_tree.leecode;

import my_code_sum_up.code_tree.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 107. 二叉树的层次遍历 II
public class LeeCode107 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Stack<List<Integer>> res = new Stack<List<Integer>>();
        if(root != null){
            process(root,res,0);
            while(!res.isEmpty()){
                ans.add(res.pop());
            }
        }
        return ans;
    }

    private void process(TreeNode root, Stack<List<Integer>> res, int level) {
        if(res.size() == level){
            res.push(new ArrayList<Integer>());
        }
        res.get(level).add(root.val);
        if(root.left != null){
            process(root.left,res,level+1);
        }
        if(root.right != null){
            process(root.right,res,level+1);
        }
    }

}
