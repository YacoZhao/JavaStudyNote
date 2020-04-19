package yc.second;

import java.util.HashMap;

public class Test76 {
    /**
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        // 将t字符串中的字符添加到HashMap中
        HashMap<Character, Integer> mt = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            Character c = t.charAt(i);
            int count = mt.getOrDefault(c, 0);
            mt.put(c, count + 1);
        }
        // 设置左右指针，以及当前已经找到的字符的个数
        int l = 0, r = 0, cur = 0, len = mt.size();
        // 设置存储当前遍历结果的HashMap
        HashMap<Character, Integer> mc = new HashMap<>();
        // 设置一个存储最小长度，左右指针位置的数组
        int[] flag = {-1, 0, 0};
        //开始循环
        while (r < s.length()) {
            char c = s.charAt(r);
            int count = mc.getOrDefault(c, 0);
            mc.put(c, count + 1);
            //如果当前找到了一个t中的元素，那么cur加一，表示已经找到了一个
            if (mt.containsKey(c) && mc.get(c).intValue() == mt.get(c).intValue()) {
                cur++;
            }
            //如果cur已经到了t的长度，则就可以更改标志数组了
            while (l <= r && cur == len) {
                c = s.charAt(l);
                //更新标志数组
                if (flag[0] == -1 || r - l + 1 < flag[0]) {
                    //如果当前长度为初始值-1或者当先长度小于原来的长度，那么更新标志数组
                    flag[0] = r - l + 1;
                    flag[1] = l;
                    flag[2] = r;
                }
                //将当前l位置的元素从mc表中减去
                mc.put(c, mc.get(c) - 1);
                if (mt.containsKey(c) && mc.get(c).intValue() < mt.get(c).intValue()) {
                    cur--;
                }
                l++;
            }
            r++;
        }
        // 循环遍历结束，返回结果
        return flag[0] == -1 ? "" : s.substring(flag[1], flag[2] + 1);
    }
}
