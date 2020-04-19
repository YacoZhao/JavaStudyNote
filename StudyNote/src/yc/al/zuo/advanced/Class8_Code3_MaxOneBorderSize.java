package yc.al.zuo.advanced;

// 8-3 边界都是1的最大正方形大小
public class Class8_Code3_MaxOneBorderSize {

    public static int getMaxSize(int[][] matrix){
        if(matrix == null || matrix.length == 0) return 0;
        // 首先把每个位置右边连续1的个数和下面连续1的个数求出来，用dp数组表示
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] right = new int[row][col];
        int[][] down = new int[row][col];
        updateInformation(matrix,right,down);
        int size = Math.min(row,col);
        for (int i = size; i > 0; i--) {
            if(hasCurSize(right,down,i)){
                return i;
            }
        }
        return 0;
    }

    // 检查边长为size的正方形在数组中是否存在
    private static boolean hasCurSize(int[][] right, int[][] down, int size) {
        for (int i = 0; i < right.length - size + 1; i++) {
            for (int j = 0; j < right[0].length - size + 1; j++) {
                if(right[i][j] >= size && down[i][j] >= size && right[i + size - 1][j] >= size && down[i][j + size - 1] >= size){
                    return true;
                }
            }
        }
        return false;
    }

    // 更新各个数组的信息
    private static void updateInformation(int[][] matrix, int[][] right, int[][] down) {
        int row = matrix.length;
        int col = matrix[0].length;
        if(matrix[row-1][col-1] == 1){
            right[row-1][col-1] = 1;
            down[row-1][col-1] = 1;
        }
        for (int i = row - 2; i >= 0; i--) {
            if(matrix[i][col-1] == 1){
                right[i][col-1] = 1;
                down[i][col-1] = down[i+1][col-1] + 1;
            }
        }
        for (int j = col - 2; j >= 0; j--) {
            if(matrix[row-1][j] == 1){
                right[row-1][j] = right[row-1][j+1] + 1;
                down[row-1][j] = 1;
            }
        }
        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                if(matrix[i][j] == 1){
                    right[i][j] = right[i][j+1] + 1;
                    down[i][j] = down[i+1][j] + 1;
                }
            }
        }
    }


    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ------------------------------测试-----------------------------------
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static int[][] generateRandom01Matrix(int rowSize, int colSize) {
        int[][] res = new int[rowSize][colSize];
        for (int i = 0; i != rowSize; i++) {
            for (int j = 0; j != colSize; j++) {
                res[i][j] = (int) (Math.random() * 2);
            }
        }
        return res;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = generateRandom01Matrix(7, 8);
        printMatrix(matrix);
        System.out.println(getMaxSize(matrix));

        int[][] matrix2 = new int[][]{
                {0, 1, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {0, 1, 0, 0, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 0, 1, 1}
        };
        System.out.println("------------------------------------");
        printMatrix(matrix2);
        System.out.println(getMaxSize(matrix2));
    }
}
