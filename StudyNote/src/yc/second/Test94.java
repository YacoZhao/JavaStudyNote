package yc.second;

import java.util.ArrayList;
import java.util.List;

public class Test94 {

    /**
     * 递归编写中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        infixOrder(root, res);
        return res;
    }

    /**
     * 中序遍历函数体
     * @param root
     * @param res
     */
    private void infixOrder(TreeNode root, List<Integer> res) {
        if (root.left != null) {
            infixOrder(root.left, res);
        }
        res.add(root.val);
        if (root.right != null) {
            infixOrder(root.right, res);
        }
    }
}
