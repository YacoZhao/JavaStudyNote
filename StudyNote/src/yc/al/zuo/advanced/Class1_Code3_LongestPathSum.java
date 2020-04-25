package yc.al.zuo.advanced;

import java.util.HashMap;

/**
 * 列加和为sum的最长路径的长度（类似数组的最长累加和）
 * @author code_yc
 * @version 1.0
 * @date 2020/4/23 8:34
 */
public class Class1_Code3_LongestPathSum {

    // 首先定义一颗树
    static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    // 主函数
    public static int longestPathSum(TreeNode root, int sum){
        if(root == null) return 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,0);
        // 进入计算函数
        return process(root,sum,map,1,0,0);
    }

    private static int process(TreeNode root, int sum, HashMap<Integer, Integer> map, int level,int preSum, int maxLen) {
        if(root == null){
            return maxLen;
        }
        int curSum = preSum + root.value;
        if(!map.containsKey(curSum)){
            map.put(curSum,level);
        }
        if(map.containsKey(curSum - sum)){
            maxLen = Math.max(maxLen,level - map.get(curSum - sum));
        }
        // 向左更新maxLen
        maxLen = process(root.left, sum, map, level + 1, curSum, maxLen);
        // 向右更新maxLen
        maxLen = process(root.right, sum, map, level + 1, curSum, maxLen);
        // 去左右节点相同值得操作
        if(level == map.get(curSum)){
            map.remove(curSum);
        }
        return maxLen;
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

    public static void main(String[] args) {
        TreeNode head = new TreeNode(-3);
        head.left = new TreeNode(3);
        head.right = new TreeNode(-9);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(0);
        head.left.right.left = new TreeNode(1);
        head.left.right.right = new TreeNode(6);
        head.right.left = new TreeNode(2);
        head.right.right = new TreeNode(1);
        printTree(head);
        System.out.println(longestPathSum(head, 4));
        System.out.println(longestPathSum(head, 9));
    }
}
