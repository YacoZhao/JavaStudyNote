package my_code_sum_up.code_dp;

import java.util.ArrayList;
import java.util.List;

// 131. 分割回文串
// 方法一：暴力递归
// 方法二：动态规划
// 方法三；回溯算法
public class Test131 {

    /**
     * 方法一：暴力递归
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        return getAns(s,0);
    }

    private List<List<String>> getAns(String str, int s){
        // 首先递归出口
        if(s == str.length()){
            List<List<String>> res = new ArrayList<>();
            res.add(new ArrayList<String>());
            return res;
        }
        // 创建返回结果
        List<List<String>> ans = new ArrayList<>();
        for (int i = s; i < str.length(); i++) {
            if(isHuiWen(str.substring(s,i+1))){
                String temp = str.substring(s,i+1);
                // 首先返回右边的所有结果
                List<List<String>> next = getAns(str,i+1);
                for (List<String> list : next) {
                    list.add(0,temp);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    // 编写一个函数，判断当前的字符是否时回文字符
    private boolean isHuiWen(String s){
        if(s.length() == 1) return true;
        int start = 0;
        int end = s.length() - 1;
        while(start < end){
            if(s.charAt(start) != s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    /**
     * 方法二： 动态规划（先使用dp数组保存回文信息）
     * @param s
     * @return
     */
    public List<List<String>> partition2(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];  //存储i-j位置的字符串是否是回文字符串
        int len = s.length();
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j + i - 1 < len; j++) {
                int k = j + i - 1;
                dp[j][k] = s.charAt(j) == s.charAt(k) && (i < 3 || dp[j+1][k-1]);
            }
        }
        return getAns2(s,0,dp);
    }

    private List<List<String>> getAns2(String str, int s, boolean[][] dp) {
        // 首先递归出口
        if(s == str.length()){
            List<List<String>> res = new ArrayList<>();
            res.add(new ArrayList<String>());
            return res;
        }
        // 创建返回结果
        List<List<String>> ans = new ArrayList<>();
        for (int i = s; i < str.length(); i++) {
            if(dp[s][i]){
                String temp = str.substring(s,i+1);
                // 首先返回右边的所有结果
                List<List<String>> next = getAns2(str,i+1,dp);
                for (List<String> list : next) {
                    list.add(0,temp);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    /**
     * 方法三：回溯
     * @param s
     * @return
     */
    public List<List<String>> partition3(String s) {
        int len = s.length();
        // dp表示字符串s从i位置到j位置的元素是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 遍历，从长度1开始
        for (int l = 1; l <= len; l++) {
            for (int i = 0; i + l <= len; i++) {
                int j = i + l - 1;
                dp[i][j] = s.charAt(i) == s.charAt(j) && (l < 3 || dp[i+1][j-1]);
            }
        }
        List<List<String>> res = new ArrayList<>();
        backTrack(s,0,dp,new ArrayList<>(),res);
        return res;
    }

    private void backTrack(String s, int start, boolean[][] dp, ArrayList<String> path, List<List<String>> res) {
        if(start == s.length()){
            res.add(new ArrayList<>(path));
            return;
        }
        // 在不同的位置分割字符串
        for (int i = start; i < s.length(); i++) {
            if(dp[start][i]){
                String cur = s.substring(start,i+1);
                path.add(cur);
                // 追溯进入下一层
                backTrack(s,i+1,dp,path,res);
                // 回溯
                path.remove(path.size() - 1);
            }
        }
    }
}

