package my_code_sum_up.code_dp;

import java.util.HashMap;

// 不同的子序列
// 方法一： 暴力递归
// 方法二： 动态规划
public class Test115 {

    /**
     * 暴力递归(超时)
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        if (s.length() < t.length()) return 0;
        return getAns(s, 0, t, 0);
    }

    private int getAns(String s1, int c1, String s2, int c2) {
        if (c2 == s2.length()) {
            return 1;
        }
        if (c1 == s1.length()) {
            return 0;
        }
        int ans = 0;
        if (s1.charAt(c1) != s2.charAt(c2)) {
            ans += getAns(s1, c1 + 1, s2, c2);
        } else {
            ans += getAns(s1, c1 + 1, s2, c2 + 1);   // c2的当前字符匹配
            ans += getAns(s1, c1 + 1, s2, c2);
        }
        return ans;
    }

    // 暴力递归优化方案一： 只要s1的长度不够匹配上剩余的s2时，直接返回false（时间优化不明显）
    public int numDistinct2(String s, String t) {
        if (s.length() < t.length()) return 0;
        return getAns2(s, 0, t, 0);
    }

    private int getAns2(String s1, int c1, String s2, int c2) {
        //--------------修改的地方----------------
        if(s1.length() - c1 < s2.length() - c2){
            return 0;
        }
        //--------------修改的地方----------------
        if (c2 == s2.length()) {
            return 1;
        }
        if (c1 == s1.length()) {
            return 0;
        }
        int ans = 0;
        if (s1.charAt(c1) != s2.charAt(c2)) {
            ans += getAns2(s1, c1 + 1, s2, c2);
        } else {
            ans += getAns2(s1, c1 + 1, s2, c2 + 1);   // c2的当前字符匹配
            ans += getAns2(s1, c1 + 1, s2, c2);
        }
        return ans;
    }

    // 暴力递归优化方案二： 加memo技术做缓存(通过)
    public int numDistinct3(String s, String t) {
        if (s.length() < t.length()) return 0;
        HashMap<String,Integer> memo = new HashMap<String,Integer>();
        return getAns3(s, 0, t, 0,memo);
    }

    private int getAns3(String s1, int c1, String s2, int c2,HashMap<String,Integer> memo) {
        //--------------修改的地方----------------
        if(s1.length() - c1 < s2.length() - c2){
            return 0;
        }
        //--------------修改的地方----------------
        if (c2 == s2.length()) {
            return 1;
        }
        if (c1 == s1.length()) {
            return 0;
        }
        String key = c1 + "@" + c2;
        if(memo.containsKey(key)){
            return memo.get(key);
        }
        int ans = 0;
        if (s1.charAt(c1) != s2.charAt(c2)) {
            ans += getAns3(s1, c1 + 1, s2, c2,memo);
        } else {
            ans += getAns3(s1, c1 + 1, s2, c2 + 1,memo);   // c2的当前字符匹配
            ans += getAns3(s1, c1 + 1, s2, c2,memo);
        }
        memo.put(key,ans);
        return ans;
    }

    /**
     * 方法二： 动态规划(完全由暴力递归改造)
     * @param s
     * @param t
     * @return
     */
    public int numDistinct4(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = s.length(); i >= 0; i--) {
            for (int j = t.length(); j >= 0; j--) {
                if(j == t.length()){
                    // 如果t为空串的化，默认值为1
                    dp[i][j] = 1;
                } else if (i != s.length()){
                  if (s.charAt(i) == t.charAt(j)){
                      dp[i][j] = dp[i+1][j+1] + dp[i+1][j]; // 可以则当前的s[i]也可不选择当前的s[i]
                  } else {
                      dp[i][j] = dp[i+1][j];    // 如果不相等，则当前位置的值，s后面的元素是否匹配
                  }
                }
            }
        }
        return dp[0][0];
    }

    // 优化动态规划(列从右到左改造为从左到右)
    public int numDistinct5(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = s.length(); i >= 0; i--) {
            for (int j = 0; j <= t.length(); j++) {
                if(j == t.length()){
                    // 如果t为空串的化，默认值为1
                    dp[i][j] = 1;
                } else if (i != s.length()){
                    if (s.charAt(i) == t.charAt(j)){
                        dp[i][j] = dp[i+1][j+1] + dp[i+1][j]; // 可以则当前的s[i]也可不选择当前的s[i]
                    } else {
                        dp[i][j] = dp[i+1][j];    // 如果不相等，则当前位置的值，s后面的元素是否匹配
                    }
                }
            }
        }
        return dp[0][0];
    }

    // 优化动态规划（二维数组改造为一维数组）————空间上优化
    public int numDistinct6(String s, String t) {
        int[] dp = new int[t.length() + 1];
        for (int i = s.length(); i >= 0; i--) {
            for (int j = 0; j <= t.length(); j++) {
                if(j == t.length()){
                    // 如果t为空串的化，默认值为1
                    dp[j] = 1;
                } else if (i != s.length()){
                    if (s.charAt(i) == t.charAt(j)){
                        dp[j] = dp[j+1] + dp[j]; // 可以则当前的s[i]也可不选择当前的s[i]
                    } else {
                        dp[j] = dp[j];    // 如果不相等，则当前位置的值，s后面的元素是否匹配
                    }
                }
            }
        }
        return dp[0];
    }
}