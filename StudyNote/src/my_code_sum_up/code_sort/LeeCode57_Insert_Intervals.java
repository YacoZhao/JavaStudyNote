package my_code_sum_up.code_sort;

import java.util.LinkedList;

/**
 * 插入区间问题
 *      Coding能力
 *
 */
public class LeeCode57_Insert_Intervals {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int newL = newInterval[0];
        int newR = newInterval[1];

        LinkedList<int[]> res = new LinkedList<>();

        int cur = 0;
        int len = intervals.length;
        while(cur < len && newL > intervals[cur][0]){
            res.add(intervals[cur++]);
        }

        int[] interval = new int[2];

        if(res.isEmpty() || res.peekLast()[1] < newL){
            res.add(newInterval);
        }else{
            interval = res.removeLast();
            interval[1] = Math.max(newR,interval[1]);
            res.add(interval);
        }

        while(cur < len){
            interval = intervals[cur++];
            int start = interval[0], end = interval[1];
            if(res.peekLast()[1] < start){
                res.add(interval);
            }else{
                interval = res.removeLast();
                interval[1] = Math.max(end,interval[1]);
                res.add(interval);
            }
        }
        return res.toArray(new int[0][]);
    }
}
