package my_code_sum_up.code_tanxin;

import yc.no4.Test218;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LeeCode1353 {

    public int maxEvents(int[][] events) {
        // 首先将所有会议按照会议开始时间进行排序
        Arrays.sort(events, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });
        // 创建优先级队列
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int cur = 0;     // 当前处于第0天
        int index = 0;      // 会议按照最早开始时间存储的编号
        int n = events.length;   // 会议总数
        int ans = 0;             // 结果集
        // 循环结束条件
        while(index < n || !queue.isEmpty()){
            // 如果队列为空，将当前的项目结束时间扔进去，day调整为当前的时间（有了这部，queue不可能为空）
            if(queue.isEmpty()){
                queue.add(events[index][1]);
                cur = events[index++][0];
            }
            // 遍历后面的会议。如果开始时间在day时间之间，都将他们入独对列
            while(index < n && events[index][0] <= cur){
                queue.add(events[index++][1]);
            }
            // 如果当前会议可以进行
            if(queue.peek() >= cur){
                cur++;
                ans++;
            }
            //没从都从queue中检索数组，不管可不可行，queue都必须弹出数据
            queue.poll();
        }
        return ans;
    }
}
