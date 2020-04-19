package yc.al.zuo.advanced;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

// 8-1  两个有序数组间相加和的TOP K问题
public class Class8_Code1_TopKSumCrossTwoArrays {

    // 创建大顶堆的节点类，三个参数关系为arr1[c1] + arr2[c2] = value
    static class HeapNode{
        int c1;
        int c2;
        int value;

        public HeapNode(int c1, int c2, int value) {
            this.c1 = c1;
            this.c2 = c2;
            this.value = value;
        }
    }

    /**
     * 主函数入口
     * @param arr1
     * @param arr2
     * @param k
     * @return
     */
    public static int[] topKSum(int[] arr1, int[] arr2, int k){
        // 边界条件判断
        if(arr1 == null || arr2 == null || k < 1) return null;
        // 保证K在有效范围之间
        k = Math.min(k, arr1.length * arr2.length);
        // 创建一个大顶堆，将两个数组的最后一个元素相加，加入大根堆
        HeapNode[] heap = new HeapNode[k + 1];
        int h1 = arr1.length - 1;
        int h2 = arr2.length - 1;
        int size = 0;
        int c1 = -1;
        int c2 = -1;
        HashSet<String> set = new HashSet<>();
        heapInsert(heap, size++, h1, h2, arr1[h1] + arr2[h2]);
        // 创建结构数组int[] res, 循环遍历向数组中填入结果
        int[] res = new int[k];
        int index = 0;
        while(index < k){
            // 首先从大顶堆中将元素弹出,加入res中
            HeapNode top = popNode(heap,size--);
            res[index++] = top.value;
            // 获取弹出节点的属性
            c1 = top.c1;
            c2 = top.c2;
            if(c1 > 0 && !set.contains((c1 - 1) + "-" + c2)){
                heapInsert(heap,size++,c1-1,c2,arr1[c1 - 1]+ arr2[c2]);
                set.add((c1 - 1) + "-" + c2);
            }
            if(c2 > 0 && !set.contains(c1 + "-" + (c2 - 1))){
                heapInsert(heap,size++,c1,c2-1,arr1[c1]+ arr2[c2-1]);
                set.add(c1 + "-" + (c2 - 1));
            }
        }
        return res;
    }

    // 弹出当钱堆顶的元素，并重新调整为大顶堆
    private static HeapNode popNode(HeapNode[] heap, int index) {
        HeapNode top = heap[0];
        swap(heap,0,index - 1);
        heap[index - 1] = null;
        heapify(heap,0,index - 1);
        return top;
    }

    // 继续调正为大顶堆
    private static void heapify(HeapNode[] heap,int index, int heapSize) {
        int left = (2 * index) + 1;
        int right = (2 * index) + 2;
        int largest = index;
        while(left < heapSize){
            if(heap[left].value > heap[index].value){
                largest = left;
            }
            if(right < heapSize && heap[right].value > heap[largest].value){
                largest = right;
            }
            if(largest != index){
                swap(heap,index,largest);
            }else{
                break;
            }
            index = largest;
            left = (2 * index) + 1;
            right = (2 * index) + 2;
        }
    }

    // 向大顶堆heap中插入元素
    private static void heapInsert(HeapNode[] heap, int index, int h1, int h2, int value) {
        heap[index] = new HeapNode(h1, h2, value);
        int parent = (index - 1) / 2;
        while(index != 0){
            if(heap[index].value > heap[parent].value){
                swap(heap,index,parent);
                index = parent;
                parent = (index - 1) / 2;
            }else {
                break;
            }
        }
    }

    // 交换两个节点的位置
    private static void swap(HeapNode[] heap, int x, int y) {
        HeapNode temp = heap[x];
        heap[x] = heap[y];
        heap[y] = temp;
    }

    //+++++++++++++++++++++++++++++++++++创建测试函数++++++++++++++++++++++++++++++++++++++++
    //-----------------------------------创建测试函数----------------------------------------
    //+++++++++++++++++++++++++++++++++++创建测试函数++++++++++++++++++++++++++++++++++++++++

    // 测试函数，纯暴力
    public static int[] topKSumTest(int[] arr1, int[] arr2, int topK) {
        int[] all = new int[arr1.length * arr2.length];
        int index = 0;
        for (int i = 0; i != arr1.length; i++) {
            for (int j = 0; j != arr2.length; j++) {
                all[index++] = arr1[i] + arr2[j];
            }
        }
        Arrays.sort(all);
        int[] res = new int[Math.min(topK, all.length)];
        index = all.length - 1;
        for (int i = 0; i != res.length; i++) {
            res[i] = all[index--];
        }
        return res;
    }

    // 生成随机数组
    public static int[] generateRandomSortArray(int len) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * 50000) + 1;
        }
        Arrays.sort(res);
        return res;
    }

    // 打印数组
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 判断两个数组是否相等
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i != arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int a1Len = 5000;
        int a2Len = 4000;
        int k = 2000;
        int[] arr1 = generateRandomSortArray(a1Len);
        int[] arr2 = generateRandomSortArray(a2Len);
        long start = System.currentTimeMillis();
        int[] res = topKSum(arr1, arr2, k);
        long end = System.currentTimeMillis();
        System.out.println(end - start + " ms");

        start = System.currentTimeMillis();
        int[] absolutelyRight = topKSumTest(arr1, arr2, k);
        end = System.currentTimeMillis();
        System.out.println(end - start + " ms");

        System.out.println(isEqual(res, absolutelyRight));
    }
}
