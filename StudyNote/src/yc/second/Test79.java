package yc.second;

public class Test79 {

    String words;
    char[][] board;
    boolean[][] use;
    int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public boolean exist(char[][] board, String word) {
        //1. 边界条件判断
        if (board.length == 0) {
            return false;
        }
        int row = board.length;
        int col = board[0].length;
        use = new boolean[row][col];
        this.words = word;
        this.board = board;
        int len = words.length();
        //2.遍历数组，判断当前点回溯下去的字符串是否可以得到
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dfs(i, j, 0, len)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断以当前i，j搜索下雨的点是否可以找到字符串word
     *
     * @param i
     * @param j
     * @param start
     * @return
     */
    private boolean dfs(int i, int j, int start, int len) {
        if (start == len - 1) {
            return words.charAt(start) == board[i][j];
        }
        if (board[i][j] == words.charAt(start)) {
            use[i][j] = true;
            for (int k = 0; k < 4; k++) {
                int newX = i + direction[k][0];
                int newY = j + direction[k][1];
                if (inAera(newX, newY) && !use[newX][newY] && dfs(newX, newY, start + 1, len)) {
                    return true;
                }
            }
            use[i][j] = false;
        }
        return false;
    }

    /**
     * 判断当前的xy坐标是否有小
     *
     * @param x
     * @param y
     * @return
     */
    private boolean inAera(int x, int y) {
        return x >= 0 && y >= 0 && x < board.length && y < board[0].length;
    }
}
