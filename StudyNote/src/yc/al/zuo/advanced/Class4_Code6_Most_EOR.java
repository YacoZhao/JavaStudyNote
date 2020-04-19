package yc.al.zuo.advanced;

import java.util.HashMap;

// 4-6  异或和为0的子数组最多是多少
public class Class4_Code6_Most_EOR {

    public static int mostEOR(int[] arr) {
        int ans = 0;  // 存储结果数
        int xor = 0;  // 存储 0 - i的亦或和
        // 存储每个位置结尾的子数组最大划分个数
        int[] dp = new int[arr.length];
        // 将每个位置的累加亦或和记录下来
        HashMap<Integer, Integer> map = new HashMap<>();
        // 向map中存储初始值，0亦或任何元素都是他自己
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            if (map.containsKey(xor)) {
                // case2的情况
                // 从前面找到那个亦或累加和等于xor的最后一个元素，那么当前的划分次数就在那个位置再加上1
                int pre = map.get(xor);
                dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
            }
            if (i > 0) {
                //case1的情况，房前结果起始和前面的结果是一样的，但是这里没有用else，直切求出Max(dp[i - 1] 和 dp[i])
                dp[i] = Math.max(dp[i - 1], dp[i]);
            }
            // 每次更新了xor都要入map，因为要保证是最近更新的xor，才可以保证划分子数组最大，划分方式就会尽可能的多
            map.put(xor, i);
            // 每次更新dp，都更新ans
            ans = Math.max(ans, dp[i]);
        }
        // 返回ans
        return ans;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                         测试集
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] eors = new int[arr.length];
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            eors[i] = eor;
        }
        int[] mosts = new int[arr.length];
        mosts[0] = arr[0] == 0 ? 1 : 0;
        for (int i = 1; i < arr.length; i++) {
            mosts[i] = eors[i] == 0 ? 1 : 0;
            for (int j = 0; j < i; j++) {
                if ((eors[i] ^ eors[j]) == 0) {
                    mosts[i] = Math.max(mosts[i], mosts[j] + 1);
                }
            }
            mosts[i] = Math.max(mosts[i], mosts[i - 1]);
        }
        return mosts[mosts.length - 1];
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
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

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 300;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int res = mostEOR(arr);
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
