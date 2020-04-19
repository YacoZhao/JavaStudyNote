package my_code_sum_up.code_tree.leecode;

import my_code_sum_up.code_tree.util.TreeNode;
import org.omg.CORBA.INTERNAL;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 102. 二叉树的层次遍历
public class LeeCode102 {

    public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root != null){
            process(root,res,0);
        }
        return res;
    }

    private void process(TreeNode root, List<List<Integer>> res, int level) {
        if(res.size() == level){
            res.add(new ArrayList<Integer>());
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
