package yc.al.zuo.advanced;

import java.util.Arrays;
import java.util.HashMap;

// 6-5  包含正负数的最长累加和为aim的最长子数组
public class Class6_Code5_LongestSubarrayLessSumAwesomeSolution {

    // 方法一： 使用哈希表存储中间变量
    public static int maxLengthAwesome(int[] arr, int k) {
        if (arr == null || arr.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);   // 初始指针赛进去
        int len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                len = Math.max(len, i - map.get(sum - k));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }

    //+++++++++++++++++++测试集++++++++++++++++++++++
    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    // 暴力测试集
    public static int test(int[] arr, int aim) {
        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            int index = i;
            int sum = 0;
            while (index < arr.length) {
                sum += arr[index++];
                if (sum == aim) {
                    len = Math.max(len, index - i);
                }
            }
        }
        return len;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 1000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            int res1 = maxLengthAwesome(arr, k);
            int res2 = test(arr, k);
            if (res1 != res2) {
                System.out.println(Arrays.toString(arr));
                System.out.println(k);
                System.out.println(res1);
                System.out.println(res2);
                System.out.println(false);
            }
        }
    }
}
