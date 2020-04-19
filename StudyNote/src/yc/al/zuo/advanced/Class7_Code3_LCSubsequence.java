package yc.al.zuo.advanced;

import java.util.HashMap;

// 7-3 最长公共子序列
public class Class7_Code3_LCSubsequence {

    // 解法一： 暴力递归
    public static String longestCommonSubsequence1(String str1, String str2) {
        if (str1 == null || str2 == null) return null;
        return getAns(str1, str2, 0, 0, str1.length() - 1, str2.length() - 1, new HashMap<String,String>());
    }

    private static String getAns(String str1, String str2, int s1, int s2, int c1, int c2, HashMap<String,String> memo) {
        if (s1 == c1 && s2 == c2) {
            return str1.charAt(s1) == str2.charAt(s2) ? str1.charAt(s1) + "" : "";
        }
        String key = s1 + "@" + s2;
        if(memo.containsKey(key)) return memo.get(key);
        String res = "";
        if (s1 == c1) {
            res = str1.charAt(s1) == str2.charAt(s2) ? str1.charAt(s1) + "" : getAns(str1, str2, s1, s2 + 1, c1, c2,memo);
            memo.put(key,res);
            return res;
        }
        if (s2 == c2) {
            res = str1.charAt(s1) == str2.charAt(s2) ? str1.charAt(s1) + "" : getAns(str1, str2, s1 + 1, s2, c1, c2,memo);
            memo.put(key,res);
            return res;
        }
        if (str1.charAt(s1) == str2.charAt(s2)) {
            res = str1.charAt(s1) + "" + getAns(str1, str2, s1 + 1, s2 + 1, c1, c2,memo);
            memo.put(key,res);
            return res;
        } else {
            String res1 = getAns(str1, str2, s1 + 1, s2, c1, c2,memo);
            String res2 = getAns(str1, str2, s1, s2 + 1, c1, c2,memo);
            res = res1.length() > res2.length() ? res1 : res2;
            memo.put(key,res);
            return res;
        }
    }

    // 解法二： 暴力递归改动态规划
    public static String longestCommonSubsequence2(String str1, String str2) {
        if (str1 == null || str2 == null) return null;
        int c1 = str1.length();
        int c2 = str2.length();
        String[][] dp = new String[c1][c2];
        for (int i = c1 - 1; i >= 0; i--) {
            for (int j = c2 - 1; j >= 0; j--) {
                if(i == c1 - 1 && j == c2 - 1){
                    dp[i][j] =  str1.charAt(i) == str2.charAt(j) ? str1.charAt(i) + "" : "";
                }else if(i == c1 - 1){
                    dp[i][j] = str1.charAt(i) == str2.charAt(j) ? str1.charAt(i) + "" : dp[i][j+1];
                }else if(j == c2 - 1){
                    dp[i][j] = str1.charAt(i) == str2.charAt(j) ? str1.charAt(i) + "" : dp[i+1][j];
                }else{
                    if(str1.charAt(i) == str2.charAt(j)){
                        dp[i][j] = str1.charAt(i) + dp[i+1][j+1];
                    }else{
                        String res1 = dp[i][j+1];
                        String res2 = dp[i+1][j];
                        dp[i][j] = res1.length() > res2.length() ? res1 : res2;
                    }
                }
            }
        }
        return dp[0][0];
    }

    // 左神提供解法
    public static String lcse(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = getdp(chs1, chs2);
        int m = chs1.length - 1;
        int n = chs2.length - 1;
        char[] res = new char[dp[m][n]];
        int index = res.length - 1;
        while (index >= 0) {
            if (n > 0 && dp[m][n] == dp[m][n - 1]) {
                n--;
            } else if (m > 0 && dp[m][n] == dp[m - 1][n]) {
                m--;
            } else {
                res[index--] = chs1[m];
                m--;
                n--;
            }
        }
        return String.valueOf(res);
    }

    public static int[][] getdp(char[] str1, char[] str2) {
        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < str1.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
        }
        for (int j = 1; j < str2.length; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], str1[0] == str2[j] ? 1 : 0);
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp;
    }

    public static void main(String[] args) {
//        String str1 = "A1BC2D3EFGH45I6JK7LMN";
//        String str2 = "12OPQ3RST4U5V6W7XYZ";
        String str1 = "1A2C3D4B56";
        String str2 = "B1D23CA45B6A";
        System.out.println(longestCommonSubsequence1(str1, str2));
        System.out.println(longestCommonSubsequence2(str1, str2));
    }
}
