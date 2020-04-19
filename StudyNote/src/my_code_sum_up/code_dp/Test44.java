package my_code_sum_up.code_dp;

//44. 通配符匹配
// 方法一；暴力递归
// 方法二：动态规划
public class Test44 {
    /**
     * 动态规划求解通配符匹配问题
     * @param s 字符串
     * @param p 匹配符
     * @return
     */
    public boolean isMatch(String s, String p) {
        if(p.isEmpty()) return s.isEmpty();
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //初始化
        dp[0][0] = true;
        // 当s为空时，p全部为'*’才有可能匹配成功
        for (int j = 1; j <= p.length(); j++) {
            dp[0][j] = dp[0][j-1] && p.charAt(j - 1) == '*';
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if(p.charAt(j) == '?' || s.charAt(i) == p.charAt(j)){
                    dp[i + 1][j + 1] = dp[i][j];
                }else if(p.charAt(j) == '*'){
                    dp[i + 1][j + 1] = dp[i][j] || dp[i][j + 1] || dp[i + 1][j];
                }
            }
        }
        // 返回结果
        return dp[s.length()][p.length()];
    }

    // 动态规划优化（时间没有提高多少）
    public boolean isMatch2(String s, String p) {
        if(p.isEmpty()) return s.isEmpty();
        boolean[][] dp = new boolean[2][p.length() + 1];
        dp[0][0] = true;
        // 当s为空时，p全部为'*’才有可能匹配成功
        for (int j = 1; j <= p.length(); j++) {
            dp[0][j] = dp[0][j-1] && p.charAt(j - 1) == '*';
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if(p.charAt(j) == '?' || s.charAt(i) == p.charAt(j)){
                    dp[(i + 1) % 2][j + 1] = dp[i % 2][j];
                }else if(p.charAt(j) == '*'){
                    dp[(i + 1) % 2][j + 1] = dp[i % 2][j] || dp[i % 2][j + 1] || dp[(i + 1) % 2][j];
                }else{   // 改动的地方：不可以匹配地方要置为0
                    dp[(i + 1) % 2][j + 1] = false;
                }
            }
            dp[i % 2][0] = false;
        }
        // 返回结果
        return dp[s.length() % 2][p.length()];
    }
}
