package yc.al.zuo.advanced;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 5-1 判断一颗树是否是BST和CBT
public class Class5_Code1_IsBSTAndCBT {

    static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    // 问题一： 判断一颗树是不是搜索二叉树
    // 方法一： 用树形DP的思想求解
    public static boolean isBST1(TreeNode root){
        if(root == null) return true;
        return process(root).isBST;
    }

    static class ResultData{
        boolean isBST;
        int max;
        int min;

        public ResultData(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    private static ResultData process(TreeNode root){
        if(root == null) return new ResultData(true,Integer.MIN_VALUE, Integer.MAX_VALUE);
        ResultData left = process(root.left);
        ResultData right = process(root.right);
        if(left.isBST && right.isBST){
            if(root.value > left.max && root.value < right.min){
                return new ResultData(true,Math.max(right.max,root.value),Math.min(root.value,left.min));
            }
        }
        return new ResultData(false,0,0);
    }

    // 方法二： 使用二叉树的中序遍历，比较各个节点的大小
    public static boolean isBST2(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return true;
        Stack<Integer> data = new Stack<>();
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                if(!data.isEmpty() && root.value < data.peek()){
                    return false;
                }
                data.push(root.value);
                root = root.right;
            }
        }
        return true;
    }

    // 方法三：Morris遍历实现
    public static boolean isBST3(TreeNode root){
        if(root == null || (root.left == null && root.right == null)) return true;
        TreeNode cur1 = root;
        TreeNode cur2 = null;
        TreeNode pre = null;
        while(cur1 != null){
            cur2 = cur1.left;
            if(cur2 != null){
                while(cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                if(cur2.right == null){
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                }else{
                    cur2.right = null;
                }
            }
            if(pre != null && cur1.value < pre.value){
                return false;
            }
            pre = cur1;
            cur1 = cur1.right;
        }
        return true;
    }

    // 问题二： 判断一颗树是不是完全二叉树
    public static boolean isCBT(TreeNode root){
        if(root == null) return true;
        boolean leaf = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(queue.isEmpty()){
            root = queue.poll();
            if(root.left != null){
                queue.add(root.left);
            }
            if(root.right != null){
                queue.add(root.right);
            }
            if(root.right != null && root.left == null){
                return false;
            }
            if(leaf && (root.left != null || root.right != null)){
                return false;
            }
            if(root.left == null || root.right == null){
                leaf = true;
            }
        }
        return true;
    }

    //+++++++++++++++++++++++++测试集+++++++++++++++++++++++++++++++++
    // for test -- print tree
    public static void printTree(TreeNode head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(TreeNode head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(2);
        head.right = new TreeNode(6);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(3);
        head.right.left = new TreeNode(5);

        printTree(head);
        System.out.println(isBST1(head));
        System.out.println(isBST2(head));
        System.out.println(isBST3(head));
        System.out.println(isCBT(head));
    }

}
