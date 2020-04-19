package yc.first;

import java.util.*;

public class LeeCodeTest {

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len = nums.length;
        if(nums ==null || len == 0) return false;
        if(k == 0) return false;
        for (int i = 0; i < nums.length; i++) {
            int v1 = nums[i];
            int index = 1;
            while(index <= k && i + index < len){
                if(Math.abs((long) (nums[i+index] - nums[i])) <= t){
                    return true;
                }
                index++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {2,2};
        System.out.println(containsNearbyAlmostDuplicate(arr,3,0));
    }
}


