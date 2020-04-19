package yc.first;

//解数独
public class Test37 {
    public void solveSudoku(char[][] board) {
        //1. 首先将char[][]数组转化为int[][]数组，与便于添加元素
        int[][] table = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    break;
                }
                table[i][j] = board[i][j] - '0';
            }
        }
        //2. 调用dfs()，深度优先遍历算法(回溯)
        dfs(table, 0, 0);
        //3. 再将遍历完的数组返回当原来的char[][]中
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (table[i][j] > 0) {
                    board[i][j] = (char) ('0' + table[i][j]);
                }
            }
        }
    }

    //DFS搜索
    //x,y表示当前搜索的位置
    private boolean dfs(int[][] table, int x, int y) {
        //1. 设置DFS结束的边界条件
        if (x == 9) {
            return true;
        }
        if (y == 9) {
            return dfs(table, x + 1, 0);
        }
        if (table[x][y] != 0) {
            return dfs(table, x, y + 1);
        }
        //2. 开始向网格中填入数据，1-9，循环遍历1-9
        for (int r = 1; r <= 9; r++) {
            //2.1 设置标志位，表明当前位置填r是否可行
            boolean ok = true;

            //2.2 检查行中是否存在r，如果存在则标志位置为false，
            for (int i = 0; i < 9; i++) {
                if (table[x][i] == r) {
                    ok = false;
                    break;
                }
            }

            //2.3 检查列中是否存在r，如果存在则标志位置为false，
            for (int i = 0; i < 9; i++) {
                if (table[i][y] == r) {
                    ok = false;
                    break;
                }
            }
            //2.4 检查小方格中的元素
            int cow = (x / 3) * 3;
            int col = (y / 3) * 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (table[cow + i][col + j] == r) {
                        ok = false;
                        break;
                    }
                }
            }
            //2.5 填值：如果标志为true，表示可以填入，填值
            if (ok) {
                table[x][y] = r;
                //2.5.1 如果下一位置填值也成功，则返回true，否则取消填值，此位置的值还是置为0；
                if (dfs(table, x, y + 1)) {
                    return true;
                }
                table[x][y] = 0;
            }
        }
        return false;
    }
}
