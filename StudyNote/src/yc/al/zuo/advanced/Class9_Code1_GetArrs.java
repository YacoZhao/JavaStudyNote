package yc.al.zuo.advanced;

import java.util.Arrays;
import java.util.LinkedList;

public class Class9_Code1_GetArrs {

    // 生成一个包含1 - N的数组，要求数组中随机出来的长度为3的子序列不为等差数列
    // 主函数
    public static int[] getArr(int n){
        if(n < 3) return null;
        // 首先创建出一个数组arr，将1 —— N 全部扔进去
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        changeList(arr,0,n-1);
        return arr;
    }

    /**
     * 调整数组————给定左右指针（取出l到r位置上的元素，按照左边右边循环加入）
     * @param arr
     * @param l
     * @param r
     */
    private static void changeList(int[] arr,int l, int r) {
        if(r - l < 2){
            return;
        }
        LinkedList<Integer> list = new LinkedList<>();
        int cur = 0;
        while(cur < (r - l + 1)){
            if(cur % 2 == 0){
                list.addLast(arr[l + cur]);
            }else {
                list.addFirst(arr[l + cur]);
            }
            cur++;
        }
        // 链表遍历出来的数加入数组
        int index = l;
        for (Integer num : list) {
            arr[index++] = num;
        }
        // 二分查找进入递归体
        int mid = (l + r + 1) / 2;
        changeList(arr,l,mid - 1);
        changeList(arr,mid,r);
    }

    // 测试函数
    public static void main(String[] args) {
        int[] res = getArr(100000);
        System.out.println(Arrays.toString(res));
        System.out.println(test(res));
    }

    // 对数器
    public static boolean test(int[] arr){
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if(arr[i] - arr[j] == arr[j] - arr[k]){
                        System.out.println(arr[i] + "--" + arr[j] + "--" + arr[k]);
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
