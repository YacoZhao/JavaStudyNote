package yc.al.zuo.base;

/**
 * 在二维排序数组中寻找一个数
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 11:25
 */
public class Class3_Code14_FindNumInSortedMatrix {

    public static boolean isContains(int[][] matrix, int K) {
        // 从数组左下角开始遍历
        int row = matrix.length - 1;
        int col = matrix[0].length - 1;
        int cR = row;
        int cC = 0;
        while(cR >= 0 && cC <= col) {
            if(matrix[cR][cC] > K){
                cR--;
            }else if(matrix[cR][cC] < K) {
                cC++;
            }else{
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },// 0
                { 10, 12, 13, 15, 16, 17, 18 },// 1
                { 23, 24, 25, 26, 27, 28, 29 },// 2
                { 44, 45, 46, 47, 48, 49, 50 },// 3
                { 65, 66, 67, 68, 69, 70, 71 },// 4
                { 96, 97, 98, 99, 100, 111, 122 },// 5
                { 166, 176, 186, 187, 190, 195, 200 },// 6
                { 233, 243, 321, 341, 356, 370, 380 } // 7
        };
        int K = 98;
        System.out.println(isContains(matrix, K));
    }
}
