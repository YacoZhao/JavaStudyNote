package yc.al.zuo.advanced;

// 给定一个数————返回最大二叉搜索子树的大小
public class Class4_Code4_BiggestSubBSTInTree {

    static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    static class ResultData{
        TreeNode head;   // 最大二叉搜索子树的头节点
        Integer size;    // 最大二叉搜索子树的头节点
        Integer min;     // 当前树种的最小值
        Integer max;     // 当前树中的最大值

        public ResultData(TreeNode head, Integer size, Integer min, Integer max) {
            this.head = head;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    // 主函数入口
    public static TreeNode biggestSubBST(TreeNode head) {
        if(head == null) return null;
        return process(head).head;
    }

    private static ResultData process(TreeNode head) {
        if(head == null) return new ResultData(null,0,Integer.MAX_VALUE,Integer.MIN_VALUE);
        ResultData left = process(head.left);
        ResultData right = process(head.right);
        if(head.left == left.head && head.right == right.head && head.value > left.max && head.value < right.min){
            return new ResultData(head,left.size + right.size + 1, Math.min(left.min,head.value),Math.max(right.max,head.value));
        }else{
            TreeNode resNode = left.size > right.size ? left.head : right.head;
            int size = left.size > right.size ? left.size : right.size;
            return new ResultData(resNode,size,Math.min(Math.min(left.min,right.min),head.value),Math.max(Math.max(left.max,right.max),head.value));
        }
    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                              测试集
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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

        TreeNode head = new TreeNode(6);
        head.left = new TreeNode(1);
        head.left.left = new TreeNode(0);
        head.left.right = new TreeNode(3);
        head.right = new TreeNode(12);
        head.right.left = new TreeNode(10);
        head.right.left.left = new TreeNode(4);
        head.right.left.left.left = new TreeNode(2);
        head.right.left.left.right = new TreeNode(5);
        head.right.left.right = new TreeNode(14);
        head.right.left.right.left = new TreeNode(11);
        head.right.left.right.right = new TreeNode(15);
        head.right.right = new TreeNode(13);
        head.right.right.left = new TreeNode(20);
        head.right.right.right = new TreeNode(16);

        printTree(head);
        TreeNode bst = biggestSubBST(head);
        printTree(bst);

    }

}
