package my_code_sum_up.code_sort;

import java.util.Arrays;

/**
 * 左神基础课————最大间距问题
 *
 * 给定一个数组，求如果排序之后，相邻两数的最大差值，要求时间复杂度O(N)，且要求不能用非基于比较的排序。
 */
public class Zuo_MaxGap {

    public static int maxGap(int[] arr) {
        if(arr == null || arr.length < 2) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            min = Math.min(min,arr[i]);
            max = Math.max(max,arr[i]);
        }
        if(min == max) return 0;
        boolean[] hasNum = new boolean[len + 1]; //用于检测数组是否已经存在以元素
        int[] maxs = new int[len + 1];  //用于存放每个桶中最大的数
        int[] mins = new int[len + 1];  //用于存放每个桶中最小的数
        int bid = 0; //计算桶数
        for (int i = 0; i < len; i++) {
            bid = getBucket(arr[i],len,min,max);
            mins[bid] = hasNum[bid] ? Math.min(mins[bid],arr[i]) : arr[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid],arr[i]) : arr[i];
            hasNum[bid] = true;
        }
        int res = 0; //计算结果
        int lastMax = maxs[0];  //第一个桶中肯定有元素
        int i = 1;
        for (; i < len + 1; i++) {
            if(hasNum[i]) {  // 如果桶不为空格
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    private static int getBucket(long num, long len, long min, long max) {
        return (int)((num - min) * len / (max - min));
    }

    // for test
    public static int comparator(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int gap = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            gap = Math.max(nums[i] - nums[i - 1], gap);
        }
        return gap;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static void main(String[] args) {
        int testTime = 50;
        int maxSize = 100;
        int maxValue = 100;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int gap = maxGap(arr1);
            System.out.println("--------" + gap + "--------");
        }
    }
}
