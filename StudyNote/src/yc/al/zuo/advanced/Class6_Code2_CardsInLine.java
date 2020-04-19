package yc.al.zuo.advanced;

// 6-2 纸牌博弈问题
public class Class6_Code2_CardsInLine {

    public static int win1(int[] arr) {
        if(arr == null || arr.length == 0) return 0;
        // 从先选和后选中取出最大值
        return Math.max(f(arr,0,arr.length - 1),s(arr,0,arr.length - 1));
    }

    // 后选
    private static int s(int[] arr, int l, int r) {
        if(l == r) {
            return 0;
        }
        // 先选的人一定将当前可以选择的最好结果选走了，所以候选这里只能取两者的较小值
        return Math.min(f(arr, l + 1, r),f(arr, l, r - 1));
    }

    // 先选
    private static int f(int[] arr, int l, int r) {
        if(l == r){
            return arr[l];
        }
        return Math.max(arr[l] + s(arr, l + 1, r), arr[r] + s(arr, l, r - 1));
    }

    // 改造动摇规划
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        for (int j = 0; j < arr.length; j++) {
            f[j][j] = arr[j];
            for (int i = j - 1; i >= 0; i--) {
                f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
            }
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,2,100,4};
        System.out.println(win1(arr));
        System.out.println(win2(arr));

    }
}
