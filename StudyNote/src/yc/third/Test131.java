package yc.third;

import java.util.ArrayList;
import java.util.List;

// 131. 分割回文串
public class Test131 {

    //  解法一： 分治递归的思想：
    public List<List<String>> partition(String s) {
        return getAns(s,0,s.length());
    }

    private List<List<String>> getAns(String s, int i, int length) {
        if(i == length){
            List<String> list = new ArrayList<>();
            List<List<String>> ans = new ArrayList<>();
            ans.add(list);
            return ans;
        }
        List<List<String>> ans = new ArrayList<>();
        for (int j = i; j < length; j++) {
            if(isHuiwen(s,i,j)){
                String cur = s.substring(i,j+1);
                for(List<String> list : getAns(s,j+1,length)){
                    list.add(0,cur);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    // 判断当前子串是否是回文串
    private boolean isHuiwen(String str, int s, int e){
        if(s == e){
            return true;
        }
        while(s < e){
            if(str.charAt(s++) != str.charAt(e--)){
                return false;
            }
        }
        return true;
    }

    // 优化分治递归（使用dp数组保存是否是回文串的信息）
    public List<List<String>> partition2(String s) {
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
        return getAns2(s,0,len,dp);
    }

    private List<List<String>> getAns2(String s, int i, int length, boolean[][] dp) {
        if(i == length){
            List<String> list = new ArrayList<>();
            List<List<String>> ans = new ArrayList<>();
            ans.add(list);
            return ans;
        }
        List<List<String>> ans = new ArrayList<>();
        for (int j = i; j < length; j++) {
            if(dp[i][j]){
                String cur = s.substring(i,j+1);
                for(List<String> list : getAns2(s,j+1,length,dp)){
                    list.add(0,cur);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    // 解法二； 经典的回溯算法
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
