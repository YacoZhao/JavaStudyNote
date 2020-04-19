package my_code_sum_up.code_tree.zuo;

import my_code_sum_up.code_tree.util.TreeNode;

import java.util.Stack;

// 前序中序后序遍历二叉树
public class Zuo_PreInPosTraversal {

    // 前序遍历递归实现
    public static void preOrderRecur(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrderUnRecur(root.left);
        preOrderUnRecur(root.right);
    }

    // 中序遍历递归实现
    public static void inOrderRecur(TreeNode root) {
        if (root == null) return;
        inOrderUnRecur(root.left);
        System.out.print(root.val + " ");
        inOrderUnRecur(root.right);
    }

    //后序遍历递归实现
    public static void posOrderRecur(TreeNode root) {
        if (root == null) return;
        posOrderUnRecur(root.left);
        posOrderUnRecur(root.right);
        System.out.print(root.val + " ");
    }

    // 前序遍历非递归实现
    public static void preOrderUnRecur(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            System.out.print(root.val + " ");
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }
        System.out.println();
    }

    // 中序遍历非递归实现
    public static void inOrderUnRecur(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                System.out.print(root.val + " ");
                root = root.right;
            }
        }
        System.out.println();
    }

    // 后序遍历非递归实现
    public static void posOrderUnRecur(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty()) {
            root = s1.pop();
            s2.push(root);
            if (root.left != null) {
                s1.push(root.left);
            }
            if (root.right != null) {
                s1.push(root.right);
            }
        }
        while (!s2.isEmpty()) {
            System.out.print(s2.pop().val + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(5);
        head.left = new TreeNode(3);
        head.right = new TreeNode(8);
        head.left.left = new TreeNode(2);
        head.left.right = new TreeNode(4);
        head.left.left.left = new TreeNode(1);
        head.right.left = new TreeNode(7);
        head.right.left.left = new TreeNode(6);
        head.right.right = new TreeNode(10);
        head.right.right.left = new TreeNode(9);
        head.right.right.right = new TreeNode(11);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        preOrderUnRecur(head);
        inOrderUnRecur(head);
        posOrderUnRecur(head);
//        posOrderUnRecur2(head);
    }
}
