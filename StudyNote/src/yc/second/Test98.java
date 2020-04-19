package yc.second;

public class Test98 {
    /**
     * 递归的思想
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return helper(root,null,null);
    }

    /**
     * 递归体
     * @param node 当前节点
     * @param lower  当前节点值的下限
     * @param upper  当前节点值的上限
     * @return
     */
    private boolean helper(TreeNode node, Integer lower, Integer upper) {
        if(node == null) return true;   //如果节点为空，但会true

        int val = node.val;  //val值一旦确定了就不会再改变
        if(lower != null && val <= lower) return false;
        if(upper != null && val >= upper) return false;

        if(!helper(node.right,val,upper)) return false;
        if(!helper(node.left,lower,val)) return false;

        return true;
    }
}
