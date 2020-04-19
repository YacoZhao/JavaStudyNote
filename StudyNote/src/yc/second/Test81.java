package yc.second;

public class Test81 {
    /**
     *
     * 本题是需要使用二分查找，怎么分是关键，举个例子：
     *
     * 第一类
     * 101111011110111 和 111011110111101 这种。此种情况下 nums[start] == nums[mid]，分不清到底是前面有序还是后面有序，此时 start++ 即可。相当于去掉一个重复的干扰项。
     * 第二类
     * 222 333 444 555 666 777 111 这种，也就是 nums[start] < nums[mid]。此例子中就是 2 < 5；
     * 这种情况下，前半部分有序。因此如果 nums[start] <=target<nums[mid]，则在前半部分找，否则去后半部分找。
     * 第三类
     * 666 777 111 222 333 444 555 这种，也就是 nums[start] > nums[mid]。此例子中就是 6 > 2；
     * 这种情况下，后半部分有序。因此如果 nums[mid] <target<=nums[end]。则在后半部分找，否则去前半部分找。
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        //二分查找循环
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {  //ture条件判断
                return true;
            }
            //分成三种情况，一个mid位置和start位置值相同，还有小于和大于
            if (nums[mid] == nums[start]) {
                //值相等时，start位右移
                start++;
                continue;
            }
            if (nums[mid] > nums[target]) {
                //两种情况，target在左边的范围内，向左搜索
                if (target < nums[mid] && nums[start] <= target) { //如果目标值在前半段，
                    end = mid - 1;
                } else {
                    //否则，向外搜索
                    start = mid + 1;
                }
            } else {
                //再分两种情况，如果在右边，向右搜索
                if (target > nums[mid] && nums[end] >= target) {
                    start = mid + 1;
                } else {
                    //否则，向内搜索
                    end = mid - 1;
                }
            }
        }
        return false;
    }

    //二刷
    public boolean search1(int[] nums, int target) {
        if(nums.length == 0) return false;
        if(nums.length == 1) return target == nums[0];
        //首先找出旋转节点的位置
        int rotate = -1;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] < nums[i - 1]){
                rotate = i;
                break;
            }
        }
        if(rotate == -1){
            //简单二分查找
            return binarySearch(nums,0,nums.length - 1, target);
        }else{
            //如果target大于nums[0]
            if(target >= nums[0]){
                //二分查找
                return binarySearch(nums,0,rotate-1,target);
            }else{
                //二分查找
                return binarySearch(nums,rotate,nums.length - 1,target);
            }
        }
    }

    private boolean binarySearch(int[] nums,int left, int right, int target) {
        if(target < nums[left] || target > nums[right]) return false;
        while(left <= right){
            int mid = left - (right - left) / 2;
            if(nums[mid] < target) {
                left = mid + 1;
            }else if(nums[mid] > target){
                right = mid - 1;
            }else{
                return true;
            }
        }
        return false;
    }

}
