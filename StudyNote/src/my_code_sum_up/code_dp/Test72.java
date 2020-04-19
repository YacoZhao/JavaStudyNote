package my_code_sum_up.code_dp;

import java.util.HashMap;

// 72. 编辑距离
// 方法一：暴力递归
// 方法二：动态规划
public class Test72 {

    /**
     * 方法一： 暴力递归
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance1(String word1, String word2) {
        if(word1.isEmpty()) return word2.length();
        if(word2.isEmpty()) return word1.length();
        HashMap<String,Integer> memo = new HashMap<>();
        return getAns(0,0,word1,word2,memo);
    }

    private int getAns(int c1, int c2, String word1, String word2,HashMap<String,Integer> memo) {
        // 如果其中一个到了尽头，则直接加上另一个的剩余长度
        if(c1 == word1.length()){
            return word2.length() - c2;
        }else if(c2 == word2.length()){
            return word1.length() - c1;
        }
        String key = c1 + "@" + c2;
        if(memo.containsKey(key)){
            return memo.get(key);
        }
        int ans = 0;
        // 如果当前两个字符相等，则不进行任何操作，两者都进入下一位
        // 如果当前两个字符不想等，则考虑增删改之后的最小操作数+1
        if(word1.charAt(c1) == word2.charAt(c2)){
            ans = getAns(c1+1,c2+1,word1,word2,memo);
        }else{
            ans = 1 + Math.min(getAns(c1+1,c2,word1,word2,memo),
                            Math.min(getAns(c1,c2+1,word1,word2,memo),
                            getAns(c1+1,c2+1,word1,word2,memo)));
        }
        memo.put(key,ans);
        return ans;
    }

    /**
     * 动态规划求解编辑距离问题
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        int m = word1.length();  //计算出word1字符串的长度
        int n = word2.length();  //计算出word2字符串的长度
        //创建动态规划数组  —— dp[i][j]表示word1的前i个字符串转化为word2的前j个字符串所使用的最少操作数
        int[][] dp = new int[m + 1][n + 1];
        //循环遍历数组
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if(i == 0){
                    dp[i][j] = j;
                }else if(j == 0){
                    dp[i][j] = i;
                }else{
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    }
                }
            }
        }
        return dp[m][n];
    }

    // 优化动态规划
    public int minDistance3(String word1, String word2) {
        int m = word1.length();  //计算出word1字符串的长度
        int n = word2.length();  //计算出word2字符串的长度
        //创建动态规划数组  —— dp[i][j]表示word1的前i个字符串转化为word2的前j个字符串所使用的最少操作数
        int[][] dp = new int[2][n + 1];
        //循环遍历数组
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if(i == 0){
                    dp[i][j] = j;
                }else if(j == 0){
                    dp[i % 2][j] = i;
                }else{
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i % 2][j] = dp[(i+1) % 2][j - 1];
                    } else {
                        dp[i % 2][j] = Math.min(dp[(i+1) % 2][j], Math.min(dp[i % 2][j - 1], dp[(i+1) % 2][j - 1])) + 1;
                    }
                }
            }
        }
        return dp[m % 2][n];
    }
}
