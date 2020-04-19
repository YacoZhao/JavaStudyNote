package yc.first;

//45. 跳跃游戏 II
public class Test45 {

    //贪婪算法
    public int jump(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int step = 0;
        for(int i=0; i< nums.length - 1;i++){
            maxPosition = Math.max(maxPosition,nums[i] + i);
            if(i == end){       //when i == end, change the position of end ans increase step
                end = maxPosition;
                step++;
            }
        }
        return step;
    }
}
