package my_code_sum_up.code_tanxin;

import java.util.Arrays;

// 分发糖果
public class LeeCode135 {

    public int candy(int[] ratings) {
        if (ratings.length == 1) return 1;
        int n = ratings.length;
        int[] candy = new int[n];
        Arrays.fill(candy,1);// 首先需要保证每个孩子手上有一颗糖
        // 从左至右遍历数组
        for (int i = 1; i < n; i++) {
            if(ratings[i] > ratings[i - 1]){
                candy[i] = candy[i - 1] + 1;;
            }
        }
        // 从右到左再来一遍
        for (int i = n - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1] && candy[i] <= candy[i + 1]){
                candy[i] = candy[i + 1] + 1;
            }
        }
        // 返回结果集
        int ans = 0;
        for (int c : candy) {
            ans += c;
        }
        return ans;
    }
}
