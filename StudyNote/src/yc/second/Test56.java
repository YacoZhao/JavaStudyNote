package yc.second;

import java.util.*;

public class Test56 {
    public int[][] merge(int[][] intervals) {
        //1. 进行边界条件判断
        if(intervals == null && intervals.length < 2){
            return intervals;
        }
        //2. 创建一个集合，用于存放结果
        List<int[]> res = new ArrayList<>();
        //3. 将原来的数组按照元素第一位进行排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        //4. 循环来进行排序
        int i = 0;
        int n = intervals.length;
        while(i < n){
            int left = intervals[i][0];
            int right = intervals[i][1];
            //如果当前元素的下一行左边小于当前元素的右变，则合并
            while(i + 1 < n && right > intervals[i+1][0]){
                i++;
                right = Math.max(right,intervals[i][1]);
            }
            //将此合并结果加入结果集
            res.add(new int[]{left,right});
            i++;
        }
        //5.返回结果
        return res.toArray(new int[0][]);
    }
}
