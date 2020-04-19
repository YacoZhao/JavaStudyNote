package yc.al.zuo.advanced;

// 5-5 子数组的最大亦或和

import javax.swing.text.MaskFormatter;

/**
 * 求数组的最大亦或和————前缀树求解（讲过——第六课）
 *
 * 亦或运算： 亦或运算满足交换律和结合率
 * a ^ b = c
 * a = b ^ c
 * a = c ^ b
 *
 */
public class Class5_Code5_Max_EOR {

    // 方法一： 暴力穷举
    public static int maxXor1(int[] arr){
        if(arr == null || arr.length == 0) return 0;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int xor = 0;
            for (int j = i; j < arr.length; j++) {
                xor ^= arr[j];
                res = Math.max(res,xor);
            }
        }
        return res;
    }

    // 方法二：使用dp数组存储计算过的值
    public static int maxXor2(int[] arr) {
        if(arr == null || arr.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int[] dp = new int[arr.length];
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            for (int start = 1; start <= i; start++) {
                int startToIZxor = eor ^ dp[start - 1];
                max = Math.max(max,startToIZxor);
            }
            dp[i] = eor;
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    // 方法三： 使用前缀树进行求解
    public static int maxXor3(int[] arr) {
        if(arr == null || arr.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        // 创建一个前缀树
        TrimTree tree = new TrimTree();
        tree.add(0);
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            max = Math.max(max,tree.maxXor(xor));
            tree.add(xor);
        }
        return max;
    }

    static class Node{
        Node[] nexts = new Node[2];
    }

    static class TrimTree{
        private Node head = new Node();

        // 向前缀树中添加一个元素
        private void add(int num){
            Node cur = head;
            for (int move = 31; move >= 0; move--) {
                int path = (num >> move) & 1;  // 按位与出当前的位置的元素
                if(cur.nexts[path] == null){
                    cur.nexts[path] = new Node();
                }
                cur = cur.nexts[path];
            }
        }

        // 添加一个元素，使得数组种摸一个前缀的亦或和与当前的亦或和亦或之后结果最大
        private int maxXor(int num){
            Node cur = head;
            int res = 0;
            for (int move = 31; move >= 0; move--) {
                int path = (num >> move) & 1;
                int best = move == 31 ? path : path^1;
                best = cur.nexts[best] != null ? best : best^1;
                res |= (path^best) << move;
                cur = cur.nexts[best];
            }
            return res;
        }
    }


    // +++++++++++++++++++++++++++测试集++++++++++++++++++++++++++++++++
    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int eor = 0;
            for (int j = i; j < arr.length; j++) {
                eor ^= arr[j];
                max = Math.max(max, eor);
            }
        }
        return max;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 30;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int res = maxXor3(arr);
            int comp = comparator(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
