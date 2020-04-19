package yc.first;
//33. 搜索旋转排序数组
public class Test33 {
    /**
     * 主方法
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if(nums.length == 0) return -1;
        if(nums.length == 1) return target == nums[0] ? 0 : -1;
        int len = nums.length;
        //如果没有旋转节点
        if(nums[0] < nums[len - 1]){
            //直接调用二分查找进行搜索
            return binarySearch(nums,0,len - 1,target);
        }else{
            //二分查找找出旋转节点的位置
            int rotate = findRotate(nums);
            //如果target <= nums[len - 1],在后面查找，否则去前面查找
            if(target < nums[0]){
                return binarySearch(nums,rotate,len - 1,target);
            }else{
                return binarySearch(nums,0,rotate - 1,target);
            }
        }
    }

    /**
     * 二分查找旋转节点的位置
     * @param nums
     * @return
     */
    private int findRotate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid  = left + (right - left + 1) / 2;
            if (mid > 0 && nums[mid] < nums[mid - 1]){
                return mid;
            }else if(nums[mid] >= nums[0]){
                left = mid + 1;
            }else if(nums[mid] <= nums[nums.length - 1]){
                right = mid - 1;
            }
        }
        return 0;
    }

    /**
     * 二分查找
     * @param nums
     * @param left
     * @param right
     * @param target
     * @return
     */
    private int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // 二分查找算法
/*
    public int search(int[] nums, int target) {
        //1.进行局部变量的赋值
        this.nums = nums;
        this.target = target;
        //2. 先进行边界条件判断
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int n = nums.length;
        //3. 找出旋转节点
        int rotate = find_rotate_point(0, n - 1);
        //4. 如果找出的节点值解释target，直接返回
        if(target == nums[rotate]){
            return rotate;
        }
        //5. 如果旋转节点为0，则直接进行二分查找
        if (rotate == 0) {
            return binarySearch(0, n - 1);
        }
        if (target > nums[0]) {
            return binarySearch(0, rotate);
        } else{
            return binarySearch(rotate, n - 1);
        }
    }

    //首先编写一个方法，用于从数组nums中找出旋转的节点
    private int find_rotate_point(int left, int right) {
        //1. 进行边界条件判断，如果左边小于右边，则一定是标准的升序排序数组
        if (nums[left] < nums[right]) {
            return 0;
        }
        //2. 循环进行二分查找，找到一个节点rotate,使得nums[rotate] > nums[rotate+1]
        while (left <= right) {
            int rotate = (left + right) / 2;
            if (nums[rotate] > nums[rotate + 1]) {    //3. 如果找到了，则返回此rotate后一个节点
                return rotate + 1;
            } else if (nums[rotate] < nums[left]) {  //4. 如果rotate节点在后半段，则右指针左移
                right = rotate - 1;
            } else {                                //5. 否则，左指针右移
                left = rotate + 1;
            }
        }
        return 0;
    }

    //再编写一个二分查找算法
    private int binarySearch(int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
*/

    //二刷


}
