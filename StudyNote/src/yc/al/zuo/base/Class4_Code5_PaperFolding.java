package yc.al.zuo.base;

/**
 * 折痕问题
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 18:49
 */
public class Class4_Code5_PaperFolding {

    public static void printAllFolds(int N) {
        process(1,N,true);
    }

    private static void process(int i, int n, boolean down) {
        if(i > n) return;
        process(i + 1,n,true);     // 上半部分是向下折
        System.out.println(down ? "down" : "up");
        process(i + 1, n, false);  // 下半部分是向上折
    }

    public static void main(String[] args) {
        printAllFolds(3);
    }
}
