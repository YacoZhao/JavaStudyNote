package my_code_sum_up.code_sort;

import java.util.Arrays;

/**
 * 左神基础课——荷兰国旗问题
 *
 *         快排的基本思想
 */
public class Zuo_NetherlandsFlag {
    public static void netherlandsFlags(int[] arr, int num) {
        if (arr == null || arr.length < 2) return;
        int less = -1;
        int more = arr.length;
        int cur = 0;
        while (cur < more) {
            if (arr[cur] < num) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > num) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
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
