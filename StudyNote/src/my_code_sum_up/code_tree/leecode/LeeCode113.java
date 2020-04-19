package my_code_sum_up.code_tree.leecode;

import my_code_sum_up.code_tree.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

//113. 路径总和 II
public class LeeCode113 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res  = new ArrayList<>();
        if(root != null){
            dfs(root,sum,res,new ArrayList<>());
        }
        return res;
    }

    private void dfs(TreeNode root, int sum, List<List<Integer>> res, ArrayList<Integer> path) {
        if(root == null){
            return;
        }
        sum -= root.val;
        path.add(root.val);
        if(root.left == null && root.right == null){
            if(sum == 0){
                res.add(new ArrayList<>(path));
            }
            return;
        }
        dfs(root.left,sum,res,new ArrayList<>(path));
        dfs(root.right,sum,res,new ArrayList<>(path));
        path.remove(path.size() - 1);
    }

    // 解法二：
    List<List<Integer>> list = new ArrayList<>();
    ArrayList<Integer> inner = new ArrayList<>();
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        if (root == null) return list;
        sum -= root.val;
        inner.add(root.val);  // 入列表
        if (root.left == null && root.right == null){
            if (sum == 0){
                list.add(new ArrayList<>(inner));  // 记得拷贝一份
            }
        }
        if (root.left != null)  pathSum2(root.left, sum);
        if (root.right != null)  pathSum2(root.right, sum);
        inner.remove(inner.size()-1);  //从列表中删除
        return list;
    }

}
