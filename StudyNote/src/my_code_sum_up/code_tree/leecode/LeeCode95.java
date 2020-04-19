package my_code_sum_up.code_tree.leecode;

import my_code_sum_up.code_tree.util.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

//95. 不同的二叉搜索树 II
public class LeeCode95 {

    // 解法一： 递归搜索
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<TreeNode>();
        }
        return process(1, n);
    }

    private List<TreeNode> process(int s, int t) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        if (s == t) {
            res.add(new TreeNode(s));
            return res;
        }
        if (s > t) {
            res.add(null);
            return res;
        }
        for (int i = s; i <= t; i++) {
            List<TreeNode> lefts = process(s, i - 1);
            List<TreeNode> rights = process(i + 1, t);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
