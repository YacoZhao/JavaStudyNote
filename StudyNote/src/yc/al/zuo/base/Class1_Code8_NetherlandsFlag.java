package yc.al.zuo.base;

import java.util.Arrays;

/**
 * 基础班 —————— 荷兰国旗问题
 * @author code_yc
 * @version 1.0
 * @date 2020/4/23 22:49
 */
public class Class1_Code8_NetherlandsFlag {

    // 给定一个数组和一个键值，小于放在左边，等于在左边，大于在右边
    public static void netherlandsFlags(int[] arr, int num) {
        if(arr == null || arr .length < 2) return;
        int less = -1;
        int more = arr.length;
        int cur = 0;
        while(cur < more){
            if(arr[cur] < num){
                swap(arr,cur++,++less);
            }else if(arr[cur] > num){
                swap(arr,cur,--more);
            }else{
                cur++;
            }
        }
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1,5,7,8,6,5,4,3,2,1,8,9,5,4,12,5};
        netherlandsFlags(arr,5 );
        System.out.println(Arrays.toString(arr));
    }
}
