package yc.al.zuo.base;

import java.util.Stack;

/**
 * 使用递归实现栈的反转
 * @author code_yc
 * @version 1.0
 * @date 2020/4/25 8:44
 */
public class Class8_Code5_ReverseStackUsingRecursive {

    // 逆序栈
    public static void reverse(Stack<Integer> stack) {
        // 如果栈空则，直接返回
        if(stack.empty()){
            return;
        }
        // 首先获取并删除出栈底的元素
        int i = getAndRemoveLast(stack);
        // 将删除栈底元素的栈进行反转
        reverse(stack);
        // 然后将之前取出来栈底元素再放到栈中
        stack.push(i);
    }

    private static int getAndRemoveLast(Stack<Integer> stack) {
        int result = stack.pop();
        if(stack.empty()){
            return result;
        }else{
            // 继续向下面获取最后一个元素
            int last = getAndRemoveLast(stack);
            // 之前获取出来的result不是栈底元素，所以需要重新将元素压回去
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }
    }
}
