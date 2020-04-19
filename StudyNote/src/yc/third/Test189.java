package yc.third;

public class Test189 {

    // 解法一： 辅助dp
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int[] res = new int[len];
        if (len > 1) {
            k = len % k;
            int index = 0;
            for (int i = len - k; i < len - k + len; i++) {
                res[index++] = nums[(i % len)];
            }
            for (int i = 0; i < len; i++) {
                nums[i] = res[i];
            }
        }
    }

    // 解法二： 一次遍历(空间复杂度1)，时间复杂度超级高
    public void rotate2(int[] nums, int k) {
        int len = nums.length;
        if (len > 1) {
            k = k % len;
            for (int i = 0; i < k; i++) {
                int temp = nums[0];
                for (int j = 0; j < len - 1; j++) {
                    nums[j] = nums[j+1];
                }
                nums[len - 1] = temp;
            }
        }
    }

    // 解法三（三次逆序）(太帅了)
    public void rotate3(int[] nums, int k){
        int len = nums.length;
        if(len > 1){
            k = k % len;
            // 首先整体进行逆序
            reverse(nums,0,len - 1);
            // 然后将数组的前k个进行必须
            reverse(nums,0,k-1);
            // 然后将数组的后len - k个逆序
            reverse(nums,k,len - 1);
        }
    }

    // 逆序s到e
    private void reverse(int[] nums, int s, int e){
        while(s < e){
            int temp = nums[s];
            nums[s] = nums[e];
            nums[e] = temp;
            s++;
            e--;
        }
    }

}
