package my_code_sum_up.code_dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// 单词拆分
// 方法一： memo技术优化暴力递归
// 方法二： 回溯算法
public class Test139 {

    /**
     * 方法一：memo技术优化暴力递归---自己想的方法——————(完美解法)
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // 创建字典中字符串存在的set，创建的同时记录字典中最长的字符串的长度
        HashSet<String> set = new HashSet<>();
        int max = Integer.MIN_VALUE;
        for (String str : wordDict) {
            max = Math.max(str.length(),max);
            set.add(str);
        }
        // 记录map，记录重复的计算的结果
        HashMap<Integer,Boolean> map = new HashMap<>();
        return getResult(s,0,set,max,map);
    }

    /**
     *
     * @param s
     * @param start  表示索引开始位置
     * @param set    字段中字符串的集合
     * @param max    每次遍历s时不可以超出max长度，否则肯定为false
     * @param map    记忆表
     * @return
     */
    private boolean getResult(String s, int start, HashSet<String> set,int max,HashMap<Integer,Boolean> map) {
        if(start == s.length()){
            return true;
        }
        if(map.containsKey(start)){
            return map.get(start);
        }
        // 关键步骤： 遍历的时候加上max的长度限制，避免无限遍历至s结尾
        for (int i = start; i < start + max && i < s.length(); i++) {
            if(set.contains(s.substring(start,i + 1))){
                if(getResult(s,i+1,set,max,map)){
                    map.put(start,true);
                    return true;
                }
            }
        }
        map.put(start,false);
        return false;
    }

    /**
     * 方法二：  回溯算法 ————  使用字典中的子符串组成字符串，如果匹配上了，则返回true)(不过)
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        return getAns(s,wordDict,"");
    }

    private boolean getAns(String s, List<String> wordDict, String temp) {
        // 如果长度超标，肯定无法匹配
        if(temp.length() > s.length()){
            return false;
        }
        //判断此时对应的字符是否全部相等
        for (int i = 0; i < temp.length(); i++) {
            if (s.charAt(i) != temp.charAt(i)) {
                return false;
            }
        }
        // 如果相等判断是否匹配
        if(temp.length() == s.length()){
            return true;
        }
        for (int i = 0; i < wordDict.size(); i++) {
            // 选择当前字符串
            if(getAns(s,wordDict,temp + wordDict.get(i))){
                return true;
            }
            // 不选择当前字符串
        }
        return false;
    }

    // 优化： 线判断s中的是否有字典中没出现过的字符，如果有，则直接返回false
    // 再利用memo技术记录之前遍历过的结果
    public boolean wordBreak3(String s, List<String> wordDict) {
        HashSet<Character> set = new HashSet<Character>();
        for (String str : wordDict) {
            for (int i = 0; i < str.length(); i++) {
                set.add(str.charAt(i));
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if(!set.contains(s.charAt(i))){
                return false;
            }
        }
        HashMap<String,Boolean> memo = new HashMap<String,Boolean>();
        return process(s,wordDict,"",memo);
    }

    private boolean process(String s, List<String> wordDict, String temp, HashMap<String, Boolean> memo) {
        // 如果长度超标，肯定无法匹配
        if(temp.length() > s.length()){
            return false;
        }
        if(memo.containsKey(temp)){
            return memo.get(temp);
        }
        //判断此时对应的字符是否全部相等
        for (int i = 0; i < temp.length(); i++) {
            if (s.charAt(i) != temp.charAt(i)) {
                return false;
            }
        }
        // 如果相等判断是否匹配
        if(temp.length() == s.length()){
            return true;
        }
        for (int i = 0; i < wordDict.size(); i++) {
            // 选择当前字符串
            if(process(s,wordDict,temp + wordDict.get(i),memo)){
                memo.put(temp,true);
                return true;
            }
            // 不选择当前字符串
        }
        memo.put(temp,false);
        return false;
    }
}
