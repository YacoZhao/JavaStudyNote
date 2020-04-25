package yc.al.zuo.advanced;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 进阶版第二课————找到被指的新类型字符
 *
 * @author code_yc
 * @version 1.0
 * @date 2020/4/21 16:28
 */
public class Class2_Code1_FindNewTypeChar {

    /**
     * 给定字符串s,和位置k,返回k位置所指的新类型字符
     *
     * @param s
     * @param k
     * @return
     */
    public static String pointNewchar(String s, int k) {
        if (s == null || s.equals("") || k < 0 || k >= s.length()) {
            return null;
        }
        char[] chars = s.toCharArray();
        int num = 0;
        // 检查K位置之前有多少个大写字母
        for (int i = k - 1; i >= 0; i--) {
            if (!isUpper(chars, i)) {
                break;
            }
            num++;
        }
        // 如果大写字母有奇数个，直接返回前一个字符到此处的位置
        if ((num & 1) == 1) {
            return s.substring(k - 1, k + 1);
        }
        // 否则，如果当前位置是大写字母
        if (isUpper(chars, k)) {
            return s.substring(k, k + 2);
        }
        return String.valueOf(chars[k]);
    }

    // 编写一个函数,判断当前位置元素是否是大写
    private static boolean isUpper(char[] chars, int i) {
        return !(chars[i] < 'A' || chars[i] > 'Z');
    }

    // 测试函数
    public static void main(String[] args) {
        String str = "aaABCDEcBCg";
        for (int i = 0; i < str.length(); i++) {
            System.out.println(pointNewchar(str,i));
        }

        AtomicInteger i = new AtomicInteger();
        i.incrementAndGet();
    }
}
