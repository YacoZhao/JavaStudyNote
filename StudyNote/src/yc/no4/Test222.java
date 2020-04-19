package yc.no4;

import yc.second.TreeNode;

public class Test222 {
    /**
     * 递归的思想
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null) return 1;
        if(root.right == null) return 2;

        int left_point = countNodes(root.left);
        //右子树节点数为
        int right_point = countNodes(root.right);
        //返回结果
        return left_point + right_point + 1;
    }
}
