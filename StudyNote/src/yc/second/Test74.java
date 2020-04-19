package yc.second;

public class Test74 {
    /**
     * 数组问题——自己写的方法
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        //1. 首先进行边界条件判断
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        //2. 循环遍历数组，找到第一大于target的元素，或者等于的元素
        int i = 0;
        for (; i < matrix.length; i++) {
            if (matrix[i][0] == target) {
                return true;
            } else if (matrix[i][0] > target) {
                break;
            }
        }
        //如果i == 0 肯定没找到
        if (i == 0) {
            return false;
        }
        //从当前行向后面进行搜索
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[i - 1][j] == target) {
                return true;
            }
        }
        return false;
    }

    //二刷
    public boolean searchMatrix1(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        if(target < matrix[0][0] || target > matrix[matrix.length - 1][matrix[0].length - 1]) return false;
        //第一遍二分查找，查找元素可能存在的行数
        int left = 0;
        int right = matrix.length;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(matrix[mid][0] > target){
                right = mid;
            }else if(matrix[mid][0] < target){
                left = mid + 1;
            }else{
                return true;
            }
        }
        int row = left - 1;
        //第二次二分查找
        left = 0;
        right = matrix[0].length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(matrix[row][mid] > target){
                right = mid - 1;
            }else if(matrix[row][mid] < target){
                left = mid + 1;
            }else{
                return true;
            }
        }
        return false;
    }
}
