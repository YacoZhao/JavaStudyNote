package my_code_sum_up.code_tree.leecode;

import my_code_sum_up.code_tree.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//199. 二叉树的右视图
public class LeeCode199 {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root != null){
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()){
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    root = queue.poll();
                    if(i == size - 1){
                        res.add(root.val);
                    }
                    if(root.left != null){
                        queue.add(root.left);
                    }
                    if(root.right != null){
                        queue.add(root.right);
                    }
                }
            }
        }
        return res;
    }
}
