package yc.al.zuo.advanced;

/**
 * 汉诺塔问题——————打印汉诺塔的移动过程
 * @author code_yc
 * @version 1.0
 * @date 2020/4/22 19:53
 */
public class Class1_Code2_HanoiProblem {

    // 打印汉诺塔的移动过程
    public static void hanoi(int n) {
        process(n,"左","中","右");
    }

    /**
     * n参数只表示元素的个数
     * @param n
     * @param left
     * @param middle
     * @param right
     */
    private static void process(int n, String left, String middle, String right) {
        if(n == 1){
            // 边界条件，棋子中只有一个元素
            System.out.println("move form" + left + " to " + right);
        }else{
            // 首先将左边n-1个元素借助右边移动到中间
            process(n-1,left,right,middle);
            // 将左边的最后一个元素移动到右边
            process(1,left,middle,right);
            // 最后将中间的n-1移动到右边
            process(n-1,middle,left,right);
        }
    }

    public static void main(String[] args) {
        hanoi(2);
    }
}
