package yc.second;

import java.util.*;

public class Test77 {
    List<List<Integer>> res = new ArrayList<>();
    /**
     * 手撸(回溯)
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        //边界条件判断
        if (n == 0 || k == 0 || n < k) {
            return res;
        }
        Stack<Integer> track = new Stack<>();
        backtrack(n, 1, track, k);
        return res;
    }

    private void backtrack(int n, int begin, Stack<Integer> track, int k) {
        //结束条件
        if (track.size() == k) {
            res.add(new ArrayList<>(track));
            return;
        }
        //回溯
        for (int i = begin; i <= n; i++) {
            track.push(i);
            backtrack(n,i+1,track,k);
            track.pop();
        }

    }
}
