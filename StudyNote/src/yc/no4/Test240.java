package yc.no4;

public class Test240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            if(matrix[i][0] < target){
                //调用二分查找算法
                if(binarySearch(matrix[i],target)){
                    return true;
                }
            }else if(matrix[i][0] == target){
                return true;
            }else{
                break;
            }
        }
        return false;
    }

    private boolean binarySearch(int[] matrix, int target) {
        int left = 0;
        int right = matrix.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(matrix[mid] == target){
                return true;
            }else if(matrix[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return false;
    }

    /**
     * 解法二： 参考答案
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0) return false;
        int row = 0, col = matrix[0].length - 1;
        while(row < matrix.length && col >= 0){
            if(target > matrix[row][col]){
                row++;
            }else if(target < matrix[row][col]){
                col--;
            }else{
                return true;
            }
        }
        return false;
    }
}
