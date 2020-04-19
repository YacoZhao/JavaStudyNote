package yc.al.zuo.advanced;

import java.util.HashMap;

// 8-5 找到字符串的最长无重复字符子串
public class Class8_Code5_LongestNoRepeatSubstring {

    // 方法一： 自己撸————使用map存储索引地址
    public static int getMaxLenStr(String s){
        if(s == null) return 0;
        if(s.length() == 1) return 1;
        HashMap<Character,Integer> map = new HashMap<>();
        int res = 0;
        char[] chars = s.toCharArray();
        int pre = -1;
        for (int i = 0; i < chars.length; i++) {
            char cur = chars[i];
            pre = Math.max(map.getOrDefault(cur, -1),pre);
            map.put(cur,i);
            res = Math.max(res,i-pre);
        }
        return res;
    }

    // 方法二： 左神提供————使用arr数组记录下标
    public static int maxUnique(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chas = str.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int len = 0;
        int pre = -1;
        int cur = 0;
        for (int i = 0; i != chas.length; i++) {
            pre = Math.max(pre, map[chas[i]]);
            cur = i - pre;
            len = Math.max(len, cur);
            map[chas[i]] = i;
        }
        return len;
    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //---------------------------测试集---------------------------
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    // 生成随机字符串
    public static String getRandomString(int len) {
        char[] str = new char[len];
        int base = 'a';
        int range = 'z' - 'a' + 1;
        for (int i = 0; i != len; i++) {
            str[i] = (char) ((int) (Math.random() * range) + base);
        }
        return String.valueOf(str);
    }

    // 完全正确的方法
    public static String maxUniqueString(String str) {
        if (str == null || str.equals("")) {
            return str;
        }
        char[] chas = str.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int len = -1;
        int pre = -1;
        int cur = 0;
        int end = -1;
        for (int i = 0; i != chas.length; i++) {
            pre = Math.max(pre, map[chas[i]]);
            cur = i - pre;
            if (cur > len) {
                len = cur;
                end = i;
            }
            map[chas[i]] = i;
        }
        return str.substring(end - len + 1, end + 1);
    }

    public static void main(String[] args) {
        String str = getRandomString(20);
        System.out.println(str);
        System.out.println(getMaxLenStr(str) == maxUnique(str));
        System.out.println(maxUniqueString(str));
    }
}
