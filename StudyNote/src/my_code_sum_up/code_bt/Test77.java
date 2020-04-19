package my_code_sum_up.code_bt;

import java.util.*;

//77. 组合
// 方法： 回溯算法
public class Test77 {

    //------------------------经典回溯----------------------------
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if(n >= k){
            Deque<Integer> path = new ArrayDeque<>();
            dfs(ans,n,k,1,path);
        }
        return ans;
    }

    private void dfs(List<List<Integer>> ans, int n, int k, int s, Deque<Integer> path){
        if(path.size() == k){
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = s; i <= n; i++) {
            path.addLast(i);
            dfs(ans,n,k,s+1,path);
            path.removeLast();
        }
    }


    //------------------------经典回溯（优化）----------------------------
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if(n >= k){
            Deque<Integer> path = new ArrayDeque<>();
            dfs(ans,n,k,1,path);
        }
        return ans;
    }

    private void dfs2(List<List<Integer>> ans, int n, int k, int s, Deque<Integer> path){
        if(path.size() == k){
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = s; i <= n - k + path.size() + 1; i++) {
            path.addLast(i);
            dfs(ans,n,k,s+1,path);
            path.removeLast();
        }
    }
}
