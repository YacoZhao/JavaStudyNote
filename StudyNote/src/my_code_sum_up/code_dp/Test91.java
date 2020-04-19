package my_code_sum_up.code_dp;

// 91. 解码方法
// 方法一：
// 方法二： 动态规划
public class Test91 {

    /**
     * 方法一： 暴力递归求解
     * @param s
     * @return
     */
    public int numDecodings(String s){
        int n = s.length();
        return getAns(s,0);
    }

    private int getAns(String s, int n) {
        // 划分到了最后，返回1
        if(n == s.length()){
            return 1;
        }
        // 如果当前字符以0开头，那么直接返回0；
        if(s.charAt(n) == '0'){
            return 0;
        }
        int ans1 = getAns(s,n+1);    // 当前位置字符单独解码的情况下划分种类
        int ans2 = 0;
        if(n < s.length() - 1 && (s.charAt(n) - '0') * 10 + (s.charAt(n + 1) - '0') <= 26){
            ans2 = getAns(s,n + 2);
        }
        return ans1 + ans2;
    }

    /**
     * 方法二：动态规划求解
     * @param s
     * @return
     */
    public int numDecodings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[len] = 1;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                dp[i] = 0;
            } else if (i < len - 1 && ((s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0')) <= 26) {
                dp[i] = dp[i + 1] + dp[i + 2];
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }
}
