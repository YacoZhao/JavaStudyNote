package my_code_sum_up.code_dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

//单词拆分
public class Test140 {

    // 暴力递归（自己撸）
    public List<String> wordBreak(String s, List<String> wordDict) {
        // 首先将字典加入一个集合中
        HashSet<String> set = new HashSet<>();
        int maxSize = Integer.MIN_VALUE; // 定义一个变量字典中字符串的最大长度
        for (String str : wordDict) {
            if(!set.contains(str)){
                maxSize = Math.max(maxSize,str.length());
                set.add(str);
            }
        }
        // 进入递归
        HashMap<Integer,List<String>> memo = new HashMap<>();              // 记录哈希表
        List<String> pre = getRes(s,0,set,maxSize, s.length(), memo);   //  存储计算结果，每个字符串钱前面都会多出一个空格，把空格删掉
        List<String> ans = new ArrayList<>();
        for (String pr : pre) {
            ans.add(pr.substring(1));
        }
        return ans;   // 返回结果
    }

    // 递归题
    private List<String> getRes(String s, int c, HashSet<String> set, int maxSize, int length, HashMap<Integer, List<String>> memo) {
        List<String> ans = new ArrayList<>();   // 创建一个结果集
        if(c == length) {                       // 如果当前指针走到了字符串尾部，将ans加入"",使得list不为null
            ans.add("");
            return ans;
        }
        if(memo.containsKey(c)) return memo.get(c);
        for (int i = c; i < c + maxSize && i < length; i++) {
            String cur = s.substring(c,i + 1);
            if(set.contains(cur)){         // 如果当前的字符串在字典中
                // 首先将后面的字符串的计算结果求出来，如果size为0，表示后面的为u发划分，那么就不做任何处理
                // 凡是如果后面可以划分的化，即list有参数，则将" "与此字符串的拼接体加到遍历字符串上面去
                List<String> next = getRes(s, i+1, set, maxSize, length, memo);
                if(next.size() != 0) {
                    for (String temp : next) {
                        ans.add(" " + cur + temp);
                    }
                }
            }
        }
        memo.put(c,ans);
        return ans;
    }
}
