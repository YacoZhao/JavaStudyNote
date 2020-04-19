package yc.first;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//30. 串联所有单词的子串
public class Test30 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();  //声明返回的变量
        if (words.length == 0) {
            return ans;
        }
        //创建第一个HashMap用于存放words中的字符串分布情况
        HashMap<String, Integer> sMap = new HashMap<>();
        for (String item : words) {
            int value = sMap.getOrDefault(item, 0);
            sMap.put(item, value + 1);
        }
        int wLen = words.length;
        int num = words[0].length();
        //开始对s字符串进行循环遍历
        for (int i = 0; i < s.length() - (wLen * num) + 1; i++) {
            //创建新的HashMap用于比较是否找到
            HashMap<String, Integer> wMap = new HashMap<>();
            int temp = 0;  //记录当前寻找的次数
            while (temp < wLen) {
                String cur = s.substring(i + (temp) * num, i + (temp + 1) * num);
                if (sMap.containsKey(cur)) {
                    int c = wMap.getOrDefault(cur, 0);
                    wMap.put(cur, c + 1);
                    if (wMap.get(cur) > sMap.get(cur)) {
                        break;
                    }
                    temp++;
                } else {
                    break;
                }
            }
            if (temp == wLen) {
                ans.add(i);
            }
        }
        return ans;
    }
}
