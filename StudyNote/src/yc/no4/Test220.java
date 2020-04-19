package yc.no4;

import javax.swing.undo.AbstractUndoableEdit;
import java.util.HashMap;

public class Test220 {

    //最快的解法：桶排序的思想***O（N）
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //首先如果t小于0，一定返回false,因为两个数的绝对值不可能为负数
        if(t < 0 )return false;
        //创建桶结构，用hashMap来表示
        HashMap<Long,Long> bucket = new HashMap<Long, Long>();
        // 创建每个桶中的容量
        long w = t + 1;
        //遍历nums数组
        for (int i = 0; i < nums.length; i++) {
            //首先如果当前遍历到的i大于k，则弹出之前保存过的k值
            if(i > k) {
                bucket.remove(getId(nums[i - k - 1], w));
            }
            //获取当前桶的id
            long curId = getId(nums[i],w);
            //如果当前的桶里面已经有元素了，则新加入的元素与其差值的绝对值，绝对在t之内，直接返回true
            if(bucket.containsKey(curId)){
                return true;
            }
            // 如果当前桶的后一个存在，且他们之间差值小于等于t，则也返回true
            if(bucket.containsKey(curId + 1) && bucket.get(curId + 1) - nums[i] <= t){
                return true;
            }
            // 如果当前桶的前一个存在， 且他们之间的差值小于等于t，则也返回true
            if(bucket.containsKey(curId - 1) && nums[i] - bucket.get(curId - 1) <= t){
                return true;
            }
            // 如果以上条件都没满足，则将此元素放入桶中
            bucket.put(curId,(long)nums[i]);
        }
        // 遍历结束之后都没有返回true，则不存在返回false
        return false;
    }

    // 获取数据存放的桶的编号
    private long getId(long num, long w){
        if(num >= 0){
            return num / w;
        }else {
            return (num + 1) / w - 1;
        }
    }


    // 解法二： 暴力法(溢出 + 超时)
    // 从每个元素开始，依次遍历当前元素的后k个位置，比较差值是否小于等于t
    // 问题： 回出先溢出问题nums[i] - nums[i+j]
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        if(t < 0) return false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= k; j++) {
                if(i + j >= nums.length){
                    break;
                }
                if(Math.abs(nums[i] - nums[i+j]) <= t){
                    return true;
                }
            }
        }
        return false;
    }

    // 解法二： 暴力法——改进 （溢出 + 超时）
    // 从每个元素开始，依次遍历当前元素的后k个位置，比较差值是否小于等于t
    // 问题： 回出先溢出问题nums[i] - nums[i+j]
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        if(t < 0) return false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= k; j++) {
                if(i + j >= nums.length){
                    break;
                }
                if(Math.abs((long) nums[i] - nums[i+j]) <= t){
                    return true;
                }
            }
        }
        return false;
    }

    // 解法二： 暴力法——改进(溢出判断)（超时）
    // 从每个元素开始，依次遍历当前元素的后k个位置，比较差值是否小于等于t
    // 问题： 回出先溢出问题nums[i] - nums[i+j]
    public boolean containsNearbyAlmostDuplicate3(int[] nums, int k, int t) {
        if(t < 0) return false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= k; j++) {
                if(i + j >= nums.length){
                    break;
                }
                int sub = nums[i] - nums[i + j];
                // 1. 正数 - 负数  如果溢出的话，结果应该是负数
                if(nums[i] > 0 && nums[i + j] < 0){
                    if(sub < 0){
                        continue;
                    }
                }
                //2. 负数 - 正数  如果结果为正数，则溢出
                if(nums[i] < 0 && nums[i + j] > 0){
                    if(sub > 0 || sub == Integer.MIN_VALUE){
                        continue;
                    }
                }
                if(Math.abs(sub) <= t){
                    return true;
                }
            }
        }
        return false;
    }
}
