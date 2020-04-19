package yc.first;

//42. 接雨水
public class Test42 {

    //方法一： 按列求解
    public int trap(int[] height) {
        int ans = 0;

        for(int i=1; i< height.length-1;i++){
            //找出当前列左边的最高的墙
            int max_left = 0;
            for(int j = i -1 ; j >= 0;j--){
                if(height[j] > max_left){
                    max_left = height[j];
                }
            }
            //找出当前列右边的最高的墙
            int max_right = 0;
            for(int j= i+1; j < height.length;j++){
                if(height[j] > max_right){
                    max_right = height[j];
                }
            }
            //找出左右墙中相对较小的
            int min = Math.min(max_right,max_left);
            //补水操作
            if(min > height[i]){
                ans += min - height[i];
            }
        }
        return ans;
    }


    //方法二： 动态规划优化找左右的最大墙
    public int trap2(int[] height) {
        int ans = 0;
        int[] left_max = new int[height.length];
        int[] right_max = new int[height.length];
        for (int i = 1; i < height.length; i++) {  //动态规划思想
            left_max[i] = Math.max(left_max[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            right_max[i] = Math.max(right_max[i + 1], height[i + 1]);
        }

        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(left_max[i], right_max[i]);
            if (min > height[i]) {
                ans += min - height[i];
            }
        }
        return ans;
    }

}
