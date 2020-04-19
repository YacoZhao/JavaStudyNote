package yc.al.zuo.advanced;

// 问题描述： 给定一颗二叉树，求树中从一个节点走到另一个节点的最远举例
// 5 - 3
public class Class5_Code3_MaxDistanceInTree {

    static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    static class ResultData{
        int maxD;
        int height;

        public ResultData(int maxD, int height) {
            this.maxD = maxD;
            this.height = height;
        }
    }

    // 指定一颗树，求树中最大的举例
    public static int maxDistanceInTree(TreeNode root){
        if(root == null) return 0;
        return process(root).maxD;
    }

    private static ResultData process(TreeNode root) {
        if(root == null) return new ResultData(0,0);
        ResultData left = process(root.left);
        ResultData right = process(root.right);
        int curMaxD = Math.max(Math.max(left.maxD,right.maxD),left.height+right.height+1);
        return new ResultData(curMaxD,Math.max(left.height,right.height) + 1);
    }

    // ++++++++++++++++++++++++++++测试函数+++++++++++++++++++++++++++++++++++++

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
        TreeNode head1 = new TreeNode(1);
        head1.left = new TreeNode(2);
        head1.right = new TreeNode(3);
        head1.left.left = new TreeNode(4);
        head1.left.right = new TreeNode(5);
        head1.right.left = new TreeNode(6);
        head1.right.right = new TreeNode(7);
        head1.left.left.left = new TreeNode(8);
        head1.right.left.right = new TreeNode(9);
        printTree(head1);
        System.out.println(maxDistanceInTree(head1));

        TreeNode head2 = new TreeNode(1);
        head2.left = new TreeNode(2);
        head2.right = new TreeNode(3);
        head2.right.left = new TreeNode(4);
        head2.right.right = new TreeNode(5);
        head2.right.left.left = new TreeNode(6);
        head2.right.right.right = new TreeNode(7);
        head2.right.left.left.left = new TreeNode(8);
        head2.right.right.right.right = new TreeNode(9);
        printTree(head2);
        System.out.println(maxDistanceInTree(head2));
    }
}
