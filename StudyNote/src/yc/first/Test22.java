package yc.first;

import java.util.ArrayList;
import java.util.List;

//22. 括号生成
public class Test22 {

    //DFS深度优先遍历（回溯）从上向下遍历，left和right逐渐变小
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        if (n == 0) {
            return ans;
        }
        dfs("", n, n, ans);
        return ans;
    }

    private void dfs(String curStr, int left, int right, List<String> ans) {
        //终止条件
        if (left == 0 && right == 0) {
            ans.add(curStr);
            return;
        }
        //剪枝条件
        if (right < left) {
            return;
        }
        //添加括号
        if (left > 0) {
            dfs(curStr + "(", left - 1, right, ans);
        }
        if (right > 0) {
            dfs(curStr + ")", left, right - 1, ans);
        }
    }

    //DFS深度优先遍历（回溯）从下向上遍历，left和right逐渐变大
    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList<>();
        if (n == 0) {
            return ans;
        }
        dfs2("", 0, 0, n, ans);
        return ans;
    }

    private void dfs2(String curStr, int left, int right, int n, List<String> ans) {
        if (left == n && right == n) {
            ans.add(curStr);
            return;
        }
        //剪枝
        if (left < right) {
            return;
        }
        if (left < n) {
            dfs2(curStr + "(", left + 1, right, n, ans);
        }
        if (right < n) {
            dfs2(curStr + ")", left, right + 1, n, ans);
        }
    }
}
