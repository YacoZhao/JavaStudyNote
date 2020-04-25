package yc.al.zuo.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 逆序对问题
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 8:40
 */
public class Class1_Code11_ReversePairs {

    // 逆序对问题
    public static List<List<Integer>> reversePairs(int[] arr){
        List<List<Integer>> ans = new ArrayList<>();
        if(arr.length > 1) {
            ans = getAns(arr, 0, arr.length - 1);
        }
        return ans;
    }

    // 分治
    private static List<List<Integer>> getAns(int[] arr, int l, int r) {
        List<List<Integer>> ans = new ArrayList<>();
        if(l == r) return ans;
        int mid = l + ((r - l) >> 1);

        List<List<Integer>> left = getAns(arr,l,mid);       // 求出左边的所有逆序对
        List<List<Integer>> right = getAns(arr,mid+1,r);  // 求出右边的所有逆序对
        List<List<Integer>> middle = merge(arr,l,r,mid);    // 合并之后的逆序对
        // 将三个部分分别加入结果集
        if(left.size() > 0){
            ans.addAll(left);
        }
        if(right.size() > 0){
            ans.addAll(right);
        }
        if(middle.size() > 0){
            ans.addAll(middle);
        }
        return ans;
    }

    // 合并所有的逆序对
    private static List<List<Integer>> merge(int[] arr, int l, int r, int mid) {
        List<List<Integer>> ans = new ArrayList<>();
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int index = 0;
        while(p1 <= mid && p2 <= r){
            if(arr[p1] > arr[p2]){
                // 此时p2后面的所有元素，都比p1大
                for (int i = p2; i <= r; i++) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(arr[p1]);
                    temp.add(arr[i]);
                    ans.add(temp);
                }
                help[index++] = arr[p1++];
            }else{
                help[index++] = arr[p2++];
            }
        }
        while(p1 <= mid){
            help[index++] = arr[p1++];
        }
        while(p2 <= r){
            help[index++] = arr[p2++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return ans;
    }

    // 编写测试类
    public static void main(String[] args) {
        int[] arr = {1,7,3,9,5,6};
        List<List<Integer>> res = reversePairs(arr);
        for (List<Integer> re : res) {
            System.out.println(re.toString());
        }
    }

}
