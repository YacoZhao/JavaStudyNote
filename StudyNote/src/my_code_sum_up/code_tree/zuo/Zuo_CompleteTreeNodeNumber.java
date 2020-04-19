package my_code_sum_up.code_tree.zuo;

import my_code_sum_up.code_tree.util.TreeNode;


/**
 * 左神基础班————求一个完全二叉树节点的个数
 *
 */
public class Zuo_CompleteTreeNodeNumber {

    // 主函数入口与
    public static int nodeNum(TreeNode root) {
        if(root == null) return 0;
        int height = getHeight(root);
        int res = 0;
        if(height == getHeight(root.right) + 1){
            // 如果右子树的高度置比总高度低1，那么左子树一定是满二叉树
            res += (1 << (height - 1)) + nodeNum(root.right);
        }else{
            // 否则右子树一定是满二叉树
            res += (1 << (height - 2)) + nodeNum(root.left);
        }
        return res;
    }

    private static int getHeight(TreeNode root){
        int res = 0;
        TreeNode cur = root;
        while(cur != null){
            res++;
            cur = cur.left;
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        System.out.println(nodeNum(head));
    }
}
