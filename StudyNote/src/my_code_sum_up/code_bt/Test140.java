package my_code_sum_up.code_bt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// 单词拆分Ⅱ
// 方法一： 回溯
// 方法二： 递归
public class Test140 {

    /**
     * 方法一：回溯算法
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
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

    /**
     * 方法二：memo技术优化递归
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak2(String s, List<String> wordDict) {
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
