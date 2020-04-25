package yc.al.zuo.base;

/**
 * 岛屿问题
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 18:59
 */
public class Class5_Code2_Islands {
    public static int countIslands(int[][] m) {
        if(m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int res = 0;   //记录结果
        int row = m.length;
        int col = m[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(m[i][j] == 1) {
                    res++;
                    infect(m,i,j,row,col);
                }
            }
        }
        return res;
    }

    // 感染函数（递归将小岛上的数全部改为2）
    private static void infect(int[][] m, int i, int j, int row, int col) {
        if(i < 0 || i >= row || j < 0 || j >= col || m[i][j] != 1) return;
        m[i][j] = 2;
        infect(m,i+1,j,row,col);
        infect(m,i-1,j,row,col);
        infect(m,i,j+1,row,col);
        infect(m,i,j-1,row,col);
    }

    public static void main(String[] args) {
        int[][] m1 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 0, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println(countIslands(m1));

        int[][] m2 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println(countIslands(m2));
    }
}
