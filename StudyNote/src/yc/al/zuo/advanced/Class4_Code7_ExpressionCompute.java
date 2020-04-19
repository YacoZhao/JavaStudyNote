package yc.al.zuo.advanced;

import java.util.LinkedList;

// 4-7 计算字符串表达式
public class Class4_Code7_ExpressionCompute {

    // 给定一个字符串表达式，求其计算结构
    public static int getValue(String str) {
        return value(str.toCharArray(), 0)[0];
    }

    private static int[] value(char[] str, int i) {
        LinkedList<String> que = new LinkedList<>();
        int pre = 0;  // 用于记录多位的数值
        int[] bra = null;
        while (i < str.length && str[i] != ')') {  // 等于右括号退出循环
            if (str[i] <= '9' && str[i] >= '0') {
                pre = pre * 10 + (str[i++] - '0');
            } else if (str[i] != '(') {
                // 如果不是'(',那么只可能是运算符号
                // 首先将前面计算法pre扔进链表
                addNum(que, pre);
                que.addLast(String.valueOf(str[i++]));
                // 清空pre
                pre = 0;
            } else {
                bra = value(str, i + 1);
                pre = bra[0];
                i = bra[1] + 1;
            }
        }
        // 最后将最后的pre加入
        addNum(que,pre);
        return new int[]{getNum(que),i};
    }

    // 给定一个队列，求其中表达式的计算结果
    private static int getNum(LinkedList<String> queue) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while(!queue.isEmpty()){
            cur = queue.pollFirst();
            if(cur.equals("+")){
                add = true;
            }else if(cur.equals("-")){
                add = false;
            }else{
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

    // 向队列种添加一个元素
    private static void addNum(LinkedList<String> queue, int num) {
        if (!queue.isEmpty()) {
            int cur = 0;
            String top = queue.pollLast();
            if (top.equals("+") || top.equals("-")) {
                queue.addLast(top);
            } else {
                cur = Integer.valueOf(queue.pollLast());
                num = top.equals("*") ? (cur * num) : (cur / num);
            }
        }
        queue.addLast(String.valueOf(num));
    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                        测试集
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static void main(String[] args) {
        String exp = "48*((70-65)-43)+8*1";
        System.out.println(getValue(exp));

        exp = "4*(6+78)+53-9/2+45*8";
        System.out.println(getValue(exp));

        exp = "10-5*3";
        System.out.println(getValue(exp));

        exp = "-3*4";
        System.out.println(getValue(exp));

        exp = "3+1*4";
        System.out.println(getValue(exp));
    }
}
