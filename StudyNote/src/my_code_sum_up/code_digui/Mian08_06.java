package my_code_sum_up.code_digui;

import java.util.List;

// 面试体
// 汉诺塔问题
// 面试题 08.06. 汉诺塔问题
public class Mian08_06 {

    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        getAns(A, B, C, A.size());
    }

    private void getAns(List<Integer> A, List<Integer> B, List<Integer> C, int n) {
        if(n == 0) return;
        // 先把A上n - 1个元素拿到 B上面去
        getAns(A, C, B, n - 1);
        // 把A 上面剩余的唯一元素拿到C上
        move(A,C);
        // 然后再将B上面n-1个元素取出放到C上面去
        getAns(B, A, C, n - 1);
    }

    private void move(List<Integer> A, List<Integer> B) {
        int a = A.get(A.size() - 1);
        A.remove(a);
        B.add(a);
    }
}
