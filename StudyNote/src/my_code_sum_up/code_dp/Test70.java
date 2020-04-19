package my_code_sum_up.code_dp;

import java.util.HashMap;

// 爬楼问题
// 动态规划
public class Test70 {
    /**
     * 动态规划求解爬楼梯问题
     * @param n
     * @return
     */
    public int climbStairs1(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 暴力递归版本(memo技术)
     * @param n
     * @return
     */
    public int climbStairs2(int n){
        HashMap<Integer,Integer> memo = new HashMap<>();
        return getAns(1,n,memo);
    }

    private int getAns(int s, int n,HashMap<Integer,Integer> memo) {
        if(s >= n){
            return 1;
        }
        if(memo.containsKey(s)){
            return memo.get(s);
        }
        int ans = getAns(s + 1,n,memo) + getAns(s+2,n,memo);
        memo.put(s,ans);
        return ans;
    }
}
