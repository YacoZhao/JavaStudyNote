package my_code_sum_up.code_dp;

import java.util.HashMap;

//  132. 分割回文串 II
// 方法一：暴力递归
public class Test132 {

    /**
     * 方法一：暴力递归(超时)
     * @param s
     * @return
     */
    public int minCut(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];  //存储i-j位置的字符串是否是回文字符串
        int len = s.length();
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j + i - 1 < len; j++) {
                int k = j + i - 1;
                dp[j][k] = s.charAt(j) == s.charAt(k) && (i < 3 || dp[j+1][k-1]);
            }
        }
        return getAns(s,0,dp);
    }

    private int getAns(String s, int c,boolean[][] dp) {
        if(dp[c][s.length() - 1]) return 0;
        int ans = Integer.MAX_VALUE;
        for (int i = c; i < s.length(); i++) {
            if(dp[c][i]){
                ans = Math.min(ans,1+getAns(s,i+1,dp));
            }
        }
        return ans;
    }

    // 暴力递归memo技术优化
    public int minCut2(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];  //存储i-j位置的字符串是否是回文字符串
        int len = s.length();
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j + i - 1 < len; j++) {
                int k = j + i - 1;
                dp[j][k] = s.charAt(j) == s.charAt(k) && (i < 3 || dp[j+1][k-1]);
            }
        }
        HashMap<Integer,Integer> memo = new HashMap<>();
        return getAns2(s,0,dp,memo);
    }

    private int getAns2(String s, int c,boolean[][] dp,HashMap<Integer,Integer> memo) {
        if(dp[c][s.length() - 1]) return 0;
        if(memo.containsKey(c)) return memo.get(c);
        int ans = Integer.MAX_VALUE;
        for (int i = c; i < s.length(); i++) {
            if(dp[c][i]){
                ans = Math.min(ans,1+getAns2(s,i+1,dp,memo));
            }
        }
        memo.put(c,ans);
        return ans;
    }

    /**
     * 暴力递归改动态规划
     * @param s
     * @return
     */
    public int minCut3(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];  //存储i-j位置的字符串是否是回文字符串
        int len = s.length();
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j + i - 1 < len; j++) {
                int k = j + i - 1;
                dp[j][k] = s.charAt(j) == s.charAt(k) && (i < 3 || dp[j+1][k-1]);
            }
        }
        int[] cp = new int[s.length()];
        for (int i = s.length() - 2; i >= 0; i--) {
            if(dp[i][s.length() - 1]){
                cp[i] = 0;
                continue;
            }else{
                cp[i] = 1 + cp[i + 1];
                for (int j = i; j < s.length(); j++) {
                    if(dp[i][j]){
                        cp[i] = Math.min(cp[i],1+cp[j+1]);
                    }
                }
            }
        }
        return cp[0];
    }

    /**
     * 方法四：回溯算法（memo技术优化）
     * @param s
     * @return
     */
    public int minCut4(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int length = s.length();

        for (int len = 1; len <= length; len++) {
            for (int i = 0; i <= s.length() - len; i++) {
                int j = i + len - 1;
                dp[i][j] = s.charAt(i) == s.charAt(j) && (len < 3 || dp[i + 1][j - 1]);
            }
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        minCutHelper(s, 0, dp, 0,map);
        return min;
    }

    int min = Integer.MAX_VALUE;

    private void minCutHelper(String s, int start, boolean[][] dp, int num,HashMap<Integer,Integer> map) {
        if(map.containsKey(start)){
            min = Math.min(min,num + map.get(start));
            return;
        }
        if(dp[start][s.length() - 1]){
            min = Math.min(min,num);
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if(dp[start][i]){
                minCutHelper(s,i+1,dp,num + 1,map);
            }
        }
        if(min > num){
            map.put(start,min - num);
        }
    }
}
