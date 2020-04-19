package yc.third;

import java.util.HashMap;

//  132. 分割回文串 II
public class Test132 {

    // 普通分治递归
    public int minCut(String s) {
        int len = s.length();
        // dp表示字符串s从i位置到j位置的元素是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 遍历，从长度1开始
        for (int l = 1; l <= len; l++) {
            for (int i = 0; i + l <= len; i++) {
                int j = i + l - 1;
                dp[i][j] = s.charAt(i) == s.charAt(j) && (l < 3 || dp[i+1][j-1]);
            }
        }
        return getAns(s,0,dp);
    }

    private int getAns(String s, int start, boolean[][] dp) {
        if(dp[start][s.length() - 1]){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = start; i < s.length(); i++) {
            if(dp[start][i]){
                min = Math.min(min,1 + getAns(s,i+1,dp));
            }
        }
        return min;
    }

    // 使用memo技术进行优化
    public int minCut2(String s) {
        int len = s.length();
        // dp表示字符串s从i位置到j位置的元素是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 遍历，从长度1开始
        for (int l = 1; l <= len; l++) {
            for (int i = 0; i + l <= len; i++) {
                int j = i + l - 1;
                dp[i][j] = s.charAt(i) == s.charAt(j) && (l < 3 || dp[i+1][j-1]);
            }
        }
        HashMap<Integer,Integer> memo = new HashMap<Integer, Integer>();
        return getAns2(s,0,dp,memo);
    }

    private int getAns2(String s, int start, boolean[][] dp,HashMap<Integer,Integer> memo) {
        if(dp[start][s.length() - 1]){
            return 0;
        }
        if(memo.containsKey(start)) return memo.get(start);
        int min = Integer.MAX_VALUE;
        for (int i = start; i < s.length(); i++) {
            if(dp[start][i]){
                min = Math.min(min,1 + getAns2(s,i+1,dp,memo));
            }
        }
        memo.put(start,min);
        return min;
    }

    // 使用动态规划进行优化
    public int minCut3(String s) {
        int len = s.length();
        // dp表示字符串s从i位置到j位置的元素是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 遍历，从长度1开始
        for (int l = 1; l <= len; l++) {
            for (int i = 0; i + l <= len; i++) {
                int j = i + l - 1;
                dp[i][j] = s.charAt(i) == s.charAt(j) && (l < 3 || dp[i+1][j-1]);
            }
        }
        //创建结果dp数组
        int[] cp = new int[s.length()];
        for (int i = s.length() - 2; i >= 0; i--) {
            if(dp[i][s.length() - 1]){
                cp[i] = 0;
            }else{
                cp[i] = cp[i - 1] + 1;
                for (int j = i + 1; j < s.length() - 1; j++) {
                    if(dp[i][j]){
                        cp[i] = Math.min(cp[i],1 + cp[j + 1]);
                    }
                }
            }
        }
        return cp[0];
    }

    // 解法三： 回溯算法
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
