package yc.no4;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Test295 {

    class MedianFinder {

        class MyMaxComparator implements Comparator<Integer> {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        }

        class MyMinComparator implements Comparator<Integer> {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        }

        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;

        /** initialize your data structure here. */
        public MedianFinder() {
            maxHeap = new PriorityQueue<Integer>(new MyMaxComparator());          // 创建大顶堆
            minHeap = new PriorityQueue<Integer>(new MyMinComparator());          // 创建小顶堆
        }

        public void addNum(int num) {
            // 如果大顶堆为null，直接加入即可，只会进行依次
            if(maxHeap.isEmpty()) {
                maxHeap.add(num);
                return;
            }
            // 如果大顶堆元素>=当前元素，则放在大顶堆下面
            if(maxHeap.peek() >= num){
                maxHeap.add(num);
            }else{
                // 如果小顶堆为null，直接加入
                if(minHeap.isEmpty()){
                    minHeap.add(num);
                    return;
                }else{
                    // 如果小顶堆元素<=当前元素，则放在小顶堆下面
                    if(minHeap.peek() <= num){
                        minHeap.add(num);
                    }else{
                        // 否则，可能介于大顶堆头和小顶堆头之间的元素，优先入大顶堆
                        maxHeap.add(num);
                    }
                }
            }
            // 添加结束之后进行均衡操作
            balanceHeap(maxHeap,minHeap);
        }

        public double findMedian() {
            int maxSize = maxHeap.size();
            int minSize = minHeap.size();
            if(maxSize == 0 && minSize == 0) throw new RuntimeException("queue is empty");
            if(maxSize == 0) return minHeap.peek();
            if(minSize == 0) return maxHeap.peek();
            if(minSize == maxSize) return (minHeap.peek() + maxHeap.peek()) / 2.0;
            return maxSize > minSize ? maxHeap.peek() : minHeap.peek();
        }

        private void balanceHeap(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
            if(maxHeap.size() == minHeap.size() + 2){
                minHeap.add(maxHeap.poll());
            }
            if(minHeap.size() == maxHeap.size() + 2){
                maxHeap.add(minHeap.poll());
            }
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
}
