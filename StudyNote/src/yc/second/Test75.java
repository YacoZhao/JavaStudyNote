package yc.second;

public class Test75 {
    /**
     * 计数排序求解
     * @param nums
     */
    public void sortColors(int[] nums) {
        //1. 边界条件判断
        if (nums.length == 0) {
            return;
        }
        //2. 创建一个辅助数组
        int[] temp = new int[3];
        //3. 遍历原来的数组
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                temp[0]++;
            } else if (nums[i] == 1) {
                temp[1]++;
            } else {
                temp[2]++;
            }
        }
        //向原来的数组填入数据
        for (int i = 0; i < nums.length; i++) {
            int ant = temp[0] == 0 ? (temp[1] == 0 ? 2 : 1) : 0;
            if (temp[0] == 0) {
                if (temp[1] == 0) {
                    temp[2]--;
                } else {
                    temp[1]--;
                }
            } else {
                temp[0]--;
            }
            nums[i] = ant;
        }
    }

    /**
     * Leecode官方
     * @param nums
     */
    public void sortColors2(int[] nums) {
        // 对于所有 idx < i : nums[idx < i] = 0
        // j是当前考虑元素的下标
        int p0 = 0, curr = 0;
        // 对于所有 idx > k : nums[idx > k] = 2
        int p2 = nums.length - 1;

        int tmp;
        while (curr <= p2) {
            if (nums[curr] == 0) {
                // 交换第 p0个和第curr个元素
                // i++，j++
                tmp = nums[p0];
                nums[p0++] = nums[curr];
                nums[curr++] = tmp;
            } else if (nums[curr] == 2) {
                // 交换第k个和第curr个元素
                // p2--
                tmp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2--] = tmp;
            } else curr++;
        }
    }
}
