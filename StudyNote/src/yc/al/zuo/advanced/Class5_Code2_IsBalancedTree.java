package yc.al.zuo.advanced;

// 判断一颗树是否是平衡二叉树
public class Class5_Code2_IsBalancedTree {

    static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    static class ResultData{
        boolean isAvl;
        int h;

        public ResultData(boolean isAvl, int h) {
            this.isAvl = isAvl;
            this.h = h;
        }
    }

    // 判断一颗树是不是平衡二叉树
    public static boolean isBalancedTree(TreeNode root) {
        if(root == null) return true;
        return process(root).isAvl;
    }

    private static ResultData process(TreeNode root) {
        if(root == null) return new ResultData(true,0);
        ResultData left = process(root.left);
        ResultData right = process(root.right);
        if(left.isAvl && right.isAvl && Math.abs(left.h - right.h) < 2){
            return new ResultData(true,Math.max(left.h,right.h) + 1);
        }
        return new ResultData(false,0);
    }

    // ++++++++++++++++++++++++++测试函数++++++++++++++++++++++++++++++++

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
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);

        printTree(head);
        System.out.println(isBalancedTree(head));
    }
}
