package yc.al.zuo.base;

/**
 * 给你一个数组arr，和一个整数aim。如果可以任意选择arr中的数字，能不能累加得到aim，返回true或者false
 * @author code_yc
 * @version 1.0
 * @date 2020/4/25 8:48
 */
public class Class8_Code6_Money_Problem {

    // 解法一：暴力递归

    /**
     * 主要思想：
     *  每经过数组中的一个数，可选要当前数组，或者不要当前数，但sum == aim时结束递归，或者数组遍历完时结束递归
     * @param arr
     * @param aim
     * @return
     */
    public static boolean money1(int[] arr, int aim) {
        return process1(arr, 0, 0, aim);
    }

    private static boolean process1(int[] arr, int i, int sum, int aim) {
        if (sum == aim) {
            return true;
        }
        if (sum > aim) {
            return false;
        }
        if (i == arr.length) {
            return false;
        }
        return process1(arr, i + 1, sum, aim) || process1(arr, i + 1, sum + arr[i], aim);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 4, 8 };
        int aim = 12;
        System.out.println(money1(arr, aim));
        //System.out.println(money2(arr, aim));
    }
}
