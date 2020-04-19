package yc.al.zuo.advanced;

// 5-6 求一个完全二叉树的节点个数
public class Class5_Code6_CompleteTreeNodeNumber {

    static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    // 主函数，寻找一个完全二叉树中节点的个数
    //+++++++++++++++++++++ 方法一： 暴力递归 +++++++++++++++++++++++++++++++++
    public static int getNodeNums1(TreeNode root){
        if(root == null) return 0;
        return process(root);
    }

    private static int process(TreeNode root) {
        if(root == null) return 0;
        return process(root.left) + 1 + process(root.right);
    }

    // ++++++++++++++++++++++++ 方法二：优化  +++++++++++++++++++++++++++++
    public static int getNodeNums2(TreeNode root){
        if(root == null) return 0;
        // 首先将当前树的高度求出来
        int height = getHeights(root,1);
        return bfs(root,1,height);

    }

    // 进入递归搜索
    private static int bfs(TreeNode root, int level, int height) {
        if(level == height) return 1;
        if(getHeights(root.right,level+ 1) == height){
            return (1 << (height - level)) + bfs(root.right, level + 1, height);
        }else{
            return (1 << (height - level - 1)) + bfs(root.left, level+1, height);
        }
    }

    // level表示当前所在的层数
    private static int getHeights(TreeNode root, int level) {
        while(root != null){
            level++;
            root = root.left;
        }
        return level - 1;
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                          测试集
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

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
        System.out.println(getNodeNums1(head));
        System.out.println(getNodeNums2(head));
    }
}
