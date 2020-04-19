package yc.first;

import java.util.HashMap;

//13. 罗马数字转整数(简单)
public class Test13 {

    //方法一：采用一遍哈希表（算法复杂度较高）
    public int romanToInt(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);
        int len = s.length(), ans = 0, index = 0;
        while (index < len) {
            if (index + 1 < len && map.containsKey(s.substring(index, index + 2))) {
                ans = ans + map.get(s.substring(index, index + 2));
                index += 2;
            } else {
                ans = ans + map.get(s.substring(index, index + 1));
                index += 1;
            }
        }
        return ans;
    }

    //优化方法可以不使用哈希表来存储变量
}
