package yc.second;

//59. 螺旋矩阵 II
public class Test59 {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        boolean[][] use = new boolean[n][n];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int di = 0;
        int r = 0, c = 0;
        for (int i = 1; i <= n * n; i++) {
            res[r][c] = i;
            use[r][c] = true;
            //更新r和c
            int cr = r + dr[di];
            int cc = c + dc[di];
            if (cr >= 0 && cr < n && cc >= 0 && cc < n && !use[cr][cc]) {
                r = cr;
                c = cc;
            } else {
                di = (di + 1) % 4;
                r = r + dr[di];
                c = c + dc[di];
            }
        }
        return res;
    }
}
