package my_code_sum_up.code_sort;

import java.util.Arrays;

/**
 * 快速获取随机数组
 *
 * 数组类问题的对数器
 *
 */
public class ArrayTestUntil {
    // Arrays工具类排序方法（一定正确）
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    /**
     * 生成随机数组
     * @param maxSize   生成数组的最大长度（大小随机）
     * @param maxValue  生成数组的最大值
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // 复制一个数组
    public static int[] copyArray(int[] arr){
        if(arr == null) return null;
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // 测试两个数组是否相等
    public static boolean isEquals(int[] arr1, int[] arr2){
        if((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) return false;
        if(arr1 == null && arr2 == null) return true;
        if(arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if(arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    //测试打印数组
    public static void printArray(int[] arr) {
        if(arr == null) return;
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = generateRandomArray(20, 20);
        System.out.println(Arrays.toString(arr));;
    }
}
