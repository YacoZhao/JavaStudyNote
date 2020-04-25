package yc.al.zuo.base;

import java.util.Arrays;

/**
 * BFPRT算法————引出问题：给定一个数组arr和k，返回数组中前k小的数的数组
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 10:02
 */
public class Class2_Code6_BFPRT {


    // O(N*logK)
    // 方法一： 堆排序的思想解决此问题，  时间复杂度：O(N*logK)
    // 基本思想：
    // 		1. 首先创建k长度的数组KHeap，用于最后的返回结果
    //		2. 然后依次将数组arr的前k个元素加入KHeap，加入时采用构造大顶堆的方法，使得初始化之后，KHeap就是一个大顶堆
    //		3. 然后从arr数组的第k+1个元素开始，如果当前元素小雨大顶堆的头节点元素，则将大顶堆KHeap第一个元素改为当前的元素
    //		4. 为了不破坏arr的结构，arr整个过程不动，第3步进行结束之后，直接将arr的指针向后移，此时，再将KHeap重新构造成为大顶堆
    //
    //时间复杂度分析：
    //		将数组改造为大顶堆的过程，其实就是堆排序
    //		O(N*logK)


    /**
     * 问题入口：求数组中前k小个数字组成的数组
     * @param arr 给定的数组
     * @param k   第k小的下标
     * @return
     */
    public static int[] getMinKNumsByHeap(int[] arr, int k) {
        // 如果当前的k越界，直接返回当前数组
        if (k < 1 || k > arr.length) {
            return arr;
        }
        // 创建前k小个数组，用于最后的返回值
        int[] kHeap = new int[k];
        // 将arr的前个数组添入hHeap中
        for (int i = 0; i != k; i++) {
            // 向堆中插入元素，构造出大顶堆
            heapInsert(kHeap, arr[i], i);
        }
        // 从第k个元素开始，此时KHeap元素是大顶堆排好序的
        for (int i = k; i != arr.length; i++) {
            //如果当前的元素小于大顶堆头，则将kHeap头改为当前后面的小元素
            if (arr[i] < kHeap[0]) {
                kHeap[0] = arr[i];
                //重新构造大顶堆
                heapify(kHeap, 0, k);
            }
        }
        // 返回数组
        return kHeap;
    }

    /**
     * 依次向KHeap数组中插入元素，构造出大顶堆
     * @param arr
     * @param value
     * @param index
     */
    public static void heapInsert(int[] arr, int value, int index) {
        arr[index] = value;
        while (index != 0) {
            int parent = (index - 1) / 2;
            if (arr[parent] < arr[index]) {
                swap(arr, parent, index);
                index = parent;
            } else {
                break;
            }
        }
    }

    /**
     * 调整数组，重新构造出大顶堆
     * @param arr
     * @param index
     * @param heapSize
     */
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;
        while (left < heapSize) {
            if (arr[left] > arr[index]) {
                largest = left;
            }
            if (right < heapSize && arr[right] > arr[largest]) {
                largest = right;
            }
            if (largest != index) {
                swap(arr, largest, index);
            } else {
                break;
            }
            index = largest;
            left = index * 2 + 1;
            right = index * 2 + 2;
        }
    }

    /**
     * 解法二： 利用BFPRT算法实现O(N)级别的时间复杂度
     *
     *
     *
     */

    /**
     * 函数入口： 获取前k小的元素数组
     * @param arr
     * @param k
     * @return
     */
    public static int[] getMinKNumsByBFPRT(int[] arr, int k) {
        // 首先同样进行判断，如果K越界，则直接返回数组
        if (k < 1 || k > arr.length) {
            return arr;
        }
        // 获取第k小的元素值
        int minKth = getMinKthByBFPRT(arr, k);
        // 创建可以容纳k这么大的数组
        int[] res = new int[k];
        int index = 0;
        // 将数组元素向res中添
        for (int i = 0; i != arr.length; i++) {
            if (arr[i] < minKth) {
                res[index++] = arr[i];
            }
        }
        // 有可能走到arr[i] 刚好等于minKth的位置，则后面一路相等即可
        for (; index != res.length; index++) {
            res[index] = minKth;
        }
        // 返回结果
        return res;
    }

    /**
     * 获取数组中第k小的元素值（同时将数组元素已经按照中间的值拍好序了）
     * @param arr
     * @param K
     * @return
     */
    public static int getMinKthByBFPRT(int[] arr, int K) {
        // 利用复制好的数组执行
        int[] copyArr = copyArray(arr);
        // 挑选第k小的值
        return select(copyArr, 0, copyArr.length - 1, K - 1);
    }

    /**
     * 复制数组（不破坏原来数组的结构）
     * @param arr
     * @return
     */
    public static int[] copyArray(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i != res.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    /**
     * 从给定数组中挑选出中位数
     * @param arr      指定数组
     * @param begin    起始位置
     * @param end      终止位置
     * @param i        选取第i小的元素
     * @return
     */
    public static int select(int[] arr, int begin, int end, int i) {
        if (begin == end) {
            return arr[begin];
        }
        // 实现了一个递归调用，获取中位数（全局最好的pivot）
        int pivot = medianOfMedians(arr, begin, end);
        // 用这个中位数实现快排
        int[] pivotRange = partition(arr, begin, end, pivot);
        // 如果刚刚好查找的数就在中间位置，直接返回arr[i]
        if (i >= pivotRange[0] && i <= pivotRange[1]) {
            return arr[i];
        } else if (i < pivotRange[0]) {
            // 如果i位置小于less，则向左进行递归调用
            return select(arr, begin, pivotRange[0] - 1, i);
        } else {
            // 如果位置大于more，则向右进行递归调用
            return select(arr, pivotRange[1] + 1, end, i);
        }
    }

    /**
     * 快速获取中位数（全局最优的快排输入值）
     * @param arr
     * @param begin
     * @param end
     * @return
     */
    public static int medianOfMedians(int[] arr, int begin, int end) {
        // 数组总长度
        int num = end - begin + 1;
        // 每五个一组，查看是否有多余的数，有的话则单独成一位
        int offset = num % 5 == 0 ? 0 : 1;
        // 创建存储每五个数据排序后中位数的数组
        int[] mArr = new int[num / 5 + offset];
        // 遍历此数组
        for (int i = 0; i < mArr.length; i++) {
            // 当前mArr来源自原来数组中的起始位置
            int beginI = begin + i * 5;
            // 当前mArr来源自原来数组中的终止位置
            int endI = beginI + 4;
            // 计算出当前i位置5个数排序后的中位数
            mArr[i] = getMedian(arr, beginI, Math.min(end, endI));
        }
        // 在这些中位数的点中，挑选出排好序之后的中位数返回
        return select(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    /**
     * 快排主体： 小于pivotValue放在左边，等于pivotValue放在中间，大于pivotValue放在右边
     * @param arr
     * @param begin
     * @param end
     * @param pivotValue    快排选取的元素
     * @return
     */
    public static int[] partition(int[] arr, int begin, int end, int pivotValue) {
        int small = begin - 1;
        int cur = begin;
        int big = end + 1;
        while (cur != big) {
            if (arr[cur] < pivotValue) {
                swap(arr, ++small, cur++);
            } else if (arr[cur] > pivotValue) {
                swap(arr, cur, --big);
            } else {
                cur++;
            }
        }
        int[] range = new int[2];
        range[0] = small + 1;
        range[1] = big - 1;
        return range;
    }

    /**
     * 获取数组排序后的中位数
     * @param arr
     * @param begin
     * @param end
     * @return
     */
    public static int getMedian(int[] arr, int begin, int end) {
        insertionSort(arr, begin, end);
        int sum = end + begin;
        int mid = (sum / 2) + (sum % 2);
        return arr[mid];
    }

    /**
     * 实现简单的插入排序
     * @param arr
     * @param begin
     * @param end
     */
    public static void insertionSort(int[] arr, int begin, int end) {
        for (int i = begin + 1; i != end + 1; i++) {
            for (int j = i; j != begin; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 交换
     * @param arr
     * @param index1
     * @param index2
     */
    public static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
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
        int[] arr = generateRandomArray(100000,10000);
        // sorted : { 1, 1, 1, 1, 2, 2, 2, 3, 3, 5, 5, 5, 6, 6, 6, 7, 9, 9, 9 }

        long s1 = System.currentTimeMillis();
        int[] nums1 = getMinKNumsByHeap(arr, 2000);
        System.out.println("普通归并排序用时：  " + (System.currentTimeMillis() - s1) + "ms");

        long s2 = System.currentTimeMillis();
        int[] nums2 = getMinKNumsByBFPRT(arr, 2000);
        System.out.println("BFPRT用时：  " + (System.currentTimeMillis() - s2) + "ms");

        System.out.println(isEquals(nums1,nums2));
    }

}
