package my_code_sum_up.code_dp;


// 10. 正则表达式匹配（困难）
// 方法一： 暴力递归
// 方法二： 动态规划
public class Test10 {

    /**
     * 方法一： 暴力递归
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch1(String s, String p) {
        // 递归出口
        if(p.isEmpty()) return s.isEmpty();
        // 条件一;
        boolean flag = !s.isEmpty() && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0));
        // 两种情况： 1.当p的第二位是'*'时，直接让p跳两位
        if(p.length() >= 2 && p.charAt(1) == '*'){
            return isMatch1(s,p.substring(2)) || (flag && isMatch1(s.substring(1),p));
        }else{
            return flag && isMatch1(s.substring(1),p.substring(1));
        }
    }

    /**
     * 方法二： 动态规划
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        if(p.isEmpty()) return s.isEmpty();
        // 创建dp数组
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // 初始化
        dp[0][0] = true;
        for (int j = 2; j <= p.length(); j++) {
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        }
        // 遍历填数组
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if(p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)){
                    dp[i + 1][j + 1] = dp[i][j];
                }else if(p.charAt(j) == '*'){
                    if(p.charAt(j - 1) != '.' && p.charAt(j - 1) != s.charAt(i)){
                        dp[i+1][j+1] = dp[i + 1][j-1];   // 如果p的前面一个字符和s的当前字符匹配不了，那么p直接删两个元素
                    }else{
                        dp[i+1][j+1] = dp[i+1][j] || dp[i][j+1] || dp[i + 1][j -1];
                    }
                }
            }
        }
        // 返回结果
        return dp[s.length()][p.length()];
    }

    // 动态规划优化(dp数组降维)
    public boolean isMatch3(String s, String p) {
        if(p.isEmpty()) return s.isEmpty();
        // 创建dp数组
        boolean[][] dp = new boolean[2][p.length() + 1];
        // 初始化
        dp[0][0] = true;
        for (int j = 2; j <= p.length(); j++) {
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        }
        // 遍历填数组
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if(p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)){
                    dp[(i + 1) % 2][j + 1] = dp[i % 2][j];
                }else if(p.charAt(j) == '*'){
                    if(p.charAt(j - 1) != '.' && p.charAt(j - 1) != s.charAt(i)){
                        dp[(i + 1) % 2][j+1] = dp[(i + 1) % 2][j-1];   // 如果p的前面一个字符和s的当前字符匹配不了，那么p直接删两个元素
                    }else{
                        dp[(i + 1) % 2][j+1] = dp[(i + 1) % 2][j] || dp[i % 2][j+1] || dp[(i + 1) % 2][j -1];
                    }
                }else{
                    dp[(i + 1) % 2][j+1] = false;
                }
            }
            dp[i % 2][0] = false;
        }
        // 返回结果
        return dp[s.length() % 2][p.length()];
    }
}
