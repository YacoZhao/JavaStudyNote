package my_code_sum_up.code_bt;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Test216 {

    // 回溯
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(ans,path,k,1,n);
        return ans;
    }

    /**
     *
     * @param ans
     * @param path
     * @param k     // 当前剩余几个k没有匹配
     * @param cur   // 当前可选的的数
     * @param n     // 当前累计和剩余值
     */
    private void dfs(List<List<Integer>> ans, Deque<Integer> path, int k, int cur, int n){
        if(n == 0){
            if(k == 0){
                ans.add(new ArrayList<>(path));
            }
            return;
        }
        if(cur > n){
            return;
        }
        for(int i = cur; i <= 9; i++){
            path.addLast(i);
            dfs(ans,path,k - 1,i+1,n-i);
            path.removeLast();
        }
    }
}
