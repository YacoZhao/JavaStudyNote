package my_code_sum_up.code_sort;

/**
 * 左神基础课——小和问题（归并排序的实际应用）
 *
 * 题目描述：
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和。
 * 例子：
 * [1,3,4,2,5]
 * 1左边比1小的数，没有；
 * 3左边比3小的数，1；
 * 4左边比4小的数，1、3；
 * 2左边比2小的数，1；
 * 5左边比5小的数，1、3、4、2；
 * 所以小和为1+1+3+1+1+3+4+2=16
 *
 *
 * 时间复杂度分析（T(N) = 2 * T(N/2) + O(N)） —————— 由master公式得出时间复杂度为 N*logN
 */
public class Zuo_SmallSum {

    // 分治归并的思想求解
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        return mergeSum(arr, 0, arr.length - 1);
    }

    private static int mergeSum(int[] arr, int l, int r) {
        if (l == r) return 0;
        int mid = l + ((r - l) >> 1);
        return mergeSum(arr, l, mid) + mergeSum(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    // 归并的过程
    private static int merge(int[] arr, int l, int mid, int r) {
        int helper[] = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        int ans = 0;
        while (p1 <= mid && p2 <= r) {
            ans += arr[p1] < arr[p2] ? (arr[p1] * (r - p2 + 1)) : 0;
            helper[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            helper[i++] = arr[p1++];
        }
        while (p2 <= r) {
            helper[i++] = arr[p2++];
        }
        for (int j = 0; j < helper.length; j++) {
            arr[l + j] = helper[j];
        }
        return ans;
    }
}
