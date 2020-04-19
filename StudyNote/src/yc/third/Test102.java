package yc.third;

import yc.second.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Test102 {

    /**
     * 解法一： 递归
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root != null){
            helper(res,root,0);
        }
        return res;
    }

    private void helper(List<List<Integer>> res, TreeNode node, int level) {
        if(res.size() == level){
            res.add(new ArrayList<Integer>());
        }
        res.get(level).add(node.val);
        if(node.left != null){
            helper(res,node.left,level+1);
        }
        if(node.right != null){
            helper(res,node.right,level+1);
        }
    }
}
