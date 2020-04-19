package my_code_sum_up.code_bt;

//79. 单词搜索
// 方法一：DFS回溯思想
public class Test79 {

    //-----------------------------方法一：DFS回溯思想--------------------------------------------
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        if(row == 0) return false;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(dfs(word,0,i,j,board,visited)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(String word, int w, int r, int c, char[][] board, boolean[][] visited) {
        // 如果r和c指针越界，返回false
        if(r < 0 || c < 0 || r >= board.length || c >= board[0].length) {
            return false;
        }
        // 如果当前位置已经访问，或者当前位置的元素不等于需要匹配的word的元素，那么直接返回false
        if(visited[r][c] || board[r][c] != word.charAt(w)){
            return false;
        }
        // 如果当前已经匹配到了字符串的最后一位，则返回false；
        if(w == word.length() - 1){
            return true;
        }
        visited[r][c] = true;
        //上下左右四个方法进行试探
        boolean up = dfs(word,w+1,r-1,c,board,visited);
        if(up){
            return true;
        }
        boolean down = dfs(word,w+1,r+1,c,board,visited);
        if(down){
            return true;
        }
        boolean left = dfs(word,w+1,r,c-1,board,visited);
        if(left){
            return true;
        }
        boolean right = dfs(word,w+1,r,c+1,board,visited);
        if(right){
            return true;
        }
        // 当前的四个方向都没有试探成功， 则将此位置置为false，返回false，不访问
        visited[r][c] = false;
        return false;
    }

    //-----------------------------------方法一：优化DFS----------------------------------------------
    public boolean exist2(char[][] board, String word) {
        int row = board.length;
        if(row == 0) return false;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(dfs2(word,0,i,j,board)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs2(String word, int w, int r, int c, char[][] board) {
        // 如果r和c指针越界，返回false
        if(r < 0 || c < 0 || r >= board.length || c >= board[0].length) {
            return false;
        }
        // 如果当前位置已经访问，或者当前位置的元素不等于需要匹配的word的元素，那么直接返回false
        if(board[r][c] != word.charAt(w)){
            return false;
        }
        // 如果当前已经匹配到了字符串的最后一位，则返回false；
        if(w == word.length() - 1){
            return true;
        }
        char temp = board[r][c];
        board[r][c] = '!';   //将当前位置变为一个不可能出现的字符，则下次再次访问到此处时，肯定返回false
        //上下左右四个方法进行试探
        boolean up = dfs2(word,w+1,r-1,c,board);
        if(up){
            return true;
        }
        boolean down = dfs2(word,w+1,r+1,c,board);
        if(down){
            return true;
        }
        boolean left = dfs2(word,w+1,r,c-1,board);
        if(left){
            return true;
        }
        boolean right = dfs2(word,w+1,r,c+1,board);
        if(right){
            return true;
        }
        // 当前的四个方向都没有试探成功， 则将此位置置为false，返回false，不访问
        board[r][c] = temp;
        return false;
    }
}