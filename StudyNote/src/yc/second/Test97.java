package yc.second;

import java.util.HashMap;

public class Test97 {

    // 解法一： 回溯法
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()){
            return false;
        }
        return getAns(s1,0,s2,0,s3,0);
    }

    private boolean getAns(String s1, int i, String s2, int j, String s3, int k) {
        // 如果三个指针都到达了末尾，返回true
        if(i == s1.length() && j == s2.length() && k == s3.length()){
            return true;
        }
        // 如果s1到达了末尾，那么一直比较s2即可
        if(i == s1.length()){
            while(j != s2.length()){
                if(s2.charAt(j) != s3.charAt(k)){
                    return false;
                }
                j++;
                k++;
            }
            return true;
        }
        // 如果s2到达了末尾，那么一直比较s1即可
        if(j == s2.length()){
            while(i != s1.length()){
                if(s1.charAt(i) != s3.charAt(k)){
                    return false;
                }
                i++;
                k++;
            }
            return true;
        }
        // 如果当前位置s1和s3相等，递归下一层，查看是否可以通过
        if(s1.charAt(i) == s3.charAt(k)){
            if(getAns(s1,i+1,s2,j,s3,k+1)){
                return true;
            }
        }
        // 如果当前位置s1和s3相等，进行下去
        if(s2.charAt(j) == s3.charAt(k)){
            if(getAns(s1,i,s2,j+1,s3,k+1)){
                return true;
            }
        }
        // 如果以上两个都没有匹配上，那么返回false
        return false;
    }

    // 解法一： 优化（加memoization 技术优化）
    public boolean isInterleave2(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()){
            return false;
        }
        HashMap<String,Integer> memo = new HashMap<String,Integer>();
        return getAns2(s1,0,s2,0,s3,0,memo);
    }

    private boolean getAns2(String s1, int i, String s2, int j, String s3, int k, HashMap<String,Integer> memo) {
        // 创建key值，便于后面使用
        String key = i + "@" + j;
        // 如果memo中存在当前的key值，检查当前key对应的值是否为1，1表示false，0表示true
        if(memo.containsKey(key)){
            return memo.get(key) == 1;
        }
        // 如果三个指针都到达了末尾，返回true
        if(i == s1.length() && j == s2.length() && k == s3.length()){
            memo.put(key,1);
            return true;
        }
        // 如果s1到达了末尾，那么一直比较s2即可
        if(i == s1.length()){
            while(j != s2.length()){
                if(s2.charAt(j) != s3.charAt(k)){
                    memo.put(key,0);
                    return false;
                }
                j++;
                k++;
            }
            memo.put(key,1);
            return true;
        }
        // 如果s2到达了末尾，那么一直比较s1即可
        if(j == s2.length()){
            while(i != s1.length()){
                if(s1.charAt(i) != s3.charAt(k)){
                    memo.put(key,0);
                    return false;
                }
                i++;
                k++;
            }
            memo.put(key,1);
            return true;
        }
        // 如果当前位置s1和s3相等，递归下一层，查看是否可以通过
        if(s1.charAt(i) == s3.charAt(k)){
            if(getAns2(s1,i+1,s2,j,s3,k+1,memo)){
                memo.put(key,1);
                return true;
            }
        }
        // 如果当前位置s1和s3相等，进行下去
        if(s2.charAt(j) == s3.charAt(k)){
            if(getAns2(s1,i,s2,j+1,s3,k+1,memo)){
                memo.put(key,1);
                return true;
            }
        }
        // 如果以上两个都没有匹配上，那么返回false
        memo.put(key,0);
        return false;
    }

    // 解法二： 动态规划
    public boolean isInterleave3(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()){
            return false;
        }
        // 创建dp数组（表示s1的前i个字符和s2的前j个字符是否可以构成s3的前i+j个字符）
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if(i == 0 && j == 0){
                    dp[i][j] = true;
                }else if(j == 0){
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i - 1);
                }else if(i == 0){
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
                }else{
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    // 优化动态规划
    public boolean isInterleave4(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()){
            return false;
        }
        // 创建dp数组（表示s1的前i个字符和s2的前j个字符是否可以构成s3的前i+j个字符）
        boolean[] dp = new boolean[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if(i == 0 && j == 0){
                    dp[j] = true;
                }else if(j == 0){
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i - 1);
                }else if(i == 0){
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
                }else{
                    dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s2.length()];
    }
}
