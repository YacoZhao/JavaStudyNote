package yc.third;

public class Test164 {

    public int maximumGap(int[] nums) {
        if(nums == null || nums.length < 2) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            min = Math.min(min,nums[i]);
            max = Math.max(max,nums[i]);
        }
        if(min == max) return 0;
        boolean[] hasNum = new boolean[len + 1]; //用于检测数组是否已经存在以元素
        int[] maxs = new int[len + 1];  //用于存放每个桶中最大的数
        int[] mins = new int[len + 1];  //用于存放每个桶中最小的数
        int bid = 0; //计算桶数
        for (int i = 0; i < len; i++) {
            bid = getBucket(nums[i],len,min,max);
            mins[bid] = hasNum[bid] ? Math.min(mins[bid],nums[i]) : nums[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid],nums[i]) : nums[i];
            hasNum[bid] = true;
        }
        int res = 0; //计算结果
        int lastMax = maxs[0];  //第一个桶中肯定有元素
        int i = 1;
        for (; i < len + 1; i++) {
            if(hasNum[i]) {  // 如果桶不为空格
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    private int getBucket(long num, long len, long min, long max) {
        return (int)((num - min) * len / (max - min));
    }
}
