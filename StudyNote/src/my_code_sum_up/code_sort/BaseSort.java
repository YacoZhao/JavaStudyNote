package my_code_sum_up.code_sort;

import java.util.Arrays;

/**
 * 各种基本的排序方法汇总
 *
 * 冒泡
 * 插入
 * 选择
 * 归并
 * 快排
 * 堆排
 * 桶排
 *
 */
public class BaseSort {

    // 交换器
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //冒泡
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    //插入排序（官方排序器中普通数据规模小于60时使用，常数级时间复杂度低）
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    //选择排序
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        for (int i = 0; i < arr.length - 1; i++) {
            int min_index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min_index]) {
                    min_index = j;
                }
            }
            swap(arr, i, min_index);
        }
    }

    //快排（不稳定的排序，一般对于大规模的基本的数据类型使用快排）
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int l, int r) {
        if (l < r) {
            //随机选择数组中的一个数
            int pivot = arr[l + (int) ((r - l + 1) * Math.random())];
            int[] temp = sortBody(arr, l, r, pivot);
            process(arr, l, temp[0]);
            process(arr, temp[1], r);
        }
    }

    private static int[] sortBody(int[] arr, int l, int r, int pivot) {
        int less = l - 1;
        int more = r + 1;
        while (l < more) {
            if (arr[l] < pivot) {
                swap(arr, ++less, l++);
            } else if (arr[l] > pivot) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        return new int[]{less, more};
    }

    // 归并排序（稳定的排序，一般对于抽象结构的大规模数据排序时，使用归并排序）
    public static void mergeSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        binarySort(arr,0,arr.length - 1);
    }

    // 分治
    private static void binarySort(int[] arr, int l, int r) {
        if(l < r){
            int mid = l + ((r - l) >> 1);
            binarySort(arr,l,mid);
            binarySort(arr,mid + 1,r);
            merge(arr,l,r,mid);
        }
    }

    //合并
    private static void merge(int[] arr, int l, int r, int mid) {
        int p1 = l;
        int p2 = mid + 1;
        int[] help = new int[r - l + 1];
        int index = 0;
        while(p1 <= mid && p2 <= r){
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1 <= mid){
            help[index++] = arr[p1++];
        }
        while(p2 <= r){
            help[index++] = arr[p2++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    // 堆排
    public static void heapSort(int[] arr){
        if (arr == null || arr.length < 2) return;
        //遍历arr，创建大顶堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr,i);
        }
        int size = arr.length;
        swap(arr,0,--size);
        for (int i = 0; i < arr.length - 1; i++) {
            heapFind(arr,--size);
            swap(arr,0,size);
        }
    }

    private static void heapFind(int[] arr, int size) {
        //调整数组的前面的元素为大顶堆
        int index = 0;
        int left = 2 * index + 1;
        while(left <= size){
            int largest = left + 1 <= size && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if(largest == index){
                break;
            }
            swap(arr,index,largest);
            index = largest;
            left = 2 * index + 1;
        }
    }

    private static void heapInsert(int[] arr , int i){
        while(arr[i] > arr[(i - 1) / 2]){
            swap(arr,i,(i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    //测试主方法
    public static void main(String[] args) {
        int testTime = 500000;  //测试次数
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = ArrayTestUntil.generateRandomArray(maxSize, maxValue);  //生成随机数组
            int[] arr1 = ArrayTestUntil.copyArray(arr);   //复制数组
            heapSort(arr);
            ArrayTestUntil.comparator(arr1);
            if (!ArrayTestUntil.isEquals(arr, arr1)) {
                System.out.println("手撸：" + Arrays.toString(arr));
                System.out.println("标准：" + Arrays.toString(arr1));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking Fucked");
    }
}
