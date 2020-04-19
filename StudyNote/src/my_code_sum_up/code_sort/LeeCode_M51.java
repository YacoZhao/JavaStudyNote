package my_code_sum_up.code_sort;

//面试题51. 数组中的逆序对
// 方法：  归并排序
public class LeeCode_M51 {


    // 逆序对问题
    public int reversePairs(int[] arr){
        if(arr.length > 1) {
            return getAns(arr, 0, arr.length - 1);
        }
        return 0;
    }

    // 分治
    private int getAns(int[] arr, int l, int r) {
        if(l == r) return 0;
        int mid = l + ((r - l) >> 1);

        int left = getAns(arr,l,mid);       // 求出左边的所有逆序对
        int right = getAns(arr,mid+1,r);  // 求出右边的所有逆序对
        int middle = merge(arr,l,r,mid);    // 合并之后的逆序对
        // 将三个部分分别加入结果集
        return left + right + middle;
    }

    // 合并所有的逆序对
    private int merge(int[] arr, int l, int r, int mid) {
        int ans = 0;
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int index = 0;
        while(p1 <= mid && p2 <= r){
            if(arr[p1] > arr[p2]){
                ans += (r - p2 + 1);
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
}
