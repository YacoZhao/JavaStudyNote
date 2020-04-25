package yc.al.zuo.base;

/**
 * 马拉车算法应用实列
 *  * 题目描述：给定一个字符串，向字符串后面添加最短的字符串，使其成为回文字符串
 *  *
 *  * 思路：
 *  * 	1. 按照马拉车算法的思想，一直计算最大右边界的停靠位置
 *  * 	2. 一旦右边界到达了最后一个元素的位置时，停止
 *  * 	3. 向前找到左边界
 *  * 	4. 将左边界左边的元素逆序加到右边界后面即可
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 9:59
 */
public class Class2_Code5_Manacher_ShortestEnd {

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static String shortestEnd(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];
        int index = -1;
        int pR = -1;
        int maxContainsEnd = -1;
        for (int i = 0; i != charArr.length; i++) {
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                index = i;
            }
            if (pR == charArr.length) {
                maxContainsEnd = pArr[i];
                break;
            }
        }
        char[] res = new char[str.length() - maxContainsEnd + 1];
        for (int i = 0; i < res.length; i++) {
            res[res.length - 1 - i] = charArr[i * 2 + 1];
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        String str2 = "abcd123321";
        System.out.println(shortestEnd(str2));
    }
}
