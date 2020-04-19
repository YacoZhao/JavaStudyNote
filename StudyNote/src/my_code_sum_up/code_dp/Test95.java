package my_code_sum_up.code_dp;

import sun.reflect.generics.tree.Tree;
import yc.second.TreeNode;

import java.util.ArrayList;
import java.util.List;

// 95. 不同的二叉搜索树Ⅱ
// 方法一： 暴力递归
// 方法二： 动态规划
public class Test95 {


    /**
     * 方法一： 暴力递归
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new ArrayList<>();
        if(n != 0){
            return getAns(1,n);
        }
        return res;
    }

    private List<TreeNode> getAns(int s, int e){
        List<TreeNode> res = new ArrayList<TreeNode>();
        if(s > e){
            res.add(null);
            return res;
        }
        if(s == e){
            res.add(new TreeNode(s));
            return res;
        }
        for (int i = s; i <= e; i++) {
            List<TreeNode> leftResults = getAns(s,i-1);
            List<TreeNode> rightResults = getAns(i+1,e);
            for (TreeNode rightResult : rightResults) {
                for (TreeNode leftResult : leftResults) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftResult;
                    root.right = rightResult;
                    res.add(root);
                }
            }
        }
        return res;
    }

    /**
     * 方法二： 动态规划求解
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees2(int n) {
        ArrayList<TreeNode>[] dp = new ArrayList[n+1];
        dp[0] = new ArrayList<TreeNode>();
        if(n == 0){
            return dp[0];
        }
        dp[0].add(null);
        // 遍历填数组(从1到n)
        for (int i = 1; i <= n; i++) {
            dp[i] = new ArrayList<TreeNode>();
            for (int j = 1; j <= i; j++) {
                int left = j - 1;
                int right = i - j;
                for (TreeNode l_tree : dp[left]) {
                    for (TreeNode r_tree : dp[right]) {
                        TreeNode root = new TreeNode(j);
                        root.left = l_tree;
                        root.right = clone(r_tree,j);  // 注意： 这里原始的dp[right]是1-r对应的所以树所构成的结果， 但是此处要求的是j - j + right 的结果，所以需要加上偏移量right
                        dp[i].add(root);
                    }
                }
            }
        }
        return dp[n];
    }

    // 赋值一棵树，加上遍历量
    private TreeNode clone(TreeNode root, int offset){
        if(root == null){
            return null;
        }
        TreeNode new_T = new TreeNode(root.val + offset);
        new_T.left = clone(root.left,offset);
        new_T.right = clone(root.right,offset);
        return new_T;
    }
}
