package yc.al.zuo.base;

/**
 * 基础班——第二课KMP算法 —— 给定两个字符串，查找一个字符串是否包含另一个字符串，如果包含，返回该字符串的起始下标
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 8:42
 */
public class Class2_Code1_KMP {

    public static int getIndexOf(String s, String m) {
        // 边界条件
        if(s == null || m == null || m.length() < 1 ||s.length() < m.length()) return -1;
        // 将两个字符串转化为字符数组
        char[] chs = s.toCharArray();
        char[] chm = m.toCharArray();
        // 计算m字符串的next数组
        int[] next = getNextArr(chm);
        int si = 0;   // chs的指针
        int sm = 0;   // chm的指针
        while(si < chs.length && sm < chm.length){
            if(chs[si] == chm[sm]){
                si++;
                sm++;
            }else if(next[sm] == -1){
                si++;
            }else{
                sm = next[sm];
            }
        }
        return sm == chm.length ? si - sm : -1;
    }

    // 计算next数组
    private static int[] getNextArr(char[] chm){
        if(chm.length == 1) return new int[]{-1};
        int[] next = new int[chm.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int pos = 2;
        while(pos < chm.length){
            if(chm[pos - 1] == chm[cn]){
                next[pos++] = ++cn;
            }else if(cn > 0){
                cn = next[cn];
            }else{
                next[pos++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str = "abcabcababaccc";
        String match = "ababa";
        System.out.println(getIndexOf(str, match));
    }

}
