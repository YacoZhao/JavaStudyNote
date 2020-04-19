package yc.first;

//44. 通配符匹配
public class Test44 {
    /**
     * 动态规划求解通配符匹配问题
     * @param s 字符串
     * @param p 匹配符
     * @return
     */
    public boolean isMatch(String s, String p) {
        //首先确定动态规划数组的属性、
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];   //表示s的前i个字符和p的前i个字符是否可以匹配
        dp[0][0] = true; //两个都为空字符串时，肯定可以匹配

        //分析可知，当s不为空时，q如果为空，则结果必为false，所以dp[i][0]必为false，不用修改
        //但是当s为空时，如果q为"*",匹配成功
        for (int i = 1; i <= n; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 1];
            }
        }

        //循环遍历，向数组中添加属性
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        //返回结果
        return dp[m][n];
    }
}
