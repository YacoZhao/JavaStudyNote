package yc.al.zuo.advanced;

import javax.swing.text.MaskFormatter;
import java.util.HashMap;

// 6-4 最长累加和为aim的子数组
public class Class6_Code4_LongestSumSubArrayLengthInPositiveArray {

    // 双指针算法求解————分三种情况，超出k的情况，小于k的情况，大于k的情况
    public static int getMaxLength(int[] arr, int k) {
        if(arr == null || k < 0 || arr.length == 0) return 0;
        int len = 0;
        int L = 0;
        int R = 0;
        int sum = arr[0];
        while(R < arr.length){
            if(sum == k){
                len = Math.max(len,R - L + 1);
                sum -= arr[L++];
            }else if (sum < k){
                R++;
                if(R == arr.length){
                    break;
                }
                sum += arr[R];
            }else{
                sum -= arr[L++];
            }
        }
        return len;
    }

    //++++++++++自己写的方法(使用map存储中间值)++++++++++++
    public static int getMaxLen(int[] arr, int aim){
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        int sum = 0;
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if(map.containsKey(sum - aim)){
                maxLen = Math.max(maxLen,i - map.get(sum - aim));
            }
            if(!map.containsKey(sum)){
                map.put(sum,i);
            }
        }
        return maxLen;
    }


    public static int[] generatePositiveArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 10) + 1;
        }
        return result;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 20;
        int k = 15;
        int[] arr = generatePositiveArray(len);
        printArray(arr);
        System.out.println(getMaxLength(arr, k));
        System.out.println(getMaxLen(arr, k));
    }
}
