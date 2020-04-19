package yc.third;

import org.omg.CORBA.INTERNAL;

import java.util.HashMap;

// 不同的子序列
public class Test115 {
    // 解法一： 递归（当前的位置选择或者不选择）
    public int numDistinct(String s, String t) {
        return getAns(s,0,t,0);
    }

    private int getAns(String s, int i, String t, int j) {
        int count = 0;
        // 递归结束条件
        if(j == t.length()){
            return 1;
        }
        if(i == s.length()){
            return 0;
        }
        // 递归过程
        if(s.charAt(i) == t.charAt(j)){
            // 如果当前的位置s和t上的元素相同，那么此处可以选择，也可以不选择
            count = getAns(s,i+1,t,j+1) + getAns(s,i+1,t,j);
        }else{
            // 如果不想等，s跳向下一位，t不动
            count = getAns(s,i+1,t,j);
        }
        return count;
    }

    // 解法一： 采用memo技术优化暴力递归(AC通过)
    public int numDistinct2(String s, String t) {
        HashMap<String,Integer> memo = new HashMap<String,Integer>();
        return getAns2(s,0,t,0,memo);
    }

    private int getAns2(String s, int i, String t, int j, HashMap<String, Integer> memo) {
        String key = i + "!" + j;
        if(memo.containsKey(key)){
            return memo.get(key);
        }
        int count = 0;
        // 递归结束条件
        if(j == t.length()){
            memo.put(key,1);
            return 1;
        }
        if(i == s.length()){
            memo.put(key,0);
            return 0;
        }
        // 递归过程
        if(s.charAt(i) == t.charAt(j)){
            // 如果当前的位置s和t上的元素相同，那么此处可以选择，也可以不选择
            count = getAns2(s,i+1,t,j+1,memo) + getAns2(s,i+1,t,j,memo);
        }else{
            // 如果不想等，s跳向下一位，t不动
            count = getAns2(s,i+1,t,j,memo);
        }
        memo.put(key,count);
        return count;
    }

    // 解法二： 递归 + 回溯 （设置全局变量）
    int count = 0;
    public int numDistinct3(String s, String t){
        HashMap<String, Integer> map = new HashMap<String,Integer>();
        getAns3(s,0,t,0,map);
        return count;
    }

    private void getAns3(String s, int i, String t, int j,HashMap<String, Integer> map) {
        // 递归终止条件
        if(j == t.length()){
            count++;
            return;
        }
        if(i == s.length()){
            return;
        }
        String key = i + "@" + j;
        if(map.containsKey(key)){
            count += map.get(key);
            return;
        }
        int count_pre = count;
        // 首先如果当前值相等，t进一位向下搜索
        if(s.charAt(i) == t.charAt(j)){
            getAns3(s,i+1,t,j+1,map);
        }
        // 获取，j保持原样，s继续向下搜索
        getAns3(s,i+1,t,j,map);
        // 将增量添加到map中去
        map.put(key,count - count_pre);
    }

    // 解法三： 动态规划(仿照解法一改造成动态规划)
    public int numDistinct4(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            dp[i][t.length()] = 1;
        }
        // 此段代码可以省略，数组默认为0；
//        for (int j = 0; j < t.length(); j++) {
//           dp[s.length()][j] = 0;
//        }
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = t.length() - 1; j >= 0; j--) {
                if(s.charAt(i) == t.charAt(j)){
                    dp[i][j] = dp[i+1][j+1] + dp[i+1][j];
                }else{
                    dp[i][j] = dp[i+1][j];
                }
            }
        }
        return dp[0][0];
    }

    // 解法三； 优化动态规划（降低dp数组的维度———— 降成二位数组)
    public int numDistinct5(String s, String t) {
        int[][] dp = new int[2][t.length() + 1];
        dp[0][t.length()] = 1;
        dp[1][t.length()] = 1;
        int x = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            x = (s.length() - 1 - i) % 2;
            for (int j = t.length() - 1; j >= 0; j--) {
                if(s.charAt(i) == t.charAt(j)){
                    dp[x][j] = dp[(x+1) % 2][j] + dp[(x+1) % 2][j + 1];
                }else{
                    dp[x][j] = dp[(x+1)%2][j];
                }
            }
        }
        return dp[x][0];
    }

}
