package yc.second;

import java.util.LinkedList;

public class Test57 {

    /**
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int newL = newInterval[0];
        int newR = newInterval[1];

        LinkedList<int[]> res = new LinkedList<>();

        int cur = 0;
        int len = intervals.length;

        //首先考虑newInterval前面的所有可能性加入res

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

        // newInterval后面的所有可能性

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
