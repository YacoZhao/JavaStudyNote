package yc.al.zuo.base;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 最低的字典序数组
 * @author code_yc
 * @version 1.0
 * @date 2020/4/25 8:33
 */
public class Class7_Code5_LowestLexicography {

    // 创建比较器
    public static class MyComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static String lowestString(String[] strs) {
        if(strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs,new MyComparator());
        String res = "";
        for (String str : strs) {
            res += str;
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
        System.out.println(lowestString(strs1));

        String[] strs2 = { "ba", "b" };
        System.out.println(lowestString(strs2));
    }
}
