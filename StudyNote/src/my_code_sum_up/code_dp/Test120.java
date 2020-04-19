package my_code_sum_up.code_dp;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.HashMap;
import java.util.List;

//120. 三角形最小路径和
// 方法一：暴力递归
// 方法二：动态规划
public class Test120 {

    /**
     * 方法一： 暴力递归(超时)
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null) return 0;
        return getAns(triangle,0,0);
    }

    private int getAns(List<List<Integer>> triangle, int level,int site) {
        if(level == triangle.size()){
            return 0;
        }
        List<Integer> cur = triangle.get(level);
        int ans = cur.get(site);
        // 当前位置和下面两个可选位置和较小的相加即为结果
        ans += Math.min(getAns(triangle,level+1,site),getAns(triangle,level+1,site+1));
        return ans;
    }

    // 暴力递归优化（memo技术优化）
    public int minimumTotal2(List<List<Integer>> triangle) {
        if(triangle == null) return 0;
        HashMap<String,Integer> memo = new HashMap<>();
        return getAns2(triangle,0,0,memo);
    }

    private int getAns2(List<List<Integer>> triangle, int level,int site,HashMap<String,Integer> memo) {
        if(level == triangle.size()){
            return 0;
        }
        String key = level + "@" + site;
        if(memo.containsKey(key)){
            return memo.get(key);
        }
        List<Integer> cur = triangle.get(level);
        int ans = cur.get(site);
        // 当前位置和下面两个可选位置和较小的相加即为结果
        ans += Math.min(getAns2(triangle,level+1,site,memo),getAns2(triangle,level+1,site+1,memo));
        memo.put(key,ans);
        return ans;
    }

    /**
     * 暴力递归改动态规划
     * @param triangle
     * @return
     */
    public int minimumTotal3(List<List<Integer>> triangle) {
        if(triangle == null) return 0;
        int[][] dp = new int[triangle.size() + 1][triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i+1][j],dp[i+1][j+1]);
            }
        }
        return dp[0][0];
    }

    // 优化动态规划
    public int minimumTotal4(List<List<Integer>> triangle) {
        if(triangle == null) return 0;
        int[] dp = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j],dp[j+1]);
            }
        }
        return dp[0];
    }
}