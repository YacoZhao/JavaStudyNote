package my_code_sum_up.code_bt;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// 89. 格雷编码
// 方法一：遍历迭代
public class Test89 {

    //--------------------------------方法一： 遍历迭代-------------------------------------------
    public List<Integer> grayCode1(int n) {
        List<Integer> res = new ArrayList<>();
        if(n < 1) {
            res.add(0);
            return res;
        }
        // 创建队列（并初始化0和1）
        Deque<String> ans = new ArrayDeque<>();
        ans.add("0");
        ans.add("1");
        for (int i = 1; i < n; i++) {
            // 遍历ans，左边弹出加上0或者1，然后在后边加入
            int size = ans.size();
            for (int j = 0; j < size; j++) {
                String cur = ans.removeFirst();
                // 奇数为先加0，再加1；偶数位先加1，再加0
                if(j % 2 == 0){
                    ans.addLast(cur + "0");
                    ans.addLast(cur + "1");
                }else{
                    ans.addLast(cur + "1");
                    ans.addLast(cur + "0");
                }
            }
        }
        // 将字符串List改造为十进制数
        for (String s : ans) {
            res.add(Integer.valueOf(s,2));
        }
        return res;
    }

    //-----------------------------方法二： 简化------------------------------------------
    public List<Integer> grayCode2(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);   // 初始化先加上0，如果n==0时，也可以返回正确的结果
        int head = 1;
        // 遍历n便
        for (int i = 0; i < n; i++) {
            // 从ans的后面向前遍历，高位加0不变，所以只考虑高位加1的情况
            for (int j = res.size() - 1; j >= 0; j--) {
                res.add(head + res.get(j));
            }
            head <<= 1;  // 右移，放大2倍
        }
        return res;
    }
}
