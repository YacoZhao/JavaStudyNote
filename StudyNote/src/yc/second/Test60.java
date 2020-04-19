package yc.second;

import javax.sound.sampled.Line;

//60. 第k个排列
public class Test60 {

    static StringBuilder res = new StringBuilder();

    public static String getPermutation(int n, int k) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        boolean[] use = new boolean[n];
        getCurElement(arr, use, k, n);
        return res.toString();
    }

    private static void getCurElement(int[] arr, boolean[] use, int k, int n) {
        int curForms = fiactorial(n - 1);
        int choice = k / curForms + (k % curForms == 0 ? 0 : 1);
        int others = k % curForms;
        //找出待元素的元素索引
        int i = 0;
        for ( ;i < use.length; i++) {
            if (!use[i]) {
                choice--;
            }
            if (choice <= 0) {
                break;
            }
        }
        //将标志位设置位true
        use[i] = true;
        res.append(arr[i] + "");
        //如果other等于0，从后向前遍历，加完返回
        if (others == 0) {
            for (int j = arr.length - 1; j >= 0; i--) {
                if (!use[j]) {
                    res.append(use[j] + "");
                }
            }
            return;
        }
        getCurElement(arr, use, others, n - 1);
    }

    /**
     * 求阶乘
     * @param n
     * @return
     */
    private static int fiactorial(int n) {
        if (n <= 1) {
            return 1;
        }
        int sum = 1;
        for (int i = 1; i <= n; i++) {
            sum = sum * i;
        }
        return sum;
    }

    public static void main(String[] args) {
        String permutation = getPermutation(3, 3);
        System.out.println(permutation);
    }
}
