 package yc.al.zuo.base;

/**
 * 基础班————京东面试题————给定一个字符串str，向字符串尾部添加字符，使得添加结束后的字符串包含两个原始字符串str
 *
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 9:47
 */
public class Class2_Code3_KMP_ShortestHaveTwice {
    public static String answer(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char[] chas = str.toCharArray();
        if (chas.length == 1) {
            return str + str;
        }
        if (chas.length == 2) {
            return chas[0] == chas[1] ? (str + String.valueOf(chas[0])) : (str + str);
        }
        int endNext = endNextLength(chas);
        return str + str.substring(endNext);  // 只要将后面的字符加上就可以了
    }

    public static int endNextLength(char[] chas) {
        int[] next = new int[chas.length + 1];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < next.length) {
            if (chas[pos - 1] == chas[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next[next.length - 1];
    }

    public static void main(String[] args) {
        String test1 = "a";
        System.out.println(answer(test1));

        String test2 = "aa";
        System.out.println(answer(test2));

        String test3 = "ab";
        System.out.println(answer(test3));

        String test4 = "abcdabcd";
        System.out.println(answer(test4));

        String test5 = "abracadabra";
        System.out.println(answer(test5));
    }
}
