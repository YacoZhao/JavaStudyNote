package yc.al.zuo.base;

import java.util.HashSet;

/**
 * 打印一个字符串的全部子排列
 * @author code_yc
 * @version 1.0
 * @date 2020/4/25 8:40
 */
public class Class8_Code3_Print_All_Permutations {

    /**
     * 主函数入口
     * @param str
     */
    public static void printAllPermutations1(String str) {
        process(str.toCharArray(),0);
    }

    public static void printAllPermutations2(String str) {
        process2(str.toCharArray(),0);
    }

    // 递归体(有一个问题：如果碰到了重复的字符，则递归会打印重复)
    public static void process(char[] chars, int i) {
        if(i == chars.length) {
            System.out.println(String.valueOf(chars));
            return;
        }
        for (int j = i; j < chars.length; j++) {
            swap(chars,i,j);
            //交换完了之后打印
            process(chars,i+1);
            //交换完了之后再变回来
            swap(chars,i,j);
        }
    }

    // 改进（使用一个hashSet来剔除重复的值）
    public static void process2(char[] chars, int i) {
        if(i == chars.length) {
            System.out.println(String.valueOf(chars));
            return;
        }
        HashSet<Character> set = new HashSet<Character>();
        for (int j = i; j < chars.length; j++) {
            if(!set.contains(chars[j])){
                set.add(chars[j]);   //如果出现了重复的字符，就不会再打印了
                swap(chars,i,j);
                //交换完了之后打印
                process(chars,i+1);
                //swap(chars,i,j);
            }
        }
    }

    // 交换两个元素在数组中的位置
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        String test1 = "abc";
        printAllPermutations1(test1);
        System.out.println("======");
        printAllPermutations2(test1);
        System.out.println("======");

        String test2 = "acc";
        printAllPermutations1(test2);
        System.out.println("======");
        printAllPermutations2(test2);
        System.out.println("======");
    }
}
