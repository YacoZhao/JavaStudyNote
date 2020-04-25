package yc.al.zuo.base;

/**
 * 背包问题
 * @author code_yc
 * @version 1.0
 * @date 2020/4/25 8:51
 */
public class Class8_Code7_Knapsack {

    /**
     * 解法一：暴力递归
     * @param w
     * @param v
     * @param bag
     * @return
     */
    public static int maxValue1(int[] w, int[] v, int bag){
        return process1(w,v,0,0,bag);
    }

    /**
     * 暴力递归
     * @param w  重量数组
     * @param v  价值素组
     * @param curL  当前遍历的商品数组的长度
     * @param curW  当前已经取得商品的总重量
     * @param bag   背包的最大载重
     * @return
     */
    private static int process1(int[] w, int[] v, int curL, int curW, int bag) {
        //如果当前背包装载的商品总质量已经大于背包载重限制，则返回0
        if(curW > bag) {
            return 0;
        }
        //如果当前的商品已经全部遍历完了，则返回0
        if(curL == w.length){
            return 0;
        }
        return Math.max(process1(w,v,curL+1,curW,bag),v[curL] + process1(w,v,curL+1,curW+w[curL],bag));
    }

    public static void main(String[] args) {
        int[] w = { 3, 2, 4, 7 };
        int[] v = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(maxValue1(w, v, bag));
        //System.out.println(maxValue2(c, p, bag));
    }

}
