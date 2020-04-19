package my_code_sum_up.code_bt;

//N皇后问题Ⅱ
// 方法：回溯算法
public class Test52 {
    // --------------------------------------------经典回溯解法-------------------------------------------------------
    int res = 0;

    public int totalNQueens(int n) {
        if(n != 0){
            boolean[][] site = new boolean[n][n];
            backtrack(0,n,site);
        }
        return res;
    }

    /**
     * @param depth       当前深度
     * @param queue_num   皇后数
     * @param site        标志位数组
     */
    private void backtrack(int depth, int queue_num, boolean[][] site) {
        //结束条件
        if(depth == queue_num){
            res++;
            return;
        }
        //遍历位置0 到  n-1
        for (int i = 0; i < queue_num; i++) {
            if(isLocation(depth,i,site)){
                site[depth][i] = true;
                //追溯
                backtrack(depth + 1, queue_num, site);
                //回溯
                site[depth][i] = false;
            }
        }
    }

    /**
     * 判断当前位置放置皇后是否可行（因为皇后填的顺序是自上而下，所以不需要判断当前位置后面的位置）
     * @param depth    当前深度，也就是行数
     * @param cur      在当前行的第几个位置方式皇后
     * @param site     皇后存在位置矩阵，存在为true，不存在为false
     * @return
     */
    private boolean isLocation(int depth, int cur, boolean[][] site) {
        //检查列是否冲突
        for (int i = 0; i <= depth; i++) {
            if(site[i][cur]){
                return false;
            }
        }
        //检查左上角元素是否可行
        int x = depth, y = cur,n = site.length;
        while(x >= 0 && y >= 0 && x < n && y < n){
            if(site[x--][y--]){
                return false;
            }
        }
        //检查右上角元素是否可行
        x = depth; y = cur;
        while(x >= 0 && y >= 0 && x < n && y < n){
            if(site[x--][y++]){
                return false;
            }
        }
        return true;
    }
}
