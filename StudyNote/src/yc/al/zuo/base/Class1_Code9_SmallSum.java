package yc.al.zuo.base;

/**
 * 基础班 —————— 小和问题
 *
 * @author code_yc
 * @version 1.0
 * @date 2020/4/23 23:00
 */
public class Class1_Code9_SmallSum {

    // 小和问题
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        return getAns(arr, 0, arr.length - 1);
    }

    //
    private static int getAns(int[] arr, int l, int r) {
        if (l >= r) return 0;
        int mid = (r + l) / 2;
        return getAns(arr, l, mid) + getAns(arr, mid + 1, r) + meger(arr, l, r, mid);
    }

    // 整合数组
    private static int meger(int[] arr, int l, int r, int mid) {
        int p1 = l;
        int p2 = mid + 1;
        int[] help = new int[r - l + 1];
        int i = 0;
        int ans = 0;
        while (p1 <= mid && p2 <= r) {
            ans += arr[p1] < arr[p2] ? (arr[p1] * (r - p2 + 1)) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,4,2,5};
        int sum = smallSum(arr);
        System.out.println(sum);
    }
}
