package my_code_sum_up.code_bt;

import com.sun.javafx.scene.layout.region.SliceSequenceConverter;

import java.util.ArrayList;
import java.util.List;

//60. 第k个排列
// 方法一：回溯法
// 方法二；设计算法
public class Test60 {
    //########################方法一：回溯法#############################
    // 时间复杂度巨高
    int k;
    String ans;

    public String getPermutation(int n, int k) {
        this.k = k;
        boolean[] dp = new boolean[n+1];
        dfs(n,"",dp);
        return ans;
    }

    private void dfs(int n, String path, boolean[] dp){
        if(path.length() == n){
            k--;
            if(k == 0){
                ans = new String(path);
            }
            return;
        }
        if(k < 0) return;
        for (int i = 1; i <= n; i++) {
            if(!dp[i]){
                dp[i] = true;
                path += i;
                dfs(n,path,dp);
                path = path.substring(0,path.length() - 1);
                dp[i] = false;
            }
        }
    }

    //########################方法二：设计算法#############################
    public String getPermutation2(int n, int k) {
        if(n == 1) return 1 + "";
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }
        return getAns(nums,n,k);
    }

    private String getAns(List<Integer> nums, int n, int k){
        if(n == 1) return nums.get(0) + "";
        //求当前每个组的元素个数
        int groupNum = factorial(n - 1);
        //求k在第几组内
        int onGroup = (k - 1) / groupNum;
        // 获取当前位置的元素，作为结果集的第一位
        int num = nums.get(onGroup);
        // 删除结果集当前元素
        nums.remove(onGroup);
        // 计算下一层的k值
        k = k % groupNum;
        k = k == 0 ? groupNum : k;
        // 递归进入下一层
        return num + "" + getAns(nums,n - 1, k);
    }

    // 求n的阶乘
    private int factorial(int n) {
        if(n <= 1){
            return 1;
        }else{
            return n * factorial(n - 1);
        }
    }
}
