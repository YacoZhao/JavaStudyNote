package yc.al.zuo.advanced;

/**
 * 进阶班一： 满足二叉搜索树条件的最大拓扑结构的大小。
 * @author code_yc
 * @version 1.0
 * @date 2020/4/23 8:58
 */
public class Class1_Code4_MaxSearchTreeTuo {

    // 首先定义二叉树
    static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    // 主函数
    public static int getMaxSize(TreeNode root){
        if(root == null) return 0;
        int max = maxTuoSize(root,root);    // 小黑盒，计算出已root为根节点的最大二叉拓扑结构的大小
        max = Math.max(max,getMaxSize(root.left));   // 向左子树更新max
        max = Math.max(max,getMaxSize(root.right));  // 向右子树更新max
        return max;
    }

    // 小黑盒：计算root，为根节点的最大二叉拓扑结构的大小
    private static int maxTuoSize(TreeNode root, TreeNode cur) {
        if(cur != null && isBSTNode(root,cur)){
            // 左边的结果加上中间的结果再加1
            return maxTuoSize(root,cur.left) + maxTuoSize(root,cur.right) + 1;
        }
        return 0;
    }

    // 检查cur是不是已root为根节点的拓扑结构中的子节点
    private static boolean isBSTNode(TreeNode root, TreeNode cur) {
        if(root == null){
            return false;
        }
        if(root == cur) {
            return true;
        }
        return isBSTNode(root.value > cur.value ? root.left : root.right,cur);
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ++                     测试集                         ++
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++

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

    public static void main(String []args) {
        //System.out.println("Hello");
        TreeNode node=new TreeNode(12);
        node.left=new TreeNode(10);
        node.right=new TreeNode(13);
        node.left.left=new TreeNode(4);
        node.left.right=new TreeNode(14);
        node.right.left=new TreeNode(20);
        node.right.right=new TreeNode(16);
        node.left.left.left=new TreeNode(2);
        node.left.left.right=new TreeNode(5);
        node.left.right.left=new TreeNode(11);
        node.left.right.right=new TreeNode(15);
        printTree(node);

        System.out.println(getMaxSize(node));
        //System.out.println(getMaxSize2(node));
    }

}
