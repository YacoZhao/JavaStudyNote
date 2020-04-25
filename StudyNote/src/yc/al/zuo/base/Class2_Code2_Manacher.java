package yc.al.zuo.base;

/**
 * 马拉车算法 ———————— 计算一个字符串中最长回文子串
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 9:23
 */
public class Class2_Code2_Manacher {

    // 求一个字符串的最长回文子串的长度
    public static int maxLcpsLength(String str) {
        if(str == null || str.length() == 0) return 0;
        // 首先将字符串str转化为带虚轴的回文字符数组
        char[] chs = getManacherString(str);
        // 进行Manacher操作
        int[] pArr = new int[chs.length];    // 存储回文半径
        int pR = -1;                         // 当前回文字符串的最大右边界
        int center = -1;                     // 最大回文右边界对应的回文中心
        int max = Integer.MIN_VALUE;         // 记录当前最长回文字符串的长度
        // 遍历chs，更新
        for (int i = 0; i < chs.length; i++) {
            pArr[i] = pR > i ? Math.max(pR - i, pArr[(2 * center) - i]) : 1;
            // 中心扩展
            while(i + pArr[i] < chs.length && i - pArr[i] >= 0){
                if(chs[i + pArr[i]] == chs[i - pArr[i]]){
                    pArr[i]++;
                }else{
                    break;
                }
            }
            // 如果最大回文右边界改变了，
            if(i + pArr[i] > pR){
                pR = i + pArr[i];
                center = i;
            }
            // 更新max
            max = Math.max(max,pArr[i]);
        }
        return max - 1;
    }

    // 获取当前字符串的改造数组
    private static char[] getManacherString(String str) {
        char[] res = new char[str.length() * 2 + 1];
        for (int i = 0; i < str.length(); i++) {
            res[2 * i] = '#';
            res[(2 * i) + 1] = str.charAt(i);
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "abc1234321ab";
        System.out.println(maxLcpsLength(str1));
    }

}
