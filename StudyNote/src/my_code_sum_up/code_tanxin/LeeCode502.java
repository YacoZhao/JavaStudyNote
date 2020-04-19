package my_code_sum_up.code_tanxin;

import java.util.Comparator;
import java.util.PriorityQueue;

// IPO
public class LeeCode502 {

    /*
    -----------------------------------创建Node辅助贪心算法----------------------------------
    */
    // 创建项目类
    class Node {
        int c;      // 资本
        int p;      // 利润

        public Node(int c, int p) {
            this.c = c;
            this.p = p;
        }
    }

    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        // 首先创建大小堆
        PriorityQueue<Node> cHeap = new PriorityQueue<Node>(new MinCapitalComparator());
        PriorityQueue<Node> pHeap = new PriorityQueue<Node>(new MaxProfitComparator());
        // 将capitalHeap构造成为资本的小顶堆
        for (int i = 0; i < Capital.length; i++) {
            cHeap.add(new Node(Capital[i], Profits[i]));
        }
        int ans = W;
        // 遍历k,进行k次交易
        for (int j = 0; j < k; j++) {
            while (!cHeap.isEmpty() && cHeap.peek().c <= ans) {
                pHeap.add(cHeap.poll());
            }
            if (pHeap.isEmpty()) return ans;
            ans += pHeap.poll().p;
        }
        // 返回利润
        return ans;
    }

    // 小顶堆改造
    public class MinCapitalComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }
    }

    // 大顶堆改造
    public class MaxProfitComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }
    }
}
