package yc.al.zuo.base;

import java.util.Arrays;

/**
 * 基于桶排序求最大间隔问题
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 7:59
 */
public class Class1_Code10_MaxGap {

    public static int maxGap(int[] arr){
        if(arr == null || arr.length < 2) return 0;
        // 首先遍历数组求出最大值和最小值
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min,arr[i]);
            max = Math.max(max,arr[i]);
        }
        int len = arr.length;
        boolean[] hasNum = new boolean[len + 1];   // 判断当前位置是否有元素
        int[] mins = new int[len + 1];
        int[] maxs = new int[len + 1];
        for (int i = 0; i < arr.length; i++) {
            int bid = getNumIndex(arr[i],min,max,len);
            mins[bid] = hasNum[bid] ? Math.min(mins[bid],arr[i]) : arr[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid],arr[i]) : arr[i];
            hasNum[bid] = true;
        }
        // 获取计算结果
        int res = 0;
        int lastMax = maxs[0];
        int i = 1;
        for (; i <= len; i++) {
            if(hasNum[i]){
                res = Math.max(res,mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    // 获取元素的下标
    private static int getNumIndex(int value, int min, int max, int len){
        return (value - min) * len / (max - min);
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                         对数器
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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

    // 对数器
    public static int test(int[] arr){
        if(arr == null || arr.length < 2) return 0;
        // 首先将数组排序
        Arrays.sort(arr);
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            res = Math.max(res,arr[i] - arr[i - 1]);
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
            System.out.println(gap == test(arr1));
        }
    }
}
