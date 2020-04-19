package my_code_sum_up.code_tree.leecode;

import my_code_sum_up.code_tree.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeeCode144 {

    // 普通方式
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root != null){
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(root);
            while(!stack.isEmpty()){
                root = stack.pop();
                res.add(root.val);
                if(root.right != null){
                    stack.push(root.right);
                }
                if(root.left != null){
                    stack.push(root.left);
                }
            }
        }
        return res;
    }

    //morris遍历
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root != null){
            TreeNode cur = root;
            TreeNode morris = null;
            while(cur != null){
                morris = cur.left;
                if(morris != null){
                    while(morris.right != null && morris.right != cur){
                        morris = morris.right;
                    }
                    if(morris.right == null){
                        res.add(cur.val);
                        morris.right = cur;
                        cur = cur.left;
                        continue;
                    }else{
                        morris.right = null;
                    }
                }else{
                    res.add(cur.val);
                }
                cur = cur.right;
            }
        }
        return res;
    }
}
