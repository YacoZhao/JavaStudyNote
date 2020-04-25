package yc.al.zuo.base;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * IPO问题——————项目启动利益
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 19:18
 */
public class Class7_Code3_IPO {

    /**
     * 主函数入口：
     * @param k  最多可以作的项目数
     * @param W  初始资金
     * @param Profits   每个项目的利润数组
     * @param Capital   每个项目的启动资金
     * @return
     */
    public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        Node[] nodes = new Node[Profits.length];
        for (int i = 0; i < Profits.length; i++) {
            nodes[i] = new Node(Capital[i],Profits[i]);
        }
        // 构建按照容量形成的小顶堆
        PriorityQueue<Node> CapitalQueue = new PriorityQueue<>(new MinCapitalComparator());
        PriorityQueue<Node> ProfitsQueue = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < nodes.length; i++) {
            CapitalQueue.add(nodes[i]);
        }
        for (int i = 0; i < k; i++) {
            while(!CapitalQueue.isEmpty() && CapitalQueue.peek().capital <= W) {
                ProfitsQueue.add(CapitalQueue.poll());
            }
            if(ProfitsQueue.isEmpty()) {
                return W;
            }
            W += ProfitsQueue.poll().profit;     // 一个项目假设只做一次，W加上此项目的利润
        }
        return W;
    }

    // 创建项目信息的类
    public static class Node{
        int capital;
        int profit;

        public Node(int capital, int profit) {
            this.capital = capital;
            this.profit = profit;
        }
    }

    // 小顶堆改造
    public static class MinCapitalComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.capital - o2.capital;
        }
    }

    // 大顶堆改造
    public static class MaxProfitComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.profit - o1.profit;
        }
    }

}
