package my_code_sum_up.code_tree.zuo;

import my_code_sum_up.code_tree.util.PrintTree;
import my_code_sum_up.code_tree.util.TreeNode;

import java.util.Stack;

/**
 * 左神基础班————判断一颗树是否是搜索二叉树
 */
public class Zuo_IsSearchBinaryTree {

    // 中序遍历的基础上做修改
    public static boolean isSBT(TreeNode root){
        if(root == null) return true;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        int cur = Integer.MIN_VALUE;
        while(!stack.isEmpty() || root != null){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                if(root.val < cur){
                    return false;
                }
                cur = root.val;
                root = root.right;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(2);
        head.right = new TreeNode(6);
        head.left.left = new TreeNode(1);
        //head.left.right = new Node(3);
        head.right.left = new TreeNode(5);

        PrintTree.printTree(head);
        System.out.println(isSBT(head));
    }
}
