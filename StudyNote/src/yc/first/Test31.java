package yc.first;

//31. 下一个排列
public class Test31 {
    public void nextPermutation(int[] nums) {
        //1. 进行边界条件判断
        if (nums.length <= 1) {
            return;
        }
        //2. 创建所需要的指针变量(i指向倒数第二个变量，j指向倒数第一个变量，k指向倒数第一个位置)
        int i = nums.length - 2, j = nums.length - 1, k = nums.length - 1;
        //3. 从右向左遍历数组，找到第一组nums[i] < nums[j]的情况
        while (i >= 0 && nums[i] >= nums[j]) {
            i--;
            j--;
        }
        //4. 得到循环之后的结果，如果没有找到则返回的i为-1，所以没有找到的话直接将数组升序排列就可以了
        if (i >= 0) {
            //5. 找到了替换的位置，再从右向左搜索第一个比nums[i]大的数，让其和nums[i]进行交换
            while (i < k && nums[i] >= nums[k]) {
                k--;
            }
            //6. 找到了k值之后，与A[i]交换
            int temp = nums[i];
            nums[i] = nums[k];
            nums[k] = temp;
        }
        //7. 交换之后，将i后面的元素按升序进行重新排序 (交换之后i后面的元素必然是降序排列，可验证，故这里直接循环头尾互换)
        int num = (nums.length - i - 1) / 2;
        while (num > 0) {
            int val = nums[i + num];
            nums[i + num] = nums[nums.length - num];
            nums[nums.length - num] = val;
            num--;
        }
    }
}
