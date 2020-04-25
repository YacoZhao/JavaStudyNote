package yc.al.zuo.base;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 创建一个可以随时获取中位数的流 ———————— 优先级队列
 * @author code_yc
 * @version 1.0
 * @date 2020/4/25 8:29
 */
public class Class7_Code4_MadianQuick {

    /**
     * 可以快速获取中位数的数据流
     *
     * 功能：
     * 可以添加元素
     * 可以快速获取中位数
     */
    public static class MedianQuickHolder{
        private PriorityQueue<Integer> maxHead = new PriorityQueue<Integer>(new MaxHeadComparator());
        private PriorityQueue<Integer> minHead = new PriorityQueue<Integer>(new MinHeadComparator());

        // 添加元素的方法
        public void add(Integer num) {
            if(maxHead.isEmpty()) {
                maxHead.add(num);
                return;      // 为空的话可以直接返回01
            }
            if(maxHead.peek() >= num) {
                maxHead.add(num);
            }else{
                if(minHead.isEmpty()){
                    minHead.add(num);
                    return;  // 为空的话可以直接返回
                }
                if(minHead.peek() <= num){
                    minHead.add(num);
                }else{
                    maxHead.add(num);
                }
            }
            modifyTwoHeapSize();
        }

        // 调整两个堆的大小，使得间距小于2
        private void modifyTwoHeapSize() {
            if(minHead.size() == maxHead.size() + 2) {
                maxHead.add(minHead.poll());
            }
            if(maxHead.size() == minHead.size() + 2) {
                minHead.add(maxHead.poll());
            }
        }

        // 快速获取中位数的方法
        public Integer getMedianNum(){
            int maxHeadSize = maxHead.size();
            int minHeadSize = minHead.size();
            if(minHeadSize + maxHeadSize == 0) return null;
            Integer maxHeadValue = maxHead.peek();
            Integer minHeadValue = minHead.peek();
            if((maxHeadSize + minHeadSize) % 2 == 0) {
                return (maxHeadValue + minHeadValue) / 2;
            }
            return maxHeadSize > minHeadSize ? maxHeadValue : minHeadValue;
        }
    }

    // 创建生成大顶堆的比较器
    static class MaxHeadComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    // 创建生成小顶堆的比较器
    static class MinHeadComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    // for test
    private static int[] getRandomArray(int maxLen, int maxValue) {
        int[] res = new int[(int) (Math.random() * maxLen) + 1];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue);
        }
        return res;
    }

    // for test, this method is ineffective but absolutely right
    private static int getMedianOfArray(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(newArr);
        int mid = (newArr.length - 1) / 2;
        if ((newArr.length & 1) == 0) {
            return (newArr[mid] + newArr[mid + 1]) / 2;
        } else {
            return newArr[mid];
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        boolean err = false;
        int testTimes = 200000;
        for (int i = 0; i != testTimes; i++) {
            int len = 30;
            int maxValue = 1000;
            int[] arr = getRandomArray(len, maxValue);
            MedianQuickHolder medianHold = new MedianQuickHolder();
            for (int j = 0; j != arr.length; j++) {
                medianHold.add(arr[j]);
            }
            if (medianHold.getMedianNum() != getMedianOfArray(arr)) {
                err = true;
                printArray(arr);
                break;
            }
        }
        System.out.println(err ? "Oops..what a fuck!" : "today is a beautiful day^_^");

    }
}
