package yc.al.zuo.base;

import java.util.HashSet;

/**
 * 全排列问题
 * @author code_yc
 * @version 1.0
 * @date 2020/4/25 8:52
 */
public class Class8_Code8_Print_All_Permutations {

    //字符串的全排列问题
    public static void printAllPermutations1 (String str) {
        process1(str.toCharArray(),0);
    }

    //字符串的全排列问题
    public static void printAllPermutations2 (String str) {
        HashSet<String> set = new HashSet<>();
        process2(str.toCharArray(),0,set);
    }

    private static void process2(char[] toCharArray, int i, HashSet<String> set) {
        if( i == toCharArray.length) {
            String res = String.valueOf(toCharArray);
            if(!set.contains(res)){
                System.out.println(res);
                set.add(res);
                return;
            }
            return;
        }
        for (int j = i; j < toCharArray.length; j++) {
            swap(toCharArray,i,j);
            process2(toCharArray,i+1,set);
            swap(toCharArray,i,j);
        }
    }

    private static void process1(char[] toCharArray, int i) {
        if( i == toCharArray.length) {
            System.out.println(String.valueOf(toCharArray));
            return;
        }
        for (int j = i; j < toCharArray.length; j++) {
            swap(toCharArray,i,j);
            process1(toCharArray,i+1);
            swap(toCharArray,i,j);
        }
    }

    // 交换两个元素的位置
    private static void swap(char[] toCharArray, int i, int j) {
        char temp = toCharArray[i];
        toCharArray[i] = toCharArray[j];
        toCharArray[j] = temp;
    }

    // 主函数：测试函数
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
