package yc.no4;

import yc.second.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Test230 {
    int num = 0;
    int res = 0;

    public int kthSmallest(TreeNode root, int k) {
        infixSearch(root, k);
        return res;
    }

    private void infixSearch(TreeNode root, int k) {
        if(root == null) return;
        infixSearch(root.left,k);
        num++;
        if(num == k){
            res = root.val;
            return;
        }
        infixSearch(root.right,k);
    }
}
