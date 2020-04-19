package my_code_sum_up.code_tree.leecode;

import my_code_sum_up.code_tree.util.TreeNode;
import org.omg.CORBA.INTERNAL;

import java.util.HashMap;

//105. 从前序与中序遍历序列构造二叉树
public class LeeCode105 {

    // 105. 从前序与中序遍历序列构造二叉树
    int[] preorder;
    int[] inorder;
    int index = 0;
    HashMap<Integer, Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        if (preorder.length == 0) return null;
        map = new HashMap<Integer, Integer>();
        int pos = 0;
        for (int v : inorder) {
            map.put(v, pos++);
        }
        return process(0, preorder.length);
    }

    private TreeNode process(int left, int right) {
        if (left == right) return null;
        int cur_val = preorder[index++];
        int in_val = map.get(cur_val);
        TreeNode root = new TreeNode(cur_val);
        root.left = process(left, in_val);
        root.right = process(in_val + 1, right);
        return root;
    }
}
