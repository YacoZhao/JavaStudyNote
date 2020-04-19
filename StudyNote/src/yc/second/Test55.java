package yc.second;

public class Test55 {

    public static void main(String[] args) {
        int[] nums = {1,0,0,1,1,2,2,0,2,2};
        System.out.println(canJump(nums));
    }
    /**
     * 动态规划
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        // 1. 设置变化数组dp，表示是否可以跳到第i个位置
        boolean[] dp = new boolean[nums.length];
        //2. 第一个元素是起点，肯定可以跳到
        dp[0] = true;
        //3. 从第二个元素开始遍历
        for (int i = 1; i < nums.length; i++) {
            //3.1 如果上一个元素不为0，所以如果它可以被跳到，
            // 那么当前的元素也一定可以跳到，上一个元素如果挑不到，
            // 那么这个元素也一定无法到达
            if (nums[i - 1] > 0) {
                dp[i] = dp[i - 1];
            } else {
                //3.2 如果上个元素都到达不了，那么这个元素也一定到达不了(优化)
                int tep = 1;
                while(i - tep >= 0 && dp[i - tep]){
                    //只要前面的可以达到，就一直往前找，直到找到可以跳到这里的点
                    if(nums[i - tep] >= tep){
                        dp[i] = true;
                    }
                    tep++;
                }
            }
        }
        return dp[nums.length - 1];
    }
}
