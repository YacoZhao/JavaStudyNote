package my_code_sum_up.code_tree.zuo;

import com.sun.org.apache.regexp.internal.RE;
import my_code_sum_up.code_tree.util.PrintTree;
import my_code_sum_up.code_tree.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 左神基础班————判断一个树是否是完全二叉树
 *
 */
public class Zuo_IsCompleteBinaryTree {
    public static boolean isCBT(TreeNode root){
        if(root == null) return true;
        boolean flag = false;  // 用于标记节点是否已近进入叶子节点模式
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        TreeNode l = null;
        TreeNode r = null;
        while(!queue.isEmpty()){
            root = queue.poll();
            l = root.left;
            r = root.right;
            if((flag && (l != null || r != null)) || (l == null && r != null)){
                return false;
            }
            if(l != null){
                queue.add(l);
            }
            if(r != null){
                queue.add(r);
            }
            if(l == null || r == null){
                flag = true;
            }
        }
        return true;
    }

    // 获取当前数树的高度
    public static int getHeight(TreeNode root){
        int res = 0;
        TreeNode cur = root;
        while(cur != null){
            res++;
            cur = cur.left;
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(2);
        head.right = new TreeNode(6);
        head.left.left = new TreeNode(1);
        //head.left.right = new Node(3);
        head.right.left = new TreeNode(5);

        PrintTree.printTree(head);
        System.out.println(isCBT(head));
    }
}
