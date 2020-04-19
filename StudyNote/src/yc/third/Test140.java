package yc.third;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// 单词拆分
public class Test140 {

    // 暴力递归（自己撸）， 击败80%以上
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

    // 方法一： 自己撸————超时
    public List<String> wordBreak2(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        int max = Integer.MIN_VALUE;
        for (String str : wordDict) {
            max = Math.max(str.length(),max);
            set.add(str);
        }
        List<String> res = new ArrayList<>();
        getAns(s,0,set,res,"",max);
        return res;
    }

    private void getAns(String s,int start, HashSet<String> set, List<String> res, String temp,int max) {
        if(start == s.length()){
            res.add(temp.substring(0,temp.length() - 1));
            return;
        }
        for (int i = start; i < start + max && i < s.length(); i++) {
            if(set.contains(s.substring(start,i + 1))){
                getAns(s,i + 1,set,res,temp + s.substring(start,i + 1) + " " ,max);
            }
        }
    }

    // 方法二————
    public List<String> wordBreak3(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < wordDict.size(); i++) {
            set.add(wordDict.get(i));
        }
        return wordBreakHelper(s, set, new HashMap<String, List<String>>());
    }

    private List<String> wordBreakHelper(String s, HashSet<String> set, HashMap<String, List<String>> map) {
        if (s.length() == 0) {
            return new ArrayList<>();
        }
        if (map.containsKey(s)) {
            return map.get(s);
        }
        List<String> res = new ArrayList<>();
        for (int j = 0; j < s.length(); j++) {
            //判断当前字符串是否存在
            if (set.contains(s.substring(j))) {
                //空串的情况，直接加入
                if (j == 0) {
                    res.add(s.substring(j));
                } else {
                    //递归得到剩余字符串的所有组成可能，然后和当前字符串分别用空格连起来加到结果中
                    List<String> temp = wordBreakHelper(s.substring(0, j), set, map);
                    for (int k = 0; k < temp.size(); k++) {
                        String t = temp.get(k);
                        res.add(t + " " + s.substring(j));
                    }
                }
            }
        }
        //缓存结果
        map.put(s, res);
        return res;
    }


}
