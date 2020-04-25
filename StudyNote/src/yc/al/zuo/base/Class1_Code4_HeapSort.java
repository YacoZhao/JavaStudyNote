package yc.al.zuo.base;

import java.util.Arrays;

/**
 * 基础班——————实现堆排序
 *
 * @author code_yc
 * @version 1.0
 * @date 2020/4/23 9:52
 */
public class Class1_Code4_HeapSort {


    // 堆排
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        // 遍历数组，将arr构造成为大顶堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        // 调整更新数组的元素
        int size = arr.length;
        swap(arr,0,--size);
        // 只要数组还有元素，就一致更新
        while(size > 0){
            heapFind(arr,size);
            swap(arr,0,--size);
        }
    }

    // 调整更新数组元素
    private static void heapFind(int[] arr, int size) {
        int left  = 1;
        int index = 0;
        int largest = 0;
        while(left < size){
            largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if(largest == index) break;
            swap(arr,index,largest);
            index = largest;
            left = (2 * index) + 1;
        }
    }

    // 插入元素
    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 交换元素
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ++++++++++++++++++++++++++++++++++++++++
    //                对数器
    // ++++++++++++++++++++++++++++++++++++++++
    // 官方排序方法
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // 获取随机测试数组
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

    //测试主方法
    public static void main(String[] args) {
        int testTime = 500000;  //测试次数
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize,maxValue);  //生成随机数组
            int[] arr1 = copyArray(arr);   //复制数组
            heapSort(arr);
            comparator(arr1);
            if(!isEquals(arr,arr1)){
                System.out.println("手撸：" + arr);
                System.out.println("标准：" + arr1);
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking Fucked");
    }
}
