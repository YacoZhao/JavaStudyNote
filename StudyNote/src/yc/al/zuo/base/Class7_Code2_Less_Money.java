package yc.al.zuo.base;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 贪心金砖问题——————优先级队列
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 19:17
 */
public class Class7_Code2_Less_Money {

    /**
     * 主函数入口
     * @param arr
     * @return
     */
    public static int lessMoney(int[] arr) {
        PriorityQueue<Integer> PQ = new PriorityQueue<Integer>();       //创建一个优先级队列（默认情况下按照升序进行排列）
        for (int i = 0; i < arr.length; i++) {
            PQ.add(arr[i]);
        }
        int res = 0;
        int sum = 0;
        while(PQ.size() > 1) {
            sum = PQ.poll() + PQ.poll();
            res += sum;
            PQ.add(sum);
        }
        return res;
    }


    public static void main(String[] args) {
        // solution
        int[] arr = {9, 7, 8, 6};
        System.out.println(lessMoney(arr));

        int[] arrForHeap = { 3, 5, 2, 7, 0, 1, 6, 4 };

        // min heap
        PriorityQueue<Integer> minQ1 = new PriorityQueue<>();
        for (int i = 0; i < arrForHeap.length; i++) {
            minQ1.add(arrForHeap[i]);
        }
        while (!minQ1.isEmpty()) {
            System.out.print(minQ1.poll() + " ");
        }
        System.out.println();

        // min heap use Comparator
        PriorityQueue<Integer> minQ2 = new PriorityQueue<>(new MinheapComparator());
        for (int i = 0; i < arrForHeap.length; i++) {
            minQ2.add(arrForHeap[i]);
        }
        while (!minQ2.isEmpty()) {
            System.out.print(minQ2.poll() + " ");
        }
        System.out.println();

        // max heap use Comparator
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(new MaxheapComparator());
        for (int i = 0; i < arrForHeap.length; i++) {
            maxQ.add(arrForHeap[i]);
        }
        while (!maxQ.isEmpty()) {
            System.out.print(maxQ.poll() + " ");
        }
    }

    // 小顶堆比较器
    public static class MinheapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2; // < 0  o1 < o2  负数
        }

    }

    // 大顶堆比较器
    public static class MaxheapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1; // <   o2 < o1
        }

    }

}
