package my_code_sum_up.code_dp;

import java.util.HashMap;

// 不同的二叉搜索树：
// 方法一： 暴力递归
// 方法二： 动态规划
public class Test96 {

    /**
     * 方法一： 暴力递归
     * @param n
     * @return
     */
    public int numTrees1(int n){
        if(n <= 1) return 1;
        return getAns(1,n);
    }

    private int getAns(int s, int e) {
        if(s > e || s == e) return 1;
        int ans = 0;
        for (int i = s; i <= e; i++) {
            ans += getAns(s,i-1) * getAns(i+1,e);
        }
        return ans;
    }

    // memo技术优化暴力递归
    public int numTrees2(int n) {
        if(n <= 1) return 1;
        HashMap<String,Integer> memo = new HashMap<String,Integer>();
        return getAns2(1,n,memo);
    }

    private int getAns2(int s, int e,HashMap<String,Integer> memo) {
        if(s > e || s == e) return 1;
        String key = s + "@" + e;
        if(memo.containsKey(key)){
            return memo.get(key);
        }
        int ans = 0;
        for (int i = s; i <= e; i++) {
            ans += getAns2(s,i-1,memo) * getAns2(i+1,e,memo);
        }
        memo.put(key,ans);
        return ans;
    }

    /**
     * 方法二： 动态规划
     * @param n
     * @return
     */
    public int numTrees3(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
