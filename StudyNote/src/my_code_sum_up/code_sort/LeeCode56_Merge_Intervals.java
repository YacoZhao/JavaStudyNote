package my_code_sum_up.code_sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并区间
 *      Coding 加思维
 *
 */
public class LeeCode56_Merge_Intervals {

    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length < 2) return intervals;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int i = 0;  // 表示当前元素索引
        List<int[]> res = new ArrayList<>();
        int l = intervals.length;
        while(i < l){
            int left = intervals[i][0];
            int right = intervals[i][1];
            while(i + 1 < l && right >= intervals[i+1][0]){
                i++;
                right = Math.max(right,intervals[i][1]);
            }
            res.add(new int[]{left,right});
            i++;
        }
        return res.toArray(new int[0][]);
    }
}
