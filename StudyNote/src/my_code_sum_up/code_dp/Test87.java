package my_code_sum_up.code_dp;

import java.util.HashMap;

//87. 扰乱字符串
// 方法一：暴力递归
// 方法二：动态规划
public class Test87 {

    /**
     * 方法一： 暴力递归
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble1(String s1, String s2){
        // 首先如果两个字符串长度不一样一定为false’
        if(s1.equals(s2)) return true;
        if(s1.length() != s2.length()) return false;
        int[] flag = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            flag[s1.charAt(i) - 'a']++;
            flag[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < flag.length; i++) {
            if(flag[i] != 0){
                return false;  // 存在啊u不一样的字母
            }
        }
        // 进入暴力递归
        for (int i = 1; i < s1.length(); i++) {
            if(isScramble1(s1.substring(0,i),s2.substring(0,i)) && isScramble1(s1.substring(i),s2.substring(i))){
                return true;
            }
            if(isScramble1(s1.substring(0,i),s2.substring(s1.length() - i))
                    && isScramble1(s1.substring(i),s2.substring(0,s2.length() - i))){
                return true;
            }
        }
        return false;
    }

    // memo技术优化
    public boolean isScramble2(String s1, String s2){
        return process(s1,s2,new HashMap<String, Boolean>());
    }

    private boolean process(String s1, String s2, HashMap<String,Boolean> memo){
        String key = s1 + "@" + s2;
        if(memo.containsKey(key)){
            return memo.get(key);
        }
        // 首先如果两个字符串长度不一样一定为false’
        if(s1.equals(s2)) {
            memo.put(key,true);
            return true;
        }
        if(s1.length() != s2.length()){
            memo.put(key,false);
            return false;
        }
        int[] flag = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            flag[s1.charAt(i) - 'a']++;
            flag[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < flag.length; i++) {
            if(flag[i] != 0){
                memo.put(key,false);
                return false;  // 存在啊u不一样的字母
            }
        }
        // 进入暴力递归
        for (int i = 1; i < s1.length(); i++) {
            if(process(s1.substring(0,i),s2.substring(0,i),memo) && process(s1.substring(i),s2.substring(i),memo)){
                memo.put(key,true);
                return true;
            }
            if(process(s1.substring(0,i),s2.substring(s1.length() - i),memo)
                    && process(s1.substring(i),s2.substring(0,s2.length() - i),memo)){
                memo.put(key,true);
                return true;
            }
        }
        memo.put(key,false);
        return false;
    }

    /**
     * 方法二： 动态规划(时间复杂度  N4)
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble3(String s1, String s2) {
        // 首先如果两个字符串长度不一样一定为false’
        if(s1.equals(s2)) return true;
        if(s1.length() != s2.length()) return false;
        int[] flag = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            flag[s1.charAt(i) - 'a']++;
            flag[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < flag.length; i++) {
            if(flag[i] != 0){
                return false;  // 存在啊u不一样的字母
            }
        }
        //创建dp数组进行遍历
        int n = s1.length();
        boolean[][][] dp = new boolean[n + 1][n][n];   //表示长度为i的字符串以jk开头的字符串是否可匹配
        for (int i = 1; i <= n; i++) {
            // 从长度为1开始遍历
            for (int j = 0; j + i <= n; j++) {
                for (int k = 0; k + i <= n; k++) {
                    if(i == 1){
                        dp[i][j][k] = s1.charAt(j) == s2.charAt(k);
                    }else{
                        for (int l = 1; l < i; l++) {
                            dp[i][j][k] = (dp[l][j][k] && dp[i - l][j+l][k+l])
                                    || (dp[l][j][k+i-l] && dp[i-l][j+l][k]);
                            if(dp[i][j][k]){
                                break;  // 如果已经出现了true，不用继续进行for循环了
                            }
                        }
                    }
                }
            }
        }
        return dp[n][0][0];
    }
}
